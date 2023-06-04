import gameLaby.laby.Heros;
import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.*;
import java.io.IOException;
import gameLaby.laby.Monstre;

public class TestAttaque {
    Labyrinthe laby;
    Labyrinthe labyUnMonstre;
    Labyrinthe laby4Monstres;

    @BeforeEach
    public void setup() throws IOException, AWTException {
        laby = new Labyrinthe("zeldiablo/project/labySimple/laby1.txt");
        labyUnMonstre = new Labyrinthe("zeldiablo/project/labySimple/test_attaqueUnMonstre.txt");
        laby4Monstres = new Labyrinthe("zeldiablo/project/labySimple/test_attaque4Monstres.txt");
    }

    @Test
    public void test_attaquerMonstre(){
        Monstre m = laby.monstres.get(0);
        int nbPvEspere = m.getPv()-1;
        laby.heros.attaquer(m);
        assertEquals(m.getPv(), nbPvEspere);
    }

    @Test
    public void test_attaquerMonstrePasACote(){
        Monstre m = laby.monstres.get(0);
        int nbPvEspere = m.getPv();
        laby.attaquerMonstresAutour();
        assertEquals(m.getPv(), nbPvEspere);
    }

    @Test
    public void test_attaquerMonstreACote(){
        Monstre m = labyUnMonstre.monstres.get(0);
        int nbPvEspere = m.getPv()-1;
        labyUnMonstre.attaquerMonstresAutour();
        assertEquals(m.getPv(), nbPvEspere);
    }

    @Test
    public void test_attaquer4MonstresACote(){

        int nbPvEspere = laby4Monstres.monstres.get(0).getPv()-1;
        laby4Monstres.attaquerMonstresAutour();
        assertEquals(laby4Monstres.monstres.get(0).getPv(), nbPvEspere);
        assertEquals(laby4Monstres.monstres.get(1).getPv(), nbPvEspere);
        assertEquals(laby4Monstres.monstres.get(2).getPv(), nbPvEspere);
        assertEquals(laby4Monstres.monstres.get(3).getPv(), nbPvEspere);
    }

    // Attaque d'un monstre sur le héros


    @Test
    public void test_attaquerHeros(){
        Monstre m = laby.monstres.get(0);
        Heros h = laby.heros;
        int nbPvEspere = h.getPv()-1;
        m.attaquer(h);
        assertEquals(h.getPv(), nbPvEspere);
    }

    @Test
    public void test_attaquerHerosPasACote(){
        Monstre m = laby.monstres.get(0);
        Heros h = laby.heros;
        int nbPvEspere = h.getPv();
        int coordonneesXMonstreEspere = m.getX();
        int coordonneesYMonstreEspere = m.getY() +1;
        laby.faireActionMonstres();
        assertEquals(h.getPv(), nbPvEspere);
        assertEquals(m.getX(), coordonneesXMonstreEspere);
        assertEquals(m.getY(), coordonneesYMonstreEspere);
    }

    @Test
    public void test_attaquerHerosACote(){
        Heros h = labyUnMonstre.heros;
        int nbPvEspere = h.getPv()-1;
        labyUnMonstre.faireActionMonstres();
        assertEquals(h.getPv(), nbPvEspere);
    }

    @Test
    public void test_attaquerHeros4MonstresACote(){
        Heros h = laby4Monstres.heros;
        int nbPvEspere = h.getPv()-4;
        laby4Monstres.faireActionMonstres();
        assertEquals(h.getPv(), nbPvEspere);

    }

}
