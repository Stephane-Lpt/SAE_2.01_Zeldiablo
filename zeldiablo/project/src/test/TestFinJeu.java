import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestFinJeu {

   /* Labyrinthe labyAmulette;

    @BeforeEach
    public void beforeEach() throws IOException {
        labyAmulette = new Labyrinthe("zeldiablo/project/labySimple/laby1.txt");
    }

    @Test
    public void testRecupererAmulette(){
        //initialisation, on déplace le personnage sur l'amulette
        this.labyAmulette.heros.setX(1);
        this.labyAmulette.heros.setY(8);

        //récupération de l'amulette
        //vérification
        assertTrue(possede());
    }

    @Test
    public void testFinJeu(){
        //initialisation, on déplace le personnage sur l'amulette
        this.labyAmulette.heros.setX(1);
        this.labyAmulette.heros.setY(8);

        //vérification, on déplace le personnage sur la case de sortie
        this.labyAmulette.heros.setX(5);
        this.labyAmulette.heros.setX(5);
        assertTrue(partieFinie?);
    }

    @Test
    public void testFinSansAmulette(){
        //initialisation, on déplace le personnage mais pas sur l'amulette
        this.labyAmulette.heros.setX(5);
        this.labyAmulette.heros.setY(7);

        //vérification, on déplace le personnage sur la case de sortie
        this.labyAmulette.heros.setX(5);
        this.labyAmulette.heros.setY(5);
        assertFalse(partieFinie?);
    }

    @Test
    public void testRecupererAmuletteMonstre(){
        //initialisation, on déplace le monstre sur l'amulette
        this.labyAmulette.monstre.setX(1);
        this.labyAmulette.monstre.setY(8);

        //non récupération de l'amulette
        //vérification
        assertFalse(possede());
    }

    @Test
    public void testAmuletteToujoursPresenteApresPassageMonstre(){
        //initialisation, on déplace le monstre sur l'amulette puis on l'enlève et enfin on déplace le personnage sur l'amulette
        this.labyAmulette.monstre.setX(1);
        this.labyAmulette.monstre.setY(8);

        this.labyAmulette.monstre.setX(1);
        this.labyAmulette.monstre.setY(1);

        this.labyAmulette.heros.setX(1);
        this.labyAmulette.heros.setY(8);

        //récupération de l'amulette
        //vérification
        assertTrue(possede());
    }

    @Test
    public void testSortieMonstre(){
        //initialisation, on déplace le personnage hors de la case de sortie
        this.labyAmulette.heros.setX(1);
        this.labyAmulette.heros.setY(1);

        //vérification, on déplace le monstre sur la case de sortie
        this.labyAmulette.monstre.setX(5);
        this.labyAmulette.monstre.setX(5);
        assertFalse(partieFinie?);
    }*/
}