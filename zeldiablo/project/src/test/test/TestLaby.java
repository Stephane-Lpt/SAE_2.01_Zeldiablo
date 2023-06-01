package test.test;

import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static gameLaby.laby.Labyrinthe.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestLaby {

    Labyrinthe laby;
    Labyrinthe labyMurs;
    Labyrinthe labyMonstre;

    @BeforeEach
    public void beforeEach() throws IOException{
        laby = new Labyrinthe("labySimple/laby0.txt");
        labyMurs = new Labyrinthe("labySimple/labyTestMurs.txt");
        labyMonstre = new Labyrinthe("labySimple/laby1.txt");
    }

    @Test
    public void testGenererLaby_ok() throws IOException {
        //initialisation
        String genLaby = "XXXXXXXXXX" + "\n"
                       + "X.....XX.X" + "\n"
                       + "X.X......X" + "\n"
                       + "X......X.X" + "\n"
                       + "X........X" + "\n"
                       + "X..XXP...X" + "\n"
                       + "XXXXXXXXXX" + "\n";

        //méthode testée
        Labyrinthe laby = new Labyrinthe("labySimple/laby1.txt");
        String resToString = "";
        for (int y = 0; y < laby.getLengthY(); y++) {
            // affiche la ligne
            for (int x = 0; x < laby.getLength(); x++) {
                if (laby.getMur(x, y))
                    resToString += "X";
                else if(laby.heros.getX() == x && laby.heros.getY() == y)
                    resToString += "P";
                else
                    resToString += ".";
            }
            // saut de ligne
            resToString += "\n";
        }

        //vérification
        assertEquals(genLaby, resToString);
    }


    @Test
    public void testDeplacerPerso_haut() {
        //méthode testée
        this.laby.deplacerPerso(HAUT);

        //vérification
        int X = this.laby.heros.getX();
        int Y = this.laby.heros.getY();
        assertEquals(3, X);
        assertEquals(1, Y);
    }

    @Test
    public void testDeplacerPerso_bas() {
        //méthode testée
        this.laby.deplacerPerso(BAS);

        //vérification
        int X = this.laby.heros.getX();
        int Y = this.laby.heros.getY();
        assertEquals(3, X);
        assertEquals(3, Y);
    }

    @Test
    public void testDeplacerPerso_gauche() {
        //méthode testée
        this.laby.deplacerPerso(GAUCHE);

        //vérification
        int X = this.laby.heros.getX();
        int Y = this.laby.heros.getY();
        assertEquals(2, X);
        assertEquals(2, Y);
    }

    @Test
    public void testDeplacerPerso_droite() {
        //méthode testée
        this.laby.deplacerPerso(DROITE);

        //vérification
        int X = this.laby.heros.getX();
        int Y = this.laby.heros.getY();
        assertEquals(4, X);
        assertEquals(2, Y);
    }

    @Test
    public void testDeplacerPerso_murHaut() {
        //méthode testée
        this.labyMurs.deplacerPerso(HAUT);

        //vérification
        int X = this.labyMurs.heros.getX();
        int Y = this.labyMurs.heros.getY();
        assertEquals(1, X);
        assertEquals(1, Y);
    }

    @Test
    public void testDeplacerPerso_murBas() {
        //méthode testée
        this.labyMurs.deplacerPerso(BAS);

        //vérification
        int X = this.labyMurs.heros.getX();
        int Y = this.labyMurs.heros.getY();
        assertEquals(1, X);
        assertEquals(1, Y);
    }

    @Test
    public void testDeplacerPerso_murGauche() {
        //méthode testée
        this.labyMurs.deplacerPerso(GAUCHE);

        //vérification
        int X = this.labyMurs.heros.getX();
        int Y = this.labyMurs.heros.getY();
        assertEquals(1, X);
        assertEquals(1, Y);
    }

    @Test
    public void testDeplacerPerso_murDroit() {
        //méthode testée
        this.labyMurs.deplacerPerso(DROITE);

        //vérification
        int X = this.labyMurs.heros.getX();
        int Y = this.labyMurs.heros.getY();
        assertEquals(1, X);
        assertEquals(1, Y);
    }

    @Test
    public void testDeplacerPerso_dansMonstre() {
        //initialisation
        this.labyMonstre.deplacerPerso(HAUT);
        this.labyMonstre.deplacerPerso(HAUT);
        this.labyMonstre.deplacerPerso(GAUCHE);

        //méthode testée
        this.labyMonstre.deplacerPerso(HAUT);

        //vérification
        int X = this.labyMonstre.heros.getX();
        int Y = this.labyMonstre.heros.getY();
        assertEquals(4, X);
        assertEquals(3, Y);
    }
}
