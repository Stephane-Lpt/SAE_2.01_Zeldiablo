package moteurJeu;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Clavier {

    /**
     * controle appuyes
     */
    public boolean haut, bas, gauche, droite, attaque;

    /**
     * String correspond à haut bas gauche droite attaque
     */
    public String h = "";
    public String b = "";
    public String g = "";

    public String d = "";

    public String a = "";

    public Clavier(String nomFich){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(nomFich));
            String ligne;
            ligne = reader.readLine();
            String[] res = ligne.split(":");
            h = res[1];
            ligne = reader.readLine();
            res = ligne.split(":");
            b = res[1];
            ligne = reader.readLine();
            res = ligne.split(":");
            g = res[1];
            ligne = reader.readLine();
            res = ligne.split(":");
            d = res[1];
            ligne = reader.readLine();
            res = ligne.split(":");
            a = res[1];
        }catch(FileNotFoundException e){
            h = "Z";
            b = "S";
            g = "Q";
            d = "D";
            a = "SPACE";
        } catch(IOException e){
            System.err.println(e);
        }
    }
    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void appuyerTouche(KeyEvent event) {
        String touche = event.getCode().toString();
        if(touche.equals(h)){
            this.haut = true;
        }else if(touche.equals(b)){
            this.bas = true;
        }else if(touche.equals(g)){
            this.gauche = true;
        }else if(touche.equals(d)){
            this.droite = true;
        }else if(touche.equals(a)){
            this.attaque = true;
        }
    }

    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void relacherTouche(KeyEvent event) {

        String touche = event.getCode().toString();
        if(touche.equals(h)){
            this.haut = false;
        }else if(touche.equals(b)){
            this.bas = false;
        }else if(touche.equals(g)){
            this.gauche = false;
        }else if(touche.equals(d)){
            this.droite = false;
        }else if(touche.equals(a)){
            this.attaque = false;
        }
    }
}
