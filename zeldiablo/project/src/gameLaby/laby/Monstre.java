package gameLaby.laby;

/**
 * gere un monstre situe en x,y
 */
public class Monstre extends Perso{

    /**
     * constructeur
     *
     * @param x position selon x
     * @param y position selon y
     */
    public Monstre(int x, int y, int pp){
        super(x, y, pp);
    }

    public void attaquer(Perso p){
        if(p instanceof Heros){
            p.changerPv(-1);
        }
    }
}
