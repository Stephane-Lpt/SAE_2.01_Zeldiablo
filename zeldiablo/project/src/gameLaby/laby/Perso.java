package gameLaby.laby;
/**
 * gere un personnage situe en x,y
 */
public class Perso extends Entite{

    /**
     * Points de vie du personnage
     */
    int pv;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy, int pp) {
        super(dx, dy);
        this.pv = pp;
    }


    // ############################################
    // GETTER
    // ############################################


    public int getPv(){
        return this.pv;
    }

    public void changerPv(int p){
        if(this.pv + p >= 0){
            this.pv += p;
        }
    }



    public boolean etreMort(){
        return this.pv <= 0;
    }
}
