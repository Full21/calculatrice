package calculatrice;

import javax.swing.text.BadLocationException;

import visuel.Entree;

public class CommandeEffacer implements Commande {


	@Override	
	public void execute() {
		Entree ecran = Entree.getEcran();
		int position = ecran.getCaretPosition();
		
		if(position == 0) return;		
		try {
			ecran.getDocument().remove(position - 1, 1);
		} catch (BadLocationException e) {
		    e.printStackTrace();
		}		
	}
	
}
