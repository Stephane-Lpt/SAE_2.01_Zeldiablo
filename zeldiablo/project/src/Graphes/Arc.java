package gameLaby.laby;

/**
 * Classe représente un arc partant d'un nœud grâce à une destination et un coût
 */
public class Arc {

    /**
     * nom du nœud destination de l'arc
     */
    private String dest;

    /**
     * coût (ou poids) de l'arc
     */
    private double cout;

    /**
     * Constructeur d'Arc avec une destination et un coût
     * @param d destination de l'arc
     * @param c coût de l'arc
     */
    public Arc(String d, double c){
        this.dest=d;
        this.cout=c;
    }

    /**
     * méthode qui permet de récupérer la destination de l'arc
     * @return la destination de l'arc
     */
    public String getDest(){
        return this.dest;
    }

    /**
     * méthode qui permet de récupérer le coût de l'arc
     * @return le coût de l'arc
     */
    public double getCout(){
        return this.cout;
    }
}
