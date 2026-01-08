package visuel;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalculatriceUI extends JFrame implements Visuel{
	
	public static Dimension DIMENSION = new Dimension(420, 500);

	public CalculatriceUI (String titre){
		super(titre);
		miseEnforme();
	}
	
	public CalculatriceUI(){
		this("Fenetre");
	}			

	@Override
	public void miseEnforme() {
		
		JPanel panel = new JPanel();			
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        panel.setPreferredSize(DIMENSION);
		panel.setBackground(new Color(255, 255, 255));        
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DIMENSION); 
		setLocationRelativeTo(null);     
		setResizable(false);
        
        panel.add(Ecran.getEcrans());
        panel.add(Clavier.getClaviers());
        
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
		
	}
}
