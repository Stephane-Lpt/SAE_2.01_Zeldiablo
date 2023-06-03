package gameLaby.laby;


/**
 * gere une entité situe en x,y
 */
public class Entite {

    /**
     * position de l'entité
     */
    int x, y;


    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Entite(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    /**
     * permet de savoir si l'entité est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si l'entité est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    // ############################################
    // GETTER
    // ############################################

    /**
     * @return position x de l'entité
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * @return position y de l'entité
     */
    public int getY() {
        //getter
        return this.y;
    }

    /**
     * change la position de l'entité
     */
    public void setX(int x) {
        //setter
       this.x = x;
    }

    /**
     * change la position de l'entité
     */
    public void setY(int y) {
        //setter
        this.y = y;
    }
}
