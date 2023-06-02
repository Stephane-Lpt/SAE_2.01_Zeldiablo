package gameLaby.laby;

/**
 * représente une Case
 */
public class Case extends Entite {

    /**
     * Attribut permetant de savoir si la case a été trouvée ou même activée
     */
    private boolean trouvee;

    /**
     * Constructeur d'une case aux coordonnées x,y
     * @param dx numéro de colonne de la case
     * @param dy numéro de ligne de la case
     */
    public Case(int dx, int dy){
        super(dx, dy);
        this.trouvee = false;
    }

    /**
     * permet de savoir si deux cases sont les mêmes (aux mêmes coordonnées)
     * @param o objet Case dont on veut vérifier si c'est la même.
     * @return true si les deux cases sont les mêmes, false sinon
     */
    @Override
    public boolean equals(Object o){
        Case c = (Case) o;
        return (this.x == c.getX() && this.y == c.getY());
    }


    /**
     * permet de changer se statut de la case, trouvée ou non
     */
    public void setTrouvee(){
        this.trouvee = true;
    }

    /**
     * retourne l'attribut trouvee, true si c'est la case a été trouvé, false sinon.
     * @return
     */
    public boolean getTrouvee(){
        return this.trouvee;
    }
}
