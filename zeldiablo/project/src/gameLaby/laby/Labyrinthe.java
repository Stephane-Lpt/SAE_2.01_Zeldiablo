package gameLaby.laby;

import Graphes.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';

    public static final char MONSTRE = 'M';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";

    /**
     * attribut du personnage
     */
    public Heros heros;
    public Monstre monstre;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    public static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.heros = null;
        this.monstre=null;

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.heros = new Heros(colonne, numeroLigne);
                        break;
                    case MONSTRE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.monstre = new Monstre(colonne, numeroLigne);
                        break;

                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
    }


    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs et les personnages
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        // case courante
        int[] courante = {this.heros.x, this.heros.y};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        if (!(this.murs[suivante[0]][suivante[1]]) && (suivante[0]!=this.monstre.getX() || suivante[1]!=this.monstre.getY())) {
            // on met a jour personnage
            this.heros.x = suivante[0];
            this.heros.y = suivante[1];
        }
    }

    /**
     * deplace un monstre en fonction de l'action.
     * gere la collision avec les murs et les personnages
     *
     * @param action une des actions possibles
     */
    public void deplacerMonstre(String action) {
        // case courante
        int[] courante = {this.monstre.x, this.monstre.y};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        if (!this.murs[suivante[0]][suivante[1]] && (suivante[0]!=this.heros.getX() || suivante[1]!=this.heros.getY())) {
            // on met a jour personnage
            this.monstre.x = suivante[0];
            this.monstre.y = suivante[1];
        }
    }


    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    /**
     * Méthode qui vérifie qu'une case est libre
     * @param x coordonnée en abscisses
     * @param y coordonnée et ordonnée
     * @return vrai si la case est libre et faux sinon
     */
    public boolean etreLibre(int x, int y){
        return ((!this.murs[x][y]) && !(this.heros.etrePresent(x, y)) && !(this.monstre.etrePresent(x, y)));
    }

    /**
     * Méthode qui permet de générer un graphe à partir d'un labyrinthe
     *
     * @return le graphe
     */
    public GrapheListe genererGraphe() {
        GrapheListe graphe = new GrapheListe();

        // Parcourir toutes les cases du labyrinthe
        for (int i = 0; i < this.murs.length; i++) {
            for (int j = 0; j < this.murs[i].length; j++) {
                String nomNoeud = "(" + i + "," + j + ")";

                // Vérifier les déplacements possibles pour chaque case
                if (!this.murs[i][j]) {
                    // Vérifier le déplacement vers le haut
                    if (j > 0 && !this.murs[i][j - 1]) {
                        String nomNoeudArrivee = "(" + i + "," + (j - 1) + ")";
                        graphe.ajouterArc(nomNoeud, nomNoeudArrivee, 1); // Ajouter un arc avec un coût de 1
                    }

                    // Vérifier le déplacement vers le bas
                    if (j < this.murs[i].length - 1 && !this.murs[i][j + 1]) {
                        String nomNoeudArrivee = "(" + i + "," + (j + 1) + ")";
                        graphe.ajouterArc(nomNoeud, nomNoeudArrivee, 1); // Ajouter un arc avec un coût de 1
                    }

                    // Vérifier le déplacement vers la gauche
                    if (i > 0 && !this.murs[i - 1][j]) {
                        String nomNoeudArrivee = "(" + (i - 1) + "," + j + ")";
                        graphe.ajouterArc(nomNoeud, nomNoeudArrivee, 1); // Ajouter un arc avec un coût de 1
                    }

                    // Vérifier le déplacement vers la droite
                    if (i < this.murs.length - 1 && !this.murs[i + 1][j]) {
                        String nomNoeudArrivee = "(" + (i + 1) + "," + j + ")";
                        graphe.ajouterArc(nomNoeud, nomNoeudArrivee, 1); // Ajouter un arc avec un coût de 1
                    }
                }
            }
        }

        return graphe;
    }

    public void deplacerMonstreIntelligent(){

        GrapheListe g = this.genererGraphe();

        //On utilise Dijkstra
        Valeur dij = (new Dijkstra()).resoudre(g, "("+this.monstre.x+","+this.monstre.y+")");
        //On utilise calculerChemin
        List<String> l = dij.calculerChemin("("+this.heros.x+","+this.heros.y+")");

        // on met a jour la position du monstre
        this.monstre.x = Integer.parseInt(l.get(1).substring(1, 2));
        this.monstre.y = Integer.parseInt(l.get(1).substring(3, 4));
    }
}
