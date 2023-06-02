package Graphes;

import gameLaby.laby.*;
import Graphes.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet de représenter et de manipuler des labyrinthes
 */
public class GrapheLabyrinthe implements Graphe{

    /**
     * représente un labyrinthe de façon simplifiée
     */
    private Labyrinthe labyrinthe;

    /**
     * Constructeur de labyrinthe
     *
     * @param labyrinthe Labyrinthe à initialiser
     */
    public GrapheLabyrinthe(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    /**
     * méthode qui permet d'obtenir tous les noeuds présents dans un labyrinthe
     *
     * @return une liste de tous les noeuds du labyrinthe
     */
    public List<String> listeNoeuds() {
        List<String> noeuds = new ArrayList<>();

        // Parcourir toutes les cases du labyrinthe
        for (int i = 0; i < this.labyrinthe.getLength(); i++) {
            for (int j = 0; j < this.labyrinthe.getLengthY(); j++) {
                if (!this.labyrinthe.getMur(i, j)) {
                    String nomNoeud = "(" + i + "," + j + ")";
                    noeuds.add(nomNoeud);
                }
            }
        }
        return noeuds;
    }


    /**
     * méthode qui permet de retourner la liste des arcs partant du nœud passé en paramètre
     *
     * @param n nœud pour lequel on veut connaitre la liste des arcs
     * @return une liste d'arc
     */
    public List<Arc> suivants(String n) {
        List<Arc> arcsSuivants = new ArrayList<>();

        // Extraire les coordonnées x et y du nom du nœud
        int x = Integer.parseInt(n.substring(1, n.indexOf(",")));
        int y = Integer.parseInt(n.substring(n.indexOf(",") + 1, n.length() - 1));

        // Vérifier les déplacements possibles à partir de la case (x, y)
        String[] actions = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};
        for (String action : actions) {
            int[] suivants = this.labyrinthe.getSuivant(x, y, action);
            int suivantX = suivants[0];
            int suivantY = suivants[1];

            if (suivantX >= 0 && suivantX < this.labyrinthe.getLength() &&
                    suivantY >= 0 && suivantY < this.labyrinthe.getLengthY() &&
                    !this.labyrinthe.getMur(suivantX, suivantY)) {
                String nomNoeudArrivee = "(" + suivantX + "," + suivantY + ")";
                arcsSuivants.add(new Arc(nomNoeudArrivee, 1));
            }
        }
        return arcsSuivants;
    }
}

