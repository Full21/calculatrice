package calculatrice;

import visuel.Entree;
import visuel.Sortie;

public class CommandeReinitiliser implements Commande{

	@Override
	public void execute() {
		Entree.getEcran().setText("");
		Sortie.getSortie().setText("0.0");
		
	}

}
