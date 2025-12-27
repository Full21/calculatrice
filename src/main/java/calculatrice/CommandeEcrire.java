package calculatrice;

import javax.swing.text.BadLocationException;

import visuel.Entree;

public class CommandeEcrire implements Commande{

	private String texte;
	
	public CommandeEcrire(String texte) {
		setTexte(texte);
	}
	
	@Override
	public void execute() {
		Entree ecran = Entree.getEcran(); 		
		int position = ecran.getCaretPosition();
		
		try {
		    ecran.getDocument().insertString(position, texte, null); 
		    // Insérer le texte au niveau du curseur
		} catch (BadLocationException e) {
		    e.printStackTrace();
		}

	}
	
	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
	
}
