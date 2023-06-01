package gameLaby.laby;

import gameArkanoid.Balle;
import gameArkanoid.Raquette;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {
    /**
     * affiche l'etat du jeu dans le canvas passe en parametre
     *
     * @param jeu jeu a afficher
     * @param canvas canvas dans lequel dessiner l'etat du jeu
     */
    public void dessinerJeu(Jeu jeu, Canvas canvas){
        LabyJeu laby = (LabyJeu) jeu;

        // recupere un pinceau pour dessiner
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());


        // dessin raquette
        gc.setFill(Color.BLACK);
        double tailleCaseH = canvas.getWidth()/laby.getLaby().getLengthY();
        double tailleCaseL = canvas.getHeight()/laby.getLaby().getLength();
        double origineLargeurC = 0;
        double origineHauteurC = 0;
        for(int i=0;i<laby.getLaby().getLengthY();i++){
            for(int j=0;j<laby.getLaby().getLength();j++){
                if(laby.getLaby().getMur(j,i)){
                    gc.fillRect(origineLargeurC,origineHauteurC, tailleCaseL, tailleCaseH);
                }
                else if(laby.getLaby().heros.x == j && laby.getLaby().heros.y == i){
                    gc.setFill(Color.RED);
                    gc.fillOval(origineLargeurC,origineHauteurC, tailleCaseL, tailleCaseH);
                    gc.setFill(Color.BLACK);
                }
                else if(laby.getLaby().monstre.x == j && laby.getLaby().monstre.y == i){
                    gc.setFill(Color.PURPLE);
                    gc.fillOval(origineLargeurC,origineHauteurC, tailleCaseL, tailleCaseH);
                    gc.setFill(Color.BLACK);
                }
                origineLargeurC += tailleCaseL;
            }
            origineHauteurC += tailleCaseH;
            origineLargeurC = 0;
        }
    }
}
