package visuel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import calculatrice.Commande;

public class Bouton extends JButton implements ActionListener, Visuel{

	private String titre;
	private Color couleurFond, couleurFondPresse;
	private Color couleurTitre;
	private Dimension dimension;
	private Commande commande;
	
	public Bouton(String titre, Dimension dimension,
			Color couleurFond, Color couleurFondPresse, 
			Color couleurTitre, Commande commande) {
		super();
		setDimension(dimension);
		setCouleurFond(couleurFond);
		setCouleurFondPresse(couleurFondPresse);		
		setCouleurTitre(couleurTitre);		
		setTitre(titre);
		setCommande(commande);		
		miseEnforme();
		addActionListener(this);
	}
	
	public Bouton(String titre, Commande commande) {
		this(titre, new Dimension(45, 30), 
				new Color(153, 153, 255), 
				new Color(147, 147, 249),
				Color.WHITE, commande);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		setText(titre);
		setActionCommand(titre);		
		this.titre = titre;
	}

	public Color getCouleurFond() {
		return couleurFond;
	}

	public void setCouleurFond(Color couleurFond) {
		setBackground(couleurFond);		
		this.couleurFond = couleurFond;
	}
	
	public void setCouleurFondPresse(Color couleurFondPresse) {
		setBackground(couleurFondPresse);		
		this.couleurFondPresse = couleurFondPresse;
	}
	
	public Color getCouleurFondPresse() {
		return couleurFondPresse;
	}

	public Color getCouleurTitre() {
		return couleurTitre;
	}

	public void setCouleurTitre(Color couleurTitre) {
		setForeground(couleurTitre);		
		this.couleurTitre = couleurTitre;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		setPreferredSize(dimension);		
		this.dimension = dimension;
	}
	
	public Commande getCommande() {
		return commande;
	}


	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	public void miseEnforme() {
		setFocusPainted(false);
		setFont(new Font("Arial", Font.BOLD, 16));
        setContentAreaFilled(false);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		commande.execute();		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON
	    );

	    int largeur = getWidth();
	    int hauteur = getHeight();
	    int rayonHaut = 10;
	    int rayonBas = 20;
	    
	    Path2D forme = new Path2D.Double();
	    forme.moveTo(rayonHaut, 0);
	    forme.lineTo(largeur - rayonHaut, 0);
	    forme.quadTo(largeur, 0, largeur, rayonHaut);
	    forme.lineTo(largeur, hauteur - rayonBas);
	    forme.quadTo(largeur, hauteur, largeur - rayonBas, hauteur);
	    forme.lineTo(rayonBas, hauteur);
	    forme.quadTo(0, hauteur, 0, hauteur - rayonBas);
	    forme.lineTo(0, rayonHaut);
	    forme.quadTo(0, 0, rayonHaut, 0);
	    forme.closePath();

	    if (getModel().isPressed()) {
	        g2.setColor(getCouleurFondPresse());
	    } else {
	        g2.setColor(getCouleurFond());
	    }
	    g2.fill(forme);
	    g2.setColor(getCouleurFond());
	    g2.draw(forme);
	    super.paintComponent(g);
	}	
		

}
