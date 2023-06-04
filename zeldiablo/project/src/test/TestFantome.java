import gameLaby.laby.Fantome;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Monstre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestFantome {
    Labyrinthe labyFantome;
    @BeforeEach
    public void beforeEach() throws IOException{
        labyFantome = new Labyrinthe("zeldiablo/project/labySimple/test_Fantome.txt");
    }

    @Test
    public void test_DeplacerMonstreDansMur(){
        //méthode testée
        this.labyFantome.faireActionMonstres();

        //vérification
        int x = this.labyFantome.monstres.get(0).getX();
        int y = this.labyFantome.monstres.get(0).getY();
        assertEquals(5, x);
        assertEquals(3, y);
    }

    @Test
    public void test_FantomeAttaque(){
        //Fais avancer le fantome à côté du héros
        this.labyFantome.faireActionMonstres();
        Monstre m = labyFantome.monstres.get(0);
        int nbPvEspere = labyFantome.heros.getPv()-1;
        //Le fantome doit attaquer le héros
        this.labyFantome.faireActionMonstres();
        assertEquals(labyFantome.heros.getPv(), nbPvEspere);
    }

    @Test
    public void test_SubirAttaque(){
        //Fais avancer le fantome à côté du héros
        this.labyFantome.faireActionMonstres();
        Monstre m = labyFantome.monstres.get(0);
        int nbPvEspere = m.getPv()-1;
        labyFantome.heros.attaquer(m);
        assertEquals(m.getPv(), nbPvEspere);
    }
}
