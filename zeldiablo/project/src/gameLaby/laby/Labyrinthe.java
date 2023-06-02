package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
     * Les cases du labyrinthe
     */
    public ArrayList<Case> cases;


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
        this.cases = new ArrayList<Case>();


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
                        this.cases.add(new CasePiegee(colonne, numeroLigne));
                        break;

                    case CASEDECLENCHEUR:
                        this.cases.add(new CaseDeclencheur(colonne, numeroLigne));
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

            verifierPresenceCase(suivante[0], suivante[1], this.heros);


        }


    }

        public void verifierPresenceCase(int x, int y, Perso p){
            int indiceCase = this.cases.indexOf(new Case(x, y)); // new Case pour comparer des objets car indexOf compare deux objets en utilisant la méthode equals de Case que l'on a redéfini.
            if(indiceCase != -1) {
                Case casePresente = this.cases.get(indiceCase);
                if (casePresente instanceof CasePiegee){
                    // Si le piège n'a pas encore été effectif
                    if (p instanceof Monstre) {
                        this.monstre.changerPv(-1);
                    } else if (p instanceof Heros) {
                        this.heros.changerPv(-1);
                    }
                casePresente.setTrouvee();
                }
                else if (casePresente instanceof CaseDeclencheur){
                    // On vérifie si dans ce cas là, il y a une case déclencheur
                    casePresente.setTrouvee();
                    System.out.println("Le héros vient de subir un effet");
                }
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
     * deplace un monstre en fonction de l'action.
     * gere la collision avec les murs et les personnages
     */
    public void deplacerMonstre() {
        int[] courante = {this.monstre.x, this.monstre.y};
        int[] h=getSuivant(courante[0], courante[1], Labyrinthe.HAUT);
        int[] b=getSuivant(courante[0], courante[1], Labyrinthe.BAS);
        int[] d=getSuivant(courante[0], courante[1], Labyrinthe.DROITE);
        int[] g=getSuivant(courante[0], courante[1], Labyrinthe.GAUCHE);

        Tuple tH=new Tuple(h, Math.sqrt(Math.pow(this.heros.x - h[0], 2) + Math.pow(this.heros.y - h[1], 2)));
        Tuple tB=new Tuple(b, Math.sqrt(Math.pow(this.heros.x - b[0], 2) + Math.pow(this.heros.y - b[1], 2)));
        Tuple tD=new Tuple(d, Math.sqrt(Math.pow(this.heros.x - d[0], 2) + Math.pow(this.heros.y - d[1], 2)));
        Tuple tG=new Tuple(g, Math.sqrt(Math.pow(this.heros.x - g[0], 2) + Math.pow(this.heros.y - g[1], 2)));

        ArrayList<Tuple> classement = new ArrayList<Tuple>();
        classement.addAll(Arrays.asList(tH,tB,tD,tG));

        Collections.sort(classement, (t1, t2) -> Double.compare(t1.getDist(), t2.getDist()));

        for (int i = 0; i < classement.size(); i++) {
            int[] suivante = classement.get(i).getCoord();

            if (etreLibre(suivante[0], suivante[1])) {
                this.monstre.x = suivante[0];
                this.monstre.y = suivante[1];
                verifierPresenceCase(suivante[0], suivante[1], this.monstre);
                break;
            }
        }
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
