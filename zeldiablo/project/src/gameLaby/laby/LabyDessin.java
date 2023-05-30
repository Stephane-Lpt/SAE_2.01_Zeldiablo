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
        for(int i=0;i<laby.getLaby().getLength()){
            for(int j=0;j<laby.getLaby().getLengthY()){
                if(laby.getLaby().getMur(i,j)){
                    gc.fillRect();
                }
            }
        }

        Raquette raquette = arkanoid.getRaquette();
        double px = raquette.getPx() - Raquette.RAQUETTE_TAILLE / 2;
        double py = raquette.getPy();
        gc.fillRect(px, py, Raquette.RAQUETTE_TAILLE, 10);

        // dessin balle
        gc.setFill(Color.RED);
        Balle balle = arkanoid.getBalle();
        double bx = balle.getPx();
        double by = balle.getPy();
        gc.fillOval(bx - 5, by - 5, 10, 10);
    }
}
