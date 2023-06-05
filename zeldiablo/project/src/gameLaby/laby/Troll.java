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

    public boolean getAjouterVieTroll(){
        return this.ajouterVieTroll;
    }

    public void setAjouterVieTroll(boolean b){
        this.ajouterVieTroll = b;
    }
}

