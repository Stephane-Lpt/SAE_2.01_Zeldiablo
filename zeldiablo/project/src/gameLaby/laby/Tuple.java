package gameLaby.laby;
/**
 * Gère un tuple contenant des coordonnées et une distance
 */
public class Tuple {

    /**
     * Coordonnées
     */
    private int[] coord;

    /**
     * Distance
     */
    private double dist;

    /**
     * Constructeur
     */
    public Tuple(int[] c, double d){
        this.coord=c;
        this.dist=d;
    }

    /**
     * getter
     *
     * @return coord
     */
    public int[] getCoord(){
        return coord;
    }

    /**
     * getter
     *
     * @return dist
     */
    public double getDist(){
        return dist;
    }
}