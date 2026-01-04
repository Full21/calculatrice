package calculatrice;

import java.util.List;

import visuel.Entree;
import visuel.Sortie;

public class CommandeEgale implements Commande {

	public static Double ans = 0.0;

	@Override
	public void execute() {
		String contenuEcran = Entree.getEcran().getText();
		StringBuilder expression = new StringBuilder(contenuEcran);
		TraiteurExpression.retirerBlanc(expression);
		try {
			
			List expressionInfixe = TraiteurExpression.expressionInfixe(contenuEcran);
			System.out.println(expressionInfixe);
			
			ValidateurExpression.valider(expression);
			
			List expressionPostfixe = TraiteurExpression.expressionPostFixe(expressionInfixe);
			
			System.out.println(expressionPostfixe);
			
			ans = MoteurCalculatrice.calculer(expressionPostfixe);
			
			Sortie.getSortie().setText(""+ans);
			
		} catch (SyntaxeException | ArithmeticException e) {
			Sortie.getSortie().setText(e.getMessage());
		}				
	}
}
