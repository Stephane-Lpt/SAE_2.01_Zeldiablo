import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static gameLaby.laby.Labyrinthe.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestFinJeu {

    Labyrinthe labyAmulette;

    @BeforeEach
    public void beforeEach() throws IOException {
        labyAmulette = new Labyrinthe("zeldiablo/project/labySimple/test_Amulette.txt");
    }

    @Test
    public void testRecupererAmulette(){
        //initialisation, on déplace le personnage sur l'amulette
        this.labyAmulette.deplacerPerso(HAUT);

        //récupération de l'amulette
        //vérification
        assertTrue(this.labyAmulette.amulette.getPossede());
    }

    @Test
    public void testFinJeu(){
        //initialisation, on déplace le personnage sur l'amulette
        this.labyAmulette.deplacerPerso(HAUT);

        //vérification, on déplace le personnage sur la case de sortie
        this.labyAmulette.deplacerPerso(BAS);
        assertTrue(this.labyAmulette.etreFini());
    }

    @Test
    public void testFinSansAmulette(){
        //initialisation, on déplace le personnage mais pas sur l'amulette
        this.labyAmulette.deplacerPerso(DROITE);

        //vérification, on déplace le personnage sur la case de sortie
        this.labyAmulette.deplacerPerso(GAUCHE);
        assertFalse(this.labyAmulette.etreFini());
    }

    @Test
    public void testRecupererAmuletteMonstre(){
        //initialisation, on déplace le perso sur un coté de l'amulette eton déplace le monstre sur l'amulette
        this.labyAmulette.deplacerPerso(DROITE);
        this.labyAmulette.deplacerPerso(HAUT);
        this.labyAmulette.deplacerMonstreIntelligent();

        //non récupération de l'amulette
        //vérification
        assertFalse(this.labyAmulette.amulette.getPossede());
    }

    @Test
    public void testAmuletteToujoursPresenteApresPassageMonstre(){
        //initialisation, on déplace le monstre sur l'amulette puis on l'enlève et enfin on déplace le personnage sur l'amulette
        this.labyAmulette.deplacerPerso(DROITE);
        this.labyAmulette.deplacerPerso(HAUT);
        this.labyAmulette.deplacerMonstreIntelligent();

        this.labyAmulette.heros.setX(1);
        this.labyAmulette.heros.setY(8);

        //récupération de l'amulette
        //vérification
        assertTrue(this.labyAmulette.amulette.getPossede());
    }

    @Test
    public void testSortieMonstre(){
        //initialisation, on déplace le personnage hors de la case de sortie
        this.labyAmulette.heros.setX(1);
        this.labyAmulette.heros.setY(1);

        //vérification, on déplace le monstre sur la case de sortie
        this.labyAmulette.monstre.setX(5);
        this.labyAmulette.monstre.setX(5);
        assertFalse(this.labyAmulette.etreFini());
    }

    @Test
    public void testHerosZeroPv(){
        //initialisation, on déplace le personnage sur l'amulette
        this.labyAmulette.heros.changerPv(0);

        //récupération de l'amulette
        //vérification
        assertTrue(this.labyAmulette.etreFini());
    }
}
