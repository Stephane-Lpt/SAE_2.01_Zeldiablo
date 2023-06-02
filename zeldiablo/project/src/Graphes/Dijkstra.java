package Graphes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui utilise l'algorithme de Dijkstra pour connaitre les distances et les parents de chaque nœud
 */
public class Dijkstra{

    /*
    Entrées :
        G un graphe orienté avec une pondération (poids) positive des arcs
        A un sommet (départ) de G

    Début
        Q <- {} // utilisation d'une liste de noeuds à traiter
        Pour chaque sommet v de G faire
            v.distance <- Infini
            v.parent <- Indéfini
        Q <- Q U {v} // ajouter le sommet v à la liste Q
        Fin Pour

        A.distance <- 0
        Tant que Q est un ensemble non vide faire
            u <- un sommet de Q telle que u.distance est minimale
            Q <- Q \ {u} // enlever le sommet u de la liste Q
            Pour chaque sommet v de Q tel que l'arc (u,v) existe faire
                D <- u.distance + poids(u,v)
                Si D < v.distance
                    Alors v.distance <- D
                        v.parent <- u
                Fin Si
            Fin Pour
        Fin Tant que
    Fin
    */

    /**
     * Méthode qui utilise l'algorithme de Dijkstra pour trouver le chemin le plus court entre 2 sommets
     *
     * @param g un graphe orienté avec pondération (poids) positive sur les arcs
     * @param depart un sommet (départ) de G
     * @return un objet Valeur contenant les distances minimales et les parents de chaque nœud du graphe
     */
    public Valeur resoudre(Graphe g, String depart){

        //test de l'existence de noeud de départ
        if(g.listeNoeuds().contains(depart)){

            Valeur v = new Valeur();

            //on crée une liste vide pour stocker les sommets à traiter
            List<String> Q = new ArrayList<String>();

            //on attribue une distance initiale infinie à chaque sommet du graphe G qu'on ajoute à la liste Q
            for(int i = 0; i < g.listeNoeuds().size(); i++){
                v.setValeur(g.listeNoeuds().get(i), Double.MAX_VALUE);
                v.setParent(g.listeNoeuds().get(i), null);
                //ajout du nom de la valeur dans la liste Q
                Q.add(g.listeNoeuds().get(i));
            }

            //on attribue une distance de 0 au sommet de départ
            v.setValeur(depart, 0);

            //tant que Q n'est pas vide
            while(Q.size() > 0){

                //déclaration de la variable qui va contenir le nom du plus petit sommet
                String plusPetitSommet = null;
                //initialisation de la distance minimale à Infini
                //(Double.MAX_VALUE correspond à la valeur maximale que peut prendre un double en java)
                double distanceMinimale = Double.MAX_VALUE;

                //on recherche la plus petite distance dans la liste de tous les sommets du graphe
                for(String s: Q){
                    double distance = v.getValeur(s);
                    //si la distance du sommet trouvé est plus petite que la distance minimale actuelle, alors on la met à jour
                    if(distance < distanceMinimale){
                        distanceMinimale = distance;
                        plusPetitSommet = s;
                    }
                }

                //on retire le plus petit sommet de la liste
                Q.remove(plusPetitSommet);

                //Pour chaque sommet (minimal à un moment) de Q.
                for(Arc arc: g.suivants(plusPetitSommet)){
                    //arc de destination
                    String dest = arc.getDest();
                    //poids de l'arc
                    double poids = arc.getCout();
                    //valeur minimale pour aller au plus petit sommet
                    double distanceDepart = v.getValeur(plusPetitSommet);

                    //calcul de la distance entre le départ et la destination
                    double distanceDestination = distanceDepart + poids;

                    //si la distance entre le départ et la destination est plus petit que la distance actuelle, on met à jour les valeurs
                    if(distanceDestination < v.getValeur(dest)){
                        v.setValeur(dest, distanceDestination);
                        v.setParent(dest, plusPetitSommet);
                    }
                }

                //si on veut afficher le contenu de l'objet valeur après chaque itération
                //System.out.println(v);
            }
            return v;
        }else{
            return null;
        }
    }
}
