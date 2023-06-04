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
        this.labyMonstreBas.faireActionMonstres();

        //vérification
        int x = this.labyMonstreBas.monstres.get(0).getX();
        int y = this.labyMonstreBas.monstres.get(0).getY();
        assertEquals(4, x);
        assertEquals(3, y);
    }

    @Test
    public void testDeplacerMonstre_Haut(){
        //méthode testée
        this.labyMonstreHaut.faireActionMonstres();

        //vérification
        int x = this.labyMonstreHaut.monstres.get(0).getX();
        int y = this.labyMonstreHaut.monstres.get(0).getY();
        assertEquals(4, x);
        assertEquals(3, y);
    }

    @Test
    public void testDeplacerMonstre_Droite(){
        //méthode testée
        this.labyMonstreDroite.faireActionMonstres();

        //vérification
        int x = this.labyMonstreDroite.monstres.get(0).getX();
        int y = this.labyMonstreDroite.monstres.get(0).getY();
        assertEquals(5, x);
        assertEquals(4, y);
    }

    @Test
    public void testDeplacerMonstre_Gauche(){
        //méthode testée
        this.labyMonstreGauche.faireActionMonstres();

        //vérification
        int x = this.labyMonstreGauche.monstres.get(0).getX();
        int y = this.labyMonstreGauche.monstres.get(0).getY();
        assertEquals(3, x);
        assertEquals(4, y);
    }

    @Test
    public void testDeplacerMonstre_Chemin() {
        //méthode testée, 1er appel
        this.labyMonstreChemin.faireActionMonstres();

        //1ere vérification
        int x = this.labyMonstreChemin.monstres.get(0).getX();
        int y = this.labyMonstreChemin.monstres.get(0).getY();
        assertEquals(1, x);
        assertEquals(2, y);

        //méthode testée, 2eme appel
        this.labyMonstreChemin.faireActionMonstres();

        //2eme vérification
        //Le monstre ne choisi pas le bon chemin et reste bloqué dans un cul de sac
        x = this.labyMonstreChemin.monstres.get(0).getX();
        y = this.labyMonstreChemin.monstres.get(0).getY();
        assertEquals(1, x);
        assertEquals(1, y);
    }

    @Test
    public void testMonstreIntelligent(){
        //méthode testée, 1er appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //1er vérification
        int x = this.labyMonstreIntelligent.monstres.get(0).getX();
        int y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(1, x);
        assertEquals(3, y);

        //méthode testée, 2eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //2eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(1, x);
        assertEquals(2, y);

        //méthode testée, 3eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //3eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(1, x);
        assertEquals(1, y);

        //méthode testée, 4eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //4eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(2, x);
        assertEquals(1, y);

        //méthode testée, 5eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //5eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(3, x);
        assertEquals(1, y);

        //méthode testée, 6eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //6eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(4, x);
        assertEquals(1, y);

        //méthode testée, 7eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //7eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(5, x);
        assertEquals(1, y);

        //méthode testée, 8eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //8eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(6, x);
        assertEquals(1, y);

        //méthode testée, 9eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //9eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(6, x);
        assertEquals(2, y);

        //méthode testée, 10eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //10eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(6, x);
        assertEquals(3, y);

        //méthode testée, 11eme appel
        this.labyMonstreIntelligent.deplacerMonstreIntelligent(this.labyMonstreIntelligent.monstres.get(0));

        //11eme vérification
        x = this.labyMonstreIntelligent.monstres.get(0).getX();
        y = this.labyMonstreIntelligent.monstres.get(0).getY();
        assertEquals(7, x);
        assertEquals(3, y);
    }

}
