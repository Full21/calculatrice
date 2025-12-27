package visuel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Entree extends JTextField implements Ecran{
		
	private static Entree ecran = null;
	
	private Entree () {
		miseEnforme();

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();				
			}
		});
				
	}
	
	public static final Entree getEcran() {
		if (ecran == null) {
        	ecran = new Entree();
        }
        return ecran;
	}

	@Override
	public void miseEnforme() {
		setOpaque(true);
		setBorder(new EmptyBorder(5, 10, 5, 10));
		setBackground(new Color(214, 255, 165));
		setFont(new Font("Yu Gothic Medium", 0, 18));
		setHorizontalAlignment(SwingConstants.LEFT);
		setPreferredSize(DIMENSION);
	}
	

}
