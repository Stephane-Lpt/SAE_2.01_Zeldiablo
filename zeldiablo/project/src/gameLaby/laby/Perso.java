package gameLaby.laby;
/**
 * gere un personnage situe en x,y
 */
public abstract class Perso extends Entite{

    /**
     * Points de vie du personnage
     */
    private int pv;

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

    /**
     * retourne le nombre de pv du personnage
     * @return les pv
     */
    public int getPv(){
        return this.pv;
    }

    /**
     * permet de changer le nombre de points de vie
     * @param p le nombre de points de vie à ajouter (argument positif) ou à soustraire (argument négatif)
     */
    public void changerPv(int p){
        if(this.pv + p >= 0){
            this.pv += p;
        }
    }

    /**
     * permet de savoir si le personnage est en vie
     * @return true si le personnage est mort (n'a plus de points de vie) et false sinon
     */
    public boolean etreMort(){
        return this.pv <= 0;
    }


    /**
     * permet à un perso d'attaquer un autre perso
     * La puissance de l'attaque dépend du type de personnage et de celui qui le subit
     * @param p person qui subit l'attaque
     */
    //public abstract void attaquer(Perso p);
}
