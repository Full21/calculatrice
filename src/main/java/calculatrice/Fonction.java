package calculatrice;

public class Fonction {

    // 🔢 Fonctions de base

    public static double addition(double a, double b) {
        return a + b;
    }

    public static double soustraction(double a, double b) {
        return a - b;
    }

    public static double multiplication(double a, double b) {
        return a * b;
    }

    public static double division(double a, double b) throws ArithmeticException {
    	if(b == 0) throw new ArithmeticException("Erreur mathématique");    	
        return a / b;
    }

    public static double modulo(double a, double b) throws ArithmeticException {
    	if(b == 0) throw new ArithmeticException("Erreur mathématique"); 
        return a % b;
    }

    public static double valeurAbsolue(double a) {
        return Math.abs(a);
    }

    // 🔢 Puissances & racines

    public static double puissance(double a, double b) {
        return Math.pow(a, b);
    }

    public static double racineCarree(double a) throws ArithmeticException {
    	if(a < 0) throw new ArithmeticException("Erreur mathématique"); 
        return Math.sqrt(a);
    }

    public static double racineNIeme(double a, double n) throws ArithmeticException {
    	if(a < 0) throw new ArithmeticException("Erreur mathématique"); 
        return Math.pow(a, 1.0 / n);
    }

    public static double carre(double a) {
        return a * a;
    }

    public static double cube(double a) {
        return a * a * a;
    }


    // 📐 Trigonométrie (radians)

    public static double sinus(double x) {
        return Math.sin(x);
    }

    public static double cosinus(double x) {
        return Math.cos(x);
    }

    public static double tangente(double x) {
        return Math.tan(x);
    }

    public static double arcSinus(double x) {
        return Math.asin(x);
    }

    public static double arcCosinus(double x) {
        return Math.acos(x);
    }

    public static double arcTangente(double x) {
        return Math.atan(x);
    }

    public static double degresVersRadians(double deg) {
        return Math.toRadians(deg);
    }

    public static double radiansVersDegres(double rad) {
        return Math.toDegrees(rad);
    }


    // 📈 Logarithmes & exponentielles

    public static double exponentielle(double x) {
        return Math.exp(x);
    }

    public static double logarithmeNaturel(double x) {
        return Math.log(x);
    }

    public static double logarithmeBase10(double x) {
        return Math.log10(x);
    }

    public static double logarithmeBaseN(double x, double n) {
        return Math.log(x) / Math.log(n);
    }

    // 🎯 Arrondis & parties entières

    public static long arrondi(double x) {
        return Math.round(x);
    }

    public static double plafond(double x) {
        return Math.ceil(x);
    }

    public static double plancher(double x) {
        return Math.floor(x);
    }

    public static int troncature(Double x) {
    	double xp = x;
        return (int) xp;
    }


    // 🔁 Inverse & pourcentages

    public static double inverse(double x) {
        return 1 / x;
    }

    public static double pourcentage(double x) {
        return x / 100;
    }

    public static double pourcentageDeAParRapportAB(double a, double b) {
        return (a * 100) / b;
    }

    // 🎲 Fonctions utiles

    public static double minimum(double a, double b) {
        return Math.min(a, b);
    }

    public static double maximum(double a, double b) {
        return Math.max(a, b);
    }

    public static double nombreAleatoire() {
        return Math.random();
    }

    public static double signe(double x) {
        return Math.signum(x);
    }

    // =========================
    // 🧠 Fonctions avancées
    // =========================

    public static double factorielle(double n) throws ArithmeticException {

    	if(n < 0) throw new ArithmeticException("Erreur mathématique"); 
        if (n == 0) return 1;
        return n * factorielle(n - 1);
    }

    public static int pgcd(int a, int b) throws ArithmeticException {
    	if(a == 0)
    		if(b == 0) throw new ArithmeticException("Erreur mathématique");
    		else return b;
    	else {
    		if (b == 0) return a;
    		else {
    			if (a > b) return pgcd(a % b, b);
    			else return pgcd(b % a, a);
    		}
    	}    		
    }

    public static int ppcm(int a, int b) throws ArithmeticException {
        return a * b / pgcd(a, b);
    }

}
