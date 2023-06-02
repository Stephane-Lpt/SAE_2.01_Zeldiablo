package gameLaby.laby;

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

        double largeurFenetre = canvas.getWidth();
        double hauteurFenetre = canvas.getHeight();

        // dessin fond
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, largeurFenetre, hauteurFenetre);


        // dessin raquette
        gc.setFill(Color.BLACK);
        double tailleCaseH = largeurFenetre/laby.getLaby().getLengthY();
        double tailleCaseL = hauteurFenetre/laby.getLaby().getLength();
        double origineLargeurC = 0;
        double origineHauteurC = 0;
        for(int i=0;i<laby.getLaby().getLengthY();i++){
            for(int j=0;j<laby.getLaby().getLength();j++){
                if(laby.getLaby().getMur(j,i)){
                    gc.fillRect(origineLargeurC,origineHauteurC, tailleCaseL, tailleCaseH);
                }
                origineLargeurC += tailleCaseL;
            }
            origineHauteurC += tailleCaseH;
            origineLargeurC = 0;
        }

        // Affihage des cases piégées
        for(CasePiegee c : laby.getLaby().casesPiegees){
            if(c.getTrouvee()){
                gc.setFill(Color.SADDLEBROWN);
                gc.fillRect(c.getX()*tailleCaseL, c.getY()*tailleCaseH, tailleCaseL, tailleCaseH); // Si la case a pour coordonnées 2,4 alors, le coin supérieur gauche du rectangle sera à 2 * la taille d'une case en largeur
            }
        }

        // Affichage du monstre
        gc.setFill(Color.PURPLE);
        gc.fillOval(laby.getLaby().monstre.x*tailleCaseL,laby.getLaby().monstre.y*tailleCaseH, tailleCaseL, tailleCaseH);

        // Affichage du perso en dernier pour qu'il soit au premier plan
        gc.setFill(Color.RED);
        gc.fillOval(laby.getLaby().heros.x*tailleCaseL, laby.getLaby().heros.y*tailleCaseH, tailleCaseL, tailleCaseH);
    }
}
