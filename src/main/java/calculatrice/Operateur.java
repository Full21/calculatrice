package calculatrice;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum Operateur {
	
    // Opérateurs unaires
    
    RACINE("√", TypeOperateur.UNAIRE, Operateur.TRES_GRANDE_PRIORITE, Fonction::racineCarree),
    FACT("!", TypeOperateur.UNAIRE, Operateur.TRES_GRANDE_PRIORITE,
            a -> (double) Fonction.factorielle(Fonction.troncature(a))),
	   
    // Opérateurs binaires
    
    PLUS("+", TypeOperateur.BINAIRE, Operateur.PETITE_PRIORITE, (a, b) -> a + b),
    MOINS("-", TypeOperateur.BINAIRE, Operateur.PETITE_PRIORITE, (a, b) -> a - b),
    FOIS("x", TypeOperateur.BINAIRE, Operateur.MOYENNE_PRIORITE, (a, b) -> a * b),
    DIV("/", TypeOperateur.BINAIRE, Operateur.MOYENNE_PRIORITE, Fonction::division),
    PUISSANCE("^", TypeOperateur.BINAIRE, Operateur.GRANDE_PRIORITE, Math::pow),
    MODULO("%", TypeOperateur.BINAIRE, Operateur.GRANDE_PRIORITE, (a, b) -> a % b),
    
    // Fonctions (binaires)
    
    PGCD("pgcd", TypeOperateur.FONCTION_BINAIRE, Operateur.GRANDE_PRIORITE,
            (a, b) -> (double) Fonction.pgcd(Fonction.troncature(a), Fonction.troncature(b))),
    PPCM("ppcm", TypeOperateur.FONCTION_BINAIRE, Operateur.GRANDE_PRIORITE,
            (a, b) -> (double) Fonction.ppcm(Fonction.troncature(a), Fonction.troncature(b))),


    // Fonctions (unaires)
    
    LN("ln",TypeOperateur.FONCTION_UNAIRE, Operateur.TRES_GRANDE_PRIORITE, Fonction::logarithmeNaturel ),
    LOG("log",TypeOperateur.FONCTION_UNAIRE, Operateur.TRES_GRANDE_PRIORITE, Fonction::logarithmeBase10 ),
    SIN("sin", TypeOperateur.FONCTION_UNAIRE, Operateur.TRES_GRANDE_PRIORITE, Fonction::sinus),
    COS("cos", TypeOperateur.FONCTION_UNAIRE, Operateur.TRES_GRANDE_PRIORITE, Fonction::cosinus),
    TAN("tan", TypeOperateur.FONCTION_UNAIRE, Operateur.TRES_GRANDE_PRIORITE, Fonction::tangente),
    
    // Parenthèses
    
    PARENT_OUVRE("(", TypeOperateur.PARENTHESE, Operateur.LA_PRIORITE),
    PARENT_FERME(")", TypeOperateur.PARENTHESE, Operateur.LA_PRIORITE);
    
    // Priorités
	
    public static final int PETITE_PRIORITE = 0;
    public static final int MOYENNE_PRIORITE = 1;
    public static final int GRANDE_PRIORITE = 2;
    public static final int TRES_GRANDE_PRIORITE = 3;
    public static final int LA_PRIORITE = -1;

    private final String libelle;
    private final TypeOperateur type;
    private final int priorite;

    private BiFunction<Double, Double, Double> opBinaire;
    private Function<Double, Double> opUnaire;

    Operateur(String libelle, TypeOperateur type, int priorite) {
        this.libelle = libelle;
        this.type = type;
        this.priorite = priorite;
    }

    Operateur(String libelle, TypeOperateur type, int priorite,
              BiFunction<Double, Double, Double> opBinaire){
        this(libelle, type, priorite);
        this.opBinaire = opBinaire;
    }

    Operateur(String libelle, TypeOperateur type, int priorite,
              Function<Double, Double> opUnaire) {
        this(libelle, type, priorite);
        this.opUnaire = opUnaire;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getPriorite() {
        return priorite;
    }

    public TypeOperateur getType() {
        return type;
    }

    public boolean estUnaire() {
        return type == TypeOperateur.UNAIRE || type == TypeOperateur.FONCTION_UNAIRE;
    }

    public boolean estBinaire() {
        return type == TypeOperateur.BINAIRE || type == TypeOperateur.FONCTION_BINAIRE;
    }
    public boolean estFonctionBinaire() {
    	return type == TypeOperateur.FONCTION_BINAIRE;
    }
    public boolean estParentheseOuvrante() {
        return this == PARENT_OUVRE;
    }

    public boolean estParentheseFermante() {
        return this == PARENT_FERME;
    }


    public double execute(double a) {
        return opUnaire.apply(a);
    }

    public double execute(double a, double b) {
        return opBinaire.apply(a, b);
    }
  

    public static Operateur trouverOperateur(String libelle) {
        for (Operateur op : values()) {
            if (op.libelle.equalsIgnoreCase(libelle)) {
                return op;
            }
        }
        return null;
    }
    
    public boolean estMoinsPrioritaire(Operateur o) {
        return this.priorite < o.priorite;
    }
    
    public boolean estAutantPrioritaire(Operateur o) {
        return this.priorite == o.priorite;
    }
       
}
