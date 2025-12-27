package visuel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Sortie extends JLabel implements Ecran {

	private static Sortie sortie = null;	
	
	private Sortie () { 
		miseEnforme();
	}
	
	public static final Sortie getSortie() {
		 if (sortie == null) {
        	sortie = new Sortie();
        }
        return sortie;
	}

	
	@Override
	public void miseEnforme() {
		setOpaque(true);
		setBorder(new EmptyBorder(5, 10, 5, 10));
		setBackground(new Color(214, 255, 165));
		setFont(new Font("Yu Gothic Medium", 0, 18));
		setHorizontalAlignment(SwingConstants.LEFT);
    	setPreferredSize(DIMENSION);
    	setText("0.0");	
	}

	
}
