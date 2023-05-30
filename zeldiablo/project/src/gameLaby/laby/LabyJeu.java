package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu{

    private Labyrinthe laby;

    public LabyJeu(){
        try {
            this.laby = new Labyrinthe("labySimple/laby1.txt");
        }
        catch(IOException e){
            System.err.println("Erreur de chargement du fichier pour charger le labyrinthe");
        }
    }

    /**
     * methode mise a jour du jeu
     * @param secondes temps ecoule depuis la derniere mise a jour
     * @param clavier objet contenant l'état du clavier'
     */
    public void update(double secondes, Clavier clavier){
        // deplace le personnage en fonction des touches
        if (clavier.droite) {
            this.laby.deplacerPerso(Labyrinthe.DROITE);
        }

        if (clavier.gauche) {
            this.laby.deplacerPerso(Labyrinthe.GAUCHE);
        }

        if (clavier.haut) {
            this.laby.deplacerPerso(Labyrinthe.HAUT);
        }

        if (clavier.bas) {
            this.laby.deplacerPerso(Labyrinthe.BAS);
        }
    }

    /**
     * initialisation du jeu
     */
    public void init(){

    }

    /**
     * verifie si le jeu est fini
     * @return booleen true si le jeu est fini
     */
    public boolean etreFini(){
        return false;
    }

    public Labyrinthe getLaby(){
        return this.laby;
    }
}