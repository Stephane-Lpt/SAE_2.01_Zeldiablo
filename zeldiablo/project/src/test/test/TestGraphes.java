package test.test;

import Graphes.Dijkstra;
import Graphes.GrapheListe;
import Graphes.Valeur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGraphes {

    @Test
    public void testAjouterArc_ok(){
        //création du graphe
        GrapheListe g = new GrapheListe();

        //premiere vérification
        int resTest = g.listeNoeuds().size();
        assertEquals(0, resTest);

        //méthode testée
        g.ajouterArc("A", "B", 30);
        int resTest2 = g.listeNoeuds().size();

        //vérification
        assertEquals(2, resTest2);
    }

    @Test
    public void testAjouterNoeud_ok(){
        //création du graphe
        GrapheListe g = new GrapheListe();

        //méthode testée
        g.ajouterArc("A", "B", 30);
        int resTest = g.listeNoeuds().size();

        //vérification
        assertEquals(2, resTest);
    }

    @Test
    public void testAjouterNoeud_Nom_ok(){
        //création du graphe
        GrapheListe g = new GrapheListe();

        //méthode testée
        g.ajouterArc("A", "B", 30);

        //vérifications
        assertEquals("A", g.listeNoeuds().get(0));
        assertEquals("B", g.listeNoeuds().get(1));
    }

    @Test
    public void testAjouterArcDejaPresent(){
        //création du graphe
        GrapheListe g = new GrapheListe();

        //méthodes testées
        g.ajouterArc("A", "B", 30);
        g.ajouterArc("A", "B", 20);
        String resTest = g.toString();
        String resultat = "A -> B(30) " + "\n";

        //vérification
        assertEquals(resultat, resTest);
    }
    
    @Test
    public void testToString_ok(){
        //création du graphe
        GrapheListe g = new GrapheListe();

        //méthode testée
        g.ajouterArc("A", "B", 30);
        g.ajouterArc("B", "C", 20);
        g.ajouterArc("C", "A", 10);
        String resTest = g.toString();
        String resultatAttendu = "A -> B(30) " + "\n"
                               + "B -> C(20) " + "\n"
                               + "C -> A(10) " + "\n";

        //vérification
        assertEquals(resultatAttendu, resTest);
    }

    @Test
    public void testToGraphViz_ok(){
        //création du graphe
        GrapheListe g = new GrapheListe();

        //méthode testée
        g.ajouterArc("A", "B", 30);
        g.ajouterArc("B", "C", 20);
        g.ajouterArc("C", "A", 10);
        String resTest = g.toGraphviz();
        String resultatAttendu = "digraph G {" + "\n"
                               + "A -> B [label = 30]" + "\n"
                               + "B -> C [label = 20]" + "\n"
                               + "C -> A [label = 10]" + "\n}";

        //vérification
        assertEquals(resultatAttendu, resTest);

    }



    @Test
    public void testDijkstra_ValeurOk(){
        //initialisation des données
        GrapheListe g = new GrapheListe();
        Dijkstra b = new Dijkstra();
        g.ajouterArc("A", "B", 30);
        g.ajouterArc("B", "C", 20);
        g.ajouterArc("C", "A", 10);

        //méthode testée
        Valeur res = b.resoudre(g, "A");
        String resTest = res.toString();
        String resultatAttendu = "A ->  V:0.0 p:null" + "\n"
                               + "B ->  V:30.0 p:A" + "\n"
                               + "C ->  V:50.0 p:B" + "\n";

        //vérification
        assertEquals(resultatAttendu, resTest);
    }

    @Test
    public void testDijkstra_ValeurPasBon(){
        //initialisation des données
        GrapheListe g = new GrapheListe();
        Dijkstra b = new Dijkstra();
        g.ajouterArc("A", "B", 30);
        g.ajouterArc("B", "C", 20);
        g.ajouterArc("C", "A", 10);

        //méthode testée
        Valeur res = b.resoudre(g, "Z");

        //vérification
        assertEquals(null, res);
    }


    @Test
    public void testCalculerChemin_Ok(){
        //initilalisation des valeurs
        Valeur gr = new Valeur();
        gr.setValeur("A", 0);
        gr.setValeur("B", 30);
        gr.setValeur("C", 20);
        gr.setValeur("D", 21);
        gr.setValeur("E", 1);

        gr.setParent("B", "A");
        gr.setParent("C", "A");
        gr.setParent("D", "A");
        gr.setParent("D", "C");
        gr.setParent("D", "B");
        gr.setParent("D", "A");
        gr.setParent("E", "B");
        gr.setParent("E", "D");

        //méthode testée
        List<String> l=gr.calculerChemin("E");

        ArrayList<String> lAttendu=new ArrayList<String>();
        lAttendu.add("A");
        lAttendu.add("D");
        lAttendu.add("E");

        //vérification
        assertEquals(lAttendu, l);
    }
}
