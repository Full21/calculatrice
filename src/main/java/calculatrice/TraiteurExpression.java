package calculatrice;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraiteurExpression {

	private TraiteurExpression() {
	}

	public static void retirerBlanc(StringBuilder expression) {
		String chaine = expression.toString();
		chaine = chaine.replaceAll("\\s+", "");
		expression.setLength(0);
		expression.append(chaine);
	}

	private static Double extraireNombre(StringBuilder contenu) {
		String regexNombre = "^\\d+(\\.\\d+)?";
		Matcher mNombre = Pattern.compile(regexNombre).matcher(contenu);

		if (mNombre.find()) {
			Double nombre = Double.parseDouble(mNombre.group());
			contenu.delete(0, mNombre.end());
			return nombre;
		}
		return null;
	}

	private static List extraireOperateursPartie(String groupOperateur) {
		String operateurString = "";
		List<Object> operateurs = new ArrayList<>();

		for (int i = 0; i < groupOperateur.length(); i++) {
			operateurString += groupOperateur.charAt(i);
			Operateur op = Operateur.trouverOperateur(operateurString);
			if (op != null) {
				operateurs.add(op);
				operateurString = "";
			}
		}
		return operateurs;
	}

	private static List extraireOperateurs(StringBuilder contenu) {
		String regexOp = "^[^0-9.]+";
		Matcher mOp = Pattern.compile(regexOp).matcher(contenu);
		List operateurs = null;
		if (mOp.find()) {
			String groupOperateur = mOp.group();
			operateurs = extraireOperateursPartie(groupOperateur);
			contenu.delete(0, mOp.end());
		}
		return operateurs;
	}
	
	
	private static boolean doitInsererFois(Object a, Object b) {

	    boolean aNombre = a instanceof Double;
	    boolean bNombre = b instanceof Double;

	    boolean aParenFerme = a == Operateur.PARENT_FERME;
	    boolean bParenOuvre = b == Operateur.PARENT_OUVRE;

	    boolean bFonction = b instanceof Operateur op && op.estUnaire();

	    return
	        (aNombre && bParenOuvre) ||
	        (aParenFerme && bNombre) ||
	        (aParenFerme && bParenOuvre) ||
	        (aNombre && bFonction);
	}
	

	
	private static List<Object> insererMultiplicationsImplicites(List infixe) {
	    List<Object> resultat = new ArrayList<>();

	    for (int i = 0; i < infixe.size(); i++) {
	        Object courant = infixe.get(i);
	        resultat.add(courant);

	        if (i == infixe.size() - 1) continue;

	        Object suivant = infixe.get(i + 1);

	        if (doitInsererFois(courant, suivant)) {
	            resultat.add(Operateur.FOIS);
	        }
	    }
	    return resultat;
	}
	

	public static List expressionInfixe(String expression) {
		StringBuilder sb = new StringBuilder(expression);
		retirerBlanc(sb);

		List infixe = new ArrayList<>();

		while (sb.length() > 0) {

			Double nombre = extraireNombre(sb);
			if (nombre != null) {
				infixe.add(nombre);
				continue;
			}
			List op = extraireOperateurs(sb);
			if (op != null) {
				infixe.addAll(op);
				continue;
			}
		}

		return insererMultiplicationsImplicites(infixe);
	}

	public static List expressionPostFixe(List expressionInfixe) {

		Stack<Operateur> pileOp = new Stack();
		List sortie = new ArrayList<Object>();

		for (Object obj : expressionInfixe) {
			
			if (obj instanceof Double d) {
				sortie.add(d);
			} else if (obj instanceof Operateur operateur) {
				if (operateur == Operateur.PARENT_OUVRE || pileOp.isEmpty()) {
					pileOp.push(operateur);
					continue;
				}

				if (operateur == Operateur.PARENT_FERME) {
					while (pileOp.peek() != Operateur.PARENT_OUVRE) {
						sortie.add(pileOp.pop());
					}
					pileOp.pop();
					if(!pileOp.isEmpty()) sortie.add(pileOp.pop());
					continue;
				}
				
				Operateur dernierOperateur = pileOp.peek();
				if (dernierOperateur.estMoinsPrioritaire(operateur)) {
					pileOp.push(operateur);	
				} else {
					sortie.add(pileOp.pop());										
					pileOp.push(operateur);					
				}
			}
		}

		while (!pileOp.isEmpty())
			sortie.add(pileOp.pop());

		return sortie;
	}
	
}
