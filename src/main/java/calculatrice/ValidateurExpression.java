package calculatrice;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateurExpression {

	private ValidateurExpression() { }

	public static final String NOMBREVALIDE = "\\d+(\\.\\d+)?";

	public static void valider(StringBuilder expressionInfixe) throws SyntaxeException {
		
		if (expressionInfixe == null || expressionInfixe.isEmpty())
			throw new SyntaxeException("Expression vide");
		
		if(!expressionValide(expressionInfixe))
			throw new SyntaxeException("Erreur de syntaxe");
	}

	
	private static String trouverPortionUnaire(StringBuilder expression) {
		
	    StringBuilder unaireOp = new StringBuilder("(");
	    boolean first = true;
	    
	    for (Operateur op : Operateur.values()) {
	    	if(op == Operateur.FACT) continue; 
	    	/* Les autres opérateurs unaires s'écrivent Operateur(nombre), c'est pas le 
	    		cas du factoriel. Il s'écrit : nombre Operateur*/
	        if (op.estUnaire()) {
	            if (!first) unaireOp.append("|");
	            unaireOp.append(Pattern.quote(op.getLibelle()));
	            first = false;
	        }
	    }
	    unaireOp.append("|\\+|\\-)"); // On rajoute le + et le - unaire

	    String operateurRegexUnaire = unaireOp + "(" + NOMBREVALIDE + "|\\(" + NOMBREVALIDE + "\\))";
	    String chaine = expression.toString();
	    Matcher m = Pattern.compile(operateurRegexUnaire).matcher(chaine);
	    return m.find() ?  m.group() : null;
	        
	}
	
	
	private static String trouverPortionBinaire(StringBuilder expression) {

		/*Dans l'expression, est-ce qu'il y a "nombre operateur nombre" ou bien "(nombre operateur nombre)" ?*/
		/*Extraire une telle portion*/
		
	    StringBuilder binaireOp = new StringBuilder("(");

	    boolean first = true;
	    for (Operateur op : Operateur.values()) {
	        if (op.estBinaire()) {
	            if (!first) binaireOp.append("|");
	            binaireOp.append(Pattern.quote(op.getLibelle()));
	            first = false;
	        }
	    }
	    binaireOp.append(")");

	    String s1 = NOMBREVALIDE + binaireOp + NOMBREVALIDE;
	    String s2 = "\\(" + NOMBREVALIDE + binaireOp + NOMBREVALIDE + "\\)";
	    String operateurRegexBinaire = "(" + s1 + "|" + s2 + ")";

	    String chaine = expression.toString();
	    Matcher m = Pattern.compile(operateurRegexBinaire).matcher(chaine);
	    return m.find() ?  m.group() : null;
	}
	
	
	private static String trouverPortionSingleton(StringBuilder expression) {
	    String operateurSingleton = "\\(" + NOMBREVALIDE + "\\)";	    	    
	    String chaine = expression.toString();
	    Matcher m = Pattern.compile(operateurSingleton).matcher(chaine);
	    return m.find() ?  m.group() : null;
	}
	
	private static String trouverPortionFactoriel(StringBuilder expression) {
			
		 	String operateurFactoriel = "(\\d+|\\(\\d+\\))!"; 
		    String chaine = expression.toString();
		    Matcher m = Pattern.compile(operateurFactoriel).matcher(chaine);
		    return m.find() ?  m.group() : null;
	}

	
	private static void extrairePortion(StringBuilder expression, String portion) {
		String expr = expression.toString();
		expr = expr.replace(portion, "1");		
		expression.setLength(0);
		expression.append(expr);
	
	}


	private static boolean expressionValide(StringBuilder expression) {
		
		String portionBinaire = trouverPortionBinaire(expression);
	    String portionUnaire = trouverPortionUnaire(expression);
	    String portionSingletion = trouverPortionSingleton(expression);	   
	    String portionFacto = trouverPortionFactoriel(expression);
	    
	    while (!(portionBinaire == null && portionUnaire == null 
	    		&& portionSingletion == null && portionFacto == null)) {
	    	if (portionBinaire != null) {
	    		extrairePortion(expression, portionBinaire);
	    	} else if (portionUnaire != null) {
	            extrairePortion(expression, portionUnaire);
	        }else if (portionSingletion != null) {
	        	extrairePortion(expression, portionSingletion);
	        } else if(portionFacto != null) {
	        	extrairePortion(expression, portionFacto);  
	        }
	    	
	        portionBinaire = trouverPortionBinaire(expression);
	        portionUnaire = trouverPortionUnaire(expression);
	        portionSingletion = trouverPortionSingleton(expression);
	        portionFacto = trouverPortionFactoriel(expression);
	    }
	    
	    return (expression.toString().matches(NOMBREVALIDE));
	}

}

