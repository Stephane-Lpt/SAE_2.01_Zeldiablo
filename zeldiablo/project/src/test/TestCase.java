import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class TestCase {
    Labyrinthe laby;
    Labyrinthe labySansCase;

    @BeforeEach
    public void setup() throws IOException {
        laby = new Labyrinthe("labySimple/laby1.txt");
        labySansCase = new Labyrinthe("labySimple/laby0.txt");
    }

    @Test
    public void testConstructeur_PasDeCase(){
        assertEquals(labySansCase.cases.size(), 0);
    }

    @Test
    public void testConstructeur_Case(){
        assertEquals(laby.cases.size(), 4);
        assertEquals(laby.cases.get(0).getX(), 3);
        assertEquals(laby.cases.get(0).getY(),3);
        assertEquals(laby.cases.get(1).getX(), 4);
        assertEquals(laby.cases.get(1).getY(),3);
        assertEquals(laby.cases.get(2).getX(), 5);
        assertEquals(laby.cases.get(2).getY(),4);
        assertEquals(laby.cases.get(3).getX(), 6);
        assertEquals(laby.cases.get(3).getY(),4);
    }

    @Test
    public void testVerifierPresenceCase_PasDeCase(){
        this.laby.deplacerPerso(Labyrinthe.DROITE);
        assertEquals(this.laby.heros.getPv(), 10); // Le heros ne doit pas avoir perdu de points de vie sur une case Declencheur
        assertEquals(this.laby.cases.get(3).getTrouvee(), false);
        assertEquals(this.laby.cases.get(0).getTrouvee(), false);
        assertEquals(this.laby.cases.get(1).getTrouvee(), false);
        assertEquals(this.laby.cases.get(2).getTrouvee(), false);
    }

    @Test
    public void testVerifierPresenceCase_CasePiegePresente(){
        this.laby.deplacerPerso(Labyrinthe.HAUT);
        assertEquals(this.laby.heros.getPv(), 9); // Le heros ne doit pas avoir perdu de points de vie sur une case Declencheur
        assertEquals(this.laby.cases.get(0).getTrouvee(), false);
        assertEquals(this.laby.cases.get(1).getTrouvee(), false);
        assertEquals(this.laby.cases.get(3).getTrouvee(), false);
        assertEquals(this.laby.cases.get(2).getTrouvee(), true); // 2 car c'est le deuxième piège ajouté dans la liste (parcours de haut en bas)
    }

    @Test
    public void testVerifierPresenceCase_CaseDeclencheurPresente(){
        this.laby.deplacerPerso(Labyrinthe.DROITE);
        this.laby.deplacerPerso(Labyrinthe.HAUT);
        assertEquals(this.laby.heros.getPv(), 10); // Le heros ne doit pas avoir perdu de points de vie sur une case Declencheur
        assertEquals(this.laby.cases.get(0).getTrouvee(), false);
        assertEquals(this.laby.cases.get(1).getTrouvee(), false);
        assertEquals(this.laby.cases.get(2).getTrouvee(), false);
        assertEquals(this.laby.cases.get(3).getTrouvee(), true);
    }
}
