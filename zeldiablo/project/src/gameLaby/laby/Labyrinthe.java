package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public static final char CASEPIEGEE = 'p';

    public static final char CASEDECLENCHEUR = 'd';

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
     * Les cases piégées du labyrinthe
     */
    public ArrayList<CasePiegee> casesPiegees;

    /**
     * Les cases déclencheurs du labyrinthe
     */
    public ArrayList<CaseDeclencheur> casesDeclencheurs;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
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

        // On instancie les cases piégées et déclencheurs
        this.casesPiegees = new ArrayList<CasePiegee>();
        this.casesDeclencheurs = new ArrayList<CaseDeclencheur>();


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
                        this.heros = new Heros(colonne, numeroLigne, 10);
                        break;
                    case MONSTRE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.monstre = new Monstre(colonne, numeroLigne, 10);
                        break;
                    case CASEPIEGEE:
                        this.casesPiegees.add(new CasePiegee(colonne, numeroLigne));
                        break;

                    case CASEDECLENCHEUR:
                        this.casesDeclencheurs.add(new CaseDeclencheur(colonne, numeroLigne));
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

            verifierCasePiegee(suivante[0], suivante[1], this.heros);


            // On vérifie si dans ce cas là, il y a une case déclencheur
            int caseD = this.casesDeclencheurs.indexOf(new Case(suivante[0], suivante[1]));
            if(caseD != -1){
                this.heros.changerPv(-1);
                this.casesDeclencheurs.get(caseD).setTrouvee();
                System.out.println("Le héros vient de subir un effet");
            }

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

            // Ici on met ce qu'il se passe pour vérifier si aux coordonnées suivantes il y a une case piégée
            verifierCasePiegee(suivante[0], suivante[1], this.monstre);

        }
    }


        public void verifierCasePiegee(int x, int y, Perso p){
            int caseP = this.casesPiegees.indexOf(new Case(x, y));
            if(caseP != -1){
                // Si le piège n'a pas encore été effectif
                if(p instanceof Monstre){
                    this.monstre.changerPv(-1);
                }else if(p instanceof Heros){
                    this.heros.changerPv(-1);
                }
                this.casesPiegees.get(caseP).setTrouvee();
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
     * @param x coordonée en abscisses
     * @param y coordonéé et ordonée
     * @return vrai si la case est libre et faux sinon
     */
    public boolean etreLibre(int x, int y){
        return ((!this.murs[x][y]) && !(this.heros.etrePresent(x, y)) && !(this.monstre.etrePresent(x, y)));
    }
}
