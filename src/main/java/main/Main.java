package main;

import com.formdev.flatlaf.FlatLightLaf;

import visuel.CalculatriceUI;

public class Main {

	public static void main(String[] args) {
		try {									
	        FlatLightLaf.setup(); // Active le thème moderne "Clair"
	    } catch( Exception ex ) {
	        System.err.println( "Échec de l'initialisation du thème" );
	    }
		
		CalculatriceUI calculatrice = new CalculatriceUI("Calculatrice");
		calculatrice.setVisible(true);

	}

}
