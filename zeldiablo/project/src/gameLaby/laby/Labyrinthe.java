package gameLaby.laby;

import Graphes.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
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

    public static final char FANTOME = 'F';

    public static final char TROLL = 'T';

    public static final char AMULETTE = 'A';

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
     * attribut des personnages
     */
    public Heros heros;


    /**
     * Liste de tous les monstres figurants sur le labyrinthe
     */
    public ArrayList<Monstre> monstres;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * Les cases du labyrinthe
     */
    public ArrayList<Case> cases;

    /**
     * L'amulette du labyrinthe
     */
    public Amulette amulette;

    public Entite depart;



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

        // On instancie les cases piégées et déclencheurs
        this.cases = new ArrayList<Case>();
        this.monstres = new ArrayList<Monstre>();


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
                        this.depart=new Entite(colonne, numeroLigne);
                        break;
                    case MONSTRE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute monstre dans liste
                        this.monstres.add(new Monstre(colonne, numeroLigne, 2));
                        break;
                    case FANTOME:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute fantome dans liste
                        this.monstres.add(new Fantome(colonne, numeroLigne, 2));
                        break;
                    case TROLL:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute troll dans liste
                        this.monstres.add(new Troll(colonne, numeroLigne, 3));
                        break;
                    case CASEPIEGEE:
                        this.cases.add(new CasePiegee(colonne, numeroLigne));
                        break;

                    case CASEDECLENCHEUR:
                        this.cases.add(new CaseDeclencheur(colonne, numeroLigne));
                        break;

                    case AMULETTE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.amulette=new Amulette(colonne, numeroLigne);
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
        if (etreLibre(suivante[0], suivante[1])){
            // on met a jour personnage
            this.heros.x = suivante[0];
            this.heros.y = suivante[1];

            verifierPresenceCase(suivante[0], suivante[1], this.heros);

            if((this.heros.x==this.amulette.x) && (this.heros.y==this.amulette.y) && !(this.amulette.getPossede())){
                this.amulette.setPossede();
            }

        }

    }

    /**
     * permet de vérifier s'il y a une case (Déclencheur, Piège, ...) présente à des coordonnées du labyrinthe ET de faire une action sur le personnage qui est sur la case
     * @param x numéro de colonne de la case dont on veut connaitre la présence
     * @param y numéro de ligne de la case dont on veut connaitre la présence
     * @param p le personnage qui est sur la case qui va subir une action selon le type de la case
     */
    public void verifierPresenceCase(int x, int y, Perso p){
        int indiceCase = this.cases.indexOf(new Case(x, y)); // new Case pour comparer des objets car indexOf compare deux objets en utilisant la méthode equals de Case que l'on a redéfini.
        if(indiceCase != -1) {
            Case casePresente = this.cases.get(indiceCase);
            if (casePresente instanceof CasePiegee){
                // Si le piège n'a pas encore été effectif
                if (p instanceof Monstre) {
                    p.changerPv(-1);
                } else if (p instanceof Heros) {
                    this.heros.changerPv(-1);
                }
                casePresente.setTrouvee();
            }
            else if (casePresente instanceof CaseDeclencheur){
                // On vérifie si dans ce cas là, il y a une case déclencheur
                casePresente.setTrouvee();
                System.out.println("Un personnage vient de subir un effet");
            }
        }
    }

    /**
     * Fini si le héros est mort ou si il atteint l'entrée avec l'amulette
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        if((this.heros.x==this.depart.x) && (this.heros.y==this.depart.y) && (this.amulette.getPossede())){
            System.out.println("Bien joué, tu as gagné");
            return true;
        } else if (this.heros.etreMort()) {
            System.out.println("Le héros est mort, tu as perdu");
            return true;
        } else{
            return false;
        }
    }

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
     * méthode qui permet de choisir l'action que le monstre affectue : attaquer ou se déplacer
     */
    public void faireActionMonstres(){
        for(Monstre m : this.monstres){
            if(!(verifierPresenceHerosCaseAdjacente(m.x,m.y))){
                deplacerMonstre(m);
            }
            else{
                m.attaquer(this.heros);
            }
        }
    }

    /**
     * deplace tous les monstres en fonction de l'action.
     * gere la collision avec les murs et les personnages
     */
    public void deplacerMonstre(Monstre m) {
        int[] courante = {m.x, m.y};
        int[] h = getSuivant(courante[0], courante[1], Labyrinthe.HAUT);
        int[] b = getSuivant(courante[0], courante[1], Labyrinthe.BAS);
        int[] d = getSuivant(courante[0], courante[1], Labyrinthe.DROITE);
        int[] g = getSuivant(courante[0], courante[1], Labyrinthe.GAUCHE);

        Tuple tH = new Tuple(h, Math.sqrt(Math.pow(this.heros.x - h[0], 2) + Math.pow(this.heros.y - h[1], 2)));
        Tuple tB = new Tuple(b, Math.sqrt(Math.pow(this.heros.x - b[0], 2) + Math.pow(this.heros.y - b[1], 2)));
        Tuple tD = new Tuple(d, Math.sqrt(Math.pow(this.heros.x - d[0], 2) + Math.pow(this.heros.y - d[1], 2)));
        Tuple tG = new Tuple(g, Math.sqrt(Math.pow(this.heros.x - g[0], 2) + Math.pow(this.heros.y - g[1], 2)));

        ArrayList<Tuple> classement = new ArrayList<Tuple>();
        classement.addAll(Arrays.asList(tH, tB, tD, tG));

        Collections.sort(classement, (t1, t2) -> Double.compare(t1.getDist(), t2.getDist()));

        for (int i = 0; i < classement.size(); i++) {
            int[] suivante = classement.get(i).getCoord();

            if(m instanceof Fantome && verifierPresenceMonstreCaseAdjacente(m.x, m.y).isEmpty()){
                m.x = suivante[0];
                m.y = suivante[1];
                verifierPresenceCase(suivante[0], suivante[1], m);
                break;
            } else if (etreLibre(suivante[0], suivante[1])) {
                m.x = suivante[0];
                m.y = suivante[1];
                verifierPresenceCase(suivante[0], suivante[1], m);
                if(m instanceof Troll){
                    if(((Troll) m).getAjouterVieTroll()){
                        m.changerPv(+1);
                        System.out.println("ajouter de la vie + 1");
                    }
                    else{
                        ((Troll) m).setAjouterVieTroll(true);
                    }

                }
                break;
            }

        }
    }

    /**
     * Méthode qui vérifie qu'une case est libre
     * @param x coordonnée en abscisses
     * @param y coordonnée et ordonnée
     * @return vrai si la case est libre et faux sinon
     */
    public boolean etreLibre(int x, int y){
        boolean noMonsters = true;
        for(Monstre m : this.monstres){
            if(m.etrePresent(x, y))
                noMonsters = false;
        }
        return ((!this.murs[x][y]) && !(this.heros.etrePresent(x, y)) && noMonsters);
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

    /**
     * méthode qui utilise l'algorithme de Dijkstra pour permettre aux monstres de se déplacer intelligemment
     *
     * @param m le monstre à faire déplacer
     */
    public void deplacerMonstreIntelligent(Monstre m){
        GrapheListe g = this.genererGraphe();

        //On utilise Dijkstra
        Valeur dij = (new Dijkstra()).resoudre(g, "("+m.x+","+m.y+")");
        //On utilise calculerChemin
        List<String> l = dij.calculerChemin("("+this.heros.x+","+this.heros.y+")");

        // on met a jour la position du monstre
        m.x = Integer.parseInt(l.get(1).substring(1, 2));
        m.y = Integer.parseInt(l.get(1).substring(3, 4));
    }

    /**
     * Verifie si un monstre est présent sur une case adjacente à celle aux coordonnées x,y
     * @param x le numéro de colonne de la case
     * @param y le numéro de ligne de la case
     * @return une liste de monstres qui sont présents sur les cases adjacentes
     */
    public ArrayList<Monstre> verifierPresenceMonstreCaseAdjacente(int x, int y){
        ArrayList<Monstre> listePersosProche = new ArrayList<Monstre>();
        if(!this.murs[x][y]){
            for(Monstre m : this.monstres){
                if(m.etrePresent(x+1, y) || m.etrePresent(x,y+1) || m.etrePresent(x-1,y) || m.etrePresent(x,y-1))
                    listePersosProche.add(m);
            }
        }

        return listePersosProche;
    }

    /**
     * Verifie la présence du héros sur une case adjacente à celle aux coordonnées x,y
     * @param x le numéro de colonne de la case
     * @param y le numéro de ligne de la case
     * @return true si le personnage est présent sur une case adjacente ou false sinon
     */
    public boolean verifierPresenceHerosCaseAdjacente(int x,int y){
        if(this.heros.etrePresent(x+1, y) || this.heros.etrePresent(x,y+1) || this.heros.etrePresent(x-1,y) || this.heros.etrePresent(x,y-1)){
            return true;
        }
        return false;
        // Cette méthode permet d'éviter de la duplication de la méthode etrePresent
    }

    /**
     * méthode qui permet de vérifier les monstres présents autour du personnage et de les attaquer
     */
    public void attaquerMonstresAutour(){
        for(Monstre m : this.verifierPresenceMonstreCaseAdjacente(this.heros.x, this.heros.y)){
            this.heros.attaquer(m);
            if(m instanceof Troll){
                ((Troll) m).setAjouterVieTroll(false);
            }
            if(m.etreMort()){
                this.monstres.remove(m);
            }
        }
        try {
            Thread.sleep(1000); // L'attaque a lieu et empêche le déplacement du joueur pendant 1 secondes
        } catch(InterruptedException e){
            e.getStackTrace();
        }
    }


}