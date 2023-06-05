import gameLaby.laby.Troll;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Monstre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static gameLaby.laby.Labyrinthe.DROITE;
import static org.junit.jupiter.api.Assertions.*;

public class TestTroll {

    Labyrinthe labyTroll;
    @BeforeEach
    public void beforeEach() throws IOException{
        labyTroll = new Labyrinthe("zeldiablo/project/labySimple/test_Troll.txt");
    }

    @Test
    public void testRegagnerVie(){
        //initialisation
        int vieTroll = this.labyTroll.monstres.get(0).getPv();

        //méthode testée
        this.labyTroll.deplacerMonstre(this.labyTroll.monstres.get(0));

        //vérification
        assertEquals(vieTroll+1, this.labyTroll.monstres.get(0).getPv());
    }

    @Test
    public void testNonRegagnerVie(){
        //initialisation
        this.labyTroll.deplacerMonstre(this.labyTroll.monstres.get(0));

        //méthode testée
        this.labyTroll.attaquerMonstresAutour();
        int vieTroll = this.labyTroll.monstres.get(0).getPv();
        this.labyTroll.deplacerPerso(DROITE);
        this.labyTroll.deplacerMonstre(this.labyTroll.monstres.get(0));

        //vérification
        assertEquals(vieTroll, this.labyTroll.monstres.get(0).getPv());
    }

    @Test
    public void test_TrollAttaque(){
        //méthode testée
        this.labyTroll.deplacerMonstre(this.labyTroll.monstres.get(0));

        //vérification
        int nbPvEspere = labyTroll.heros.getPv()-1;
        this.labyTroll.faireActionMonstres();
        assertEquals(nbPvEspere, labyTroll.heros.getPv());
    }
}
