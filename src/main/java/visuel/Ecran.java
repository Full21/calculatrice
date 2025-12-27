package visuel;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;

public interface Ecran {
	
	static final Dimension DIMENSION = new Dimension(330, 40);
	public void miseEnforme();
	
	public static JPanel getEcrans() {
    	JPanel ecrans = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));;
    	ecrans.setPreferredSize(new Dimension(330, 80));
    	ecrans.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ecrans.add(Entree.getEcran());
        ecrans.add(Sortie.getSortie());
        return ecrans;
    }

}
