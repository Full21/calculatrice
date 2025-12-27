package visuel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import calculatrice.Commande;
import calculatrice.CommandeEcrire;
import calculatrice.CommandeEffacer;
import calculatrice.CommandeEgale;
import calculatrice.CommandeReinitiliser;

public class Clavier extends JPanel implements Visuel {
	
	private ArrayList<Bouton> boutons = new ArrayList<Bouton>();
	private static final Dimension DIMENSION = new Dimension(400, 300);
	private static Clavier clavierFonction = null;	
	private static Clavier clavierNumeriqueComplet = null;
	
	private Clavier() {
		miseEnforme();
	}

	private void ajouterBouton(Bouton btn) {
		boutons.add(btn);
		add(btn);
		revalidate();
		repaint();
	}

	private static Clavier creerClavierFonction(Clavier clavier) {
		String[] fonctions = { "COS", "SIN", "!", "PGCD", "PPCM", "√", "(", ")", "ln", "log" };
		Color couleurFonction = new Color(120, 200, 120);

		for (String f : fonctions) {
			clavier.ajouterBouton(new Bouton(f, new Dimension(60, 40), couleurFonction,
					couleurFonction.darker(), Color.WHITE, new CommandeEcrire(f.toLowerCase())));
		}
		
		return clavier;
	}

	private static Clavier creerClavierOperation() {
		Clavier clavierOp = new Clavier();
		clavierOp.setPreferredSize(new Dimension(150, 180));
		// Opérations
		Color couleurOp = new Color(255, 140, 0);
		String[] ops = {"DEL", "C", "+", "-", "x", "/", "^", "=" };
		for (String op : ops) {
			Commande cmd = op.equals("=") ? new CommandeEgale()
					: op.equals("DEL") ? new CommandeEffacer()
							: op.equals("C") ? new CommandeReinitiliser() : new CommandeEcrire(op);
			clavierOp.ajouterBouton(new Bouton(op, cmd));
		}
		return clavierOp;
	}

	private static Clavier creerClavierNumerique() {
		Clavier clavierNumerique = new Clavier();
		clavierNumerique.setPreferredSize(new Dimension(190, 180));
		
		Color couleurChiffre = new Color(80, 160, 255);
		for (int i = 9; i >= 0; i--) {
			clavierNumerique.ajouterBouton(new Bouton("" + i, new CommandeEcrire("" + i)));
		}
		clavierNumerique.ajouterBouton(new Bouton(".", new CommandeEcrire(".")));
		clavierNumerique.ajouterBouton(new Bouton("ANS", ()->{
			Sortie.getSortie().setText(""+CommandeEgale.ans);
		}));
		return clavierNumerique;
	}
	
	@Override
	public void miseEnforme() {
        setPreferredSize(DIMENSION);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));        		
	}
	
	private static final Clavier getClavierFonction() {
		if(clavierFonction == null) {
			clavierFonction = new Clavier();
	        clavierFonction.setPreferredSize(new Dimension(400, 100));
	        Clavier.creerClavierFonction(clavierFonction);
		}
		return clavierFonction;
	}
	
	
	private static final Clavier getClavierNumeriqueComplet() {
		if(clavierNumeriqueComplet == null) {
			
			clavierNumeriqueComplet = new Clavier();
			clavierNumeriqueComplet.setPreferredSize(new Dimension(400, 200));
			clavierNumeriqueComplet.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
			clavierNumeriqueComplet.add(creerClavierNumerique());
			clavierNumeriqueComplet.add(creerClavierOperation());
		}
		return clavierNumeriqueComplet;
	}

	public static final JPanel getClaviers() {
		JPanel claviers = new JPanel();		
		claviers.setPreferredSize(DIMENSION);
		claviers.add(Clavier.getClavierFonction());
		claviers.add(Clavier.getClavierNumeriqueComplet());
		
		return claviers;
	}
	
}
