package gameLaby.laby;

/**
 * gere un troll situe en x,y
 */
public class Troll extends Monstre{

    private boolean ajouterVieTroll = true;

    /**
     * constructeur de troll
     *
     * @param x position selon x
     * @param y position selon y
     */
    public Troll(int x, int y, int pp){
        super(x, y, pp);
    }

    /**
     * @return b boolean correspondant à la condition d'ajout de vie au troll
     */
    public boolean getAjouterVieTroll(){
        return this.ajouterVieTroll;
    }

    /**
     * @param b boolean correspondant à la condition d'ajout de vie au troll
     */
    public void setAjouterVieTroll(boolean b){
        this.ajouterVieTroll = b;
    }
}

