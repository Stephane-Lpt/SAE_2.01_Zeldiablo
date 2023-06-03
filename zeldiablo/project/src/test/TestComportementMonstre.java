import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestComportementMonstre {

    Labyrinthe labyMonstreBas;
    Labyrinthe labyMonstreHaut;
    Labyrinthe labyMonstreDroite;
    Labyrinthe labyMonstreGauche;
    Labyrinthe labyMonstreChemin;
    Labyrinthe labyMonstreIntelligent;

    @BeforeEach
    public void beforeEach() throws IOException{
        labyMonstreBas = new Labyrinthe("zeldiablo/project/labySimple/laby1.txt");
        labyMonstreHaut = new Labyrinthe("zeldiablo/project/labySimple/test_monstre_haut.txt");
        labyMonstreDroite = new Labyrinthe("zeldiablo/project/labySimple/test_monstre_droite.txt");
        labyMonstreGauche = new Labyrinthe("zeldiablo/project/labySimple/test_monstre_gauche.txt");
        labyMonstreChemin = new Labyrinthe("zeldiablo/project/labySimple/test_monstre_chemin.txt");
        labyMonstreIntelligent = new Labyrinthe("zeldiablo/project/labySimple/test_monstre_intelligent.txt");
    }

    @Test
    public void testDeplacerMonstre_Bas(){
        //méthode testée
        this.labyMonstreBas.deplacerMonstre();

        //vérification
        int x = this.labyMonstreBas.monstre.getX();
        int y = this.labyMonstreBas.monstre.getY();
        assertEquals(4, x);
        assertEquals(3, y);
    }

    @Test
    public void testDeplacerMonstre_Haut(){
        //méthode testée
        this.labyMonstreHaut.deplacerMonstre();

        //vérification
        int x = this.labyMonstreHaut.monstre.getX();
        int y = this.labyMonstreHaut.monstre.getY();
        assertEquals(4, x);
        assertEquals(3, y);
    }

    @Test
    public void testDeplacerMonstre_Droite(){
        //méthode testée
        this.labyMonstreDroite.deplacerMonstre();

        //vérification
        int x = this.labyMonstreDroite.monstre.getX();
        int y = this.labyMonstreDroite.monstre.getY();
        assertEquals(5, x);
        assertEquals(4, y);
    }

    @Test
    public void testDeplacerMonstre_Gauche(){
        //méthode testée
        this.labyMonstreGauche.deplacerMonstre();

        //vérification
        int x = this.labyMonstreGauche.monstre.getX();
        int y = this.labyMonstreGauche.monstre.getY();
        assertEquals(3, x);
        assertEquals(4, y);
    }

    @Test
    public void testDeplacerMonstre_Chemin() {
        //méthode testée, 1er appel
        this.labyMonstreChemin.deplacerMonstre();

        //1ere vérification
        int x = this.labyMonstreChemin.monstre.getX();
        int y = this.labyMonstreChemin.monstre.getY();
        assertEquals(1, x);
        assertEquals(2, y);

        //méthode testée, 2eme appel
        this.labyMonstreChemin.deplacerMonstre();

        //2eme vérification
        //Le monstre ne choisi pas le bon chemin et reste bloqué dans un cul de sac
        x = this.labyMonstreChemin.monstre.getX();
        y = this.labyMonstreChemin.monstre.getY();
        assertEquals(1, x);
        assertEquals(1, y);
    }

    @Test
    public void testMonstreIntelligent(){
        //méthode testée, 1er appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //1er vérification
        int x = this.labyMonstreIntelligent.monstre.getX();
        int y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(1, x);
        assertEquals(3, y);

        //méthode testée, 2eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //2eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(1, x);
        assertEquals(2, y);

        //méthode testée, 3eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //3eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(1, x);
        assertEquals(1, y);

        //méthode testée, 4eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //4eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(2, x);
        assertEquals(1, y);

        //méthode testée, 5eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //5eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(3, x);
        assertEquals(1, y);

        //méthode testée, 6eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //6eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(4, x);
        assertEquals(1, y);

        //méthode testée, 7eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //7eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(5, x);
        assertEquals(1, y);

        //méthode testée, 8eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //8eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(6, x);
        assertEquals(1, y);

        //méthode testée, 9eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //9eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(6, x);
        assertEquals(2, y);

        //méthode testée, 10eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //10eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(6, x);
        assertEquals(3, y);

        //méthode testée, 11eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent();

        //11eme vérification
        x = this.labyMonstreIntelligent.monstre.getX();
        y = this.labyMonstreIntelligent.monstre.getY();
        assertEquals(7, x);
        assertEquals(3, y);
    }

}
