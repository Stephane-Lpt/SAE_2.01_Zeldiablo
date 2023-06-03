package gameLaby.laby;

/**
 * Représente une amulette
 */
public class Amulette extends Objet {

    /**
     * Attribut permetant de savoir si le héros possède l'amulette
     */
    private boolean possede;

    /**
     * Constructeur d'une amulette aux coordonnées x,y
     * @param x numéro de colonne de la case
     * @param y numéro de ligne de la case
     */
    public Amulette(int x, int y){
        super(x, y);
        this.possede = false;
    }

    /**
     * permet de changer le statut de l'amulette, possédée ou non
     */
    public void setPossede(){
        this.possede = true;
    }

    /**
     * retourne l'attribut possede, true si c'est l'amulette a été trouvé, false sinon.
     * @return
     */
    public boolean getPossede(){
        return this.possede;
    }
}
