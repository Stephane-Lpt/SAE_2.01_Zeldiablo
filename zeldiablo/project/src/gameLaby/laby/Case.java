package gameLaby.laby;

public class Case extends Entite {

    private boolean trouvee;

    public Case(int dx, int dy){
        super(dx, dy);
        this.trouvee = false;
    }

    public boolean equals(Object o){
        Case c = (Case) o;
        return (this.x == c.getX() && this.y == c.getY());
    }


    public void setTrouvee(){
        this.trouvee = true;
    }

    public boolean getTrouvee(){
        return this.trouvee;
    }
}
