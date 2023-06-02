package Graphes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet de représenter les données associées à un graphe.
 */
public class GrapheListe implements Graphe {
    /**
     * Liste contenant les noms des objets nœuds stockés
     */
    private List<String> ensNom;
    /**
     * Liste d'objet nœud permettant de stocker les arcs
     */
    private ArrayList<Noeud> ensNoeuds;


    /**
     * Constructeur de GrapheListe, il initialise les deux listes (vides)
     */
    public GrapheListe(){
        this.ensNom=new ArrayList<String>();
        this.ensNoeuds=new ArrayList<Noeud>();
    }

    /**
     * méthode qui permet de retourner la liste des arcs partant du nœud passé en paramètre
     *
     * @param n nœud pour lequel on veut connaitre la liste des arcs
     * @return la liste des arcs du nœud passé en paramètre
     */
    public List<Arc> suivants(String n){
        Noeud noeud = null;
        for(Noeud elt: this.ensNoeuds) {
            if (elt.equals(new Noeud(n))){
                noeud = elt;
            }
        }
        return noeud.getArc();
    }

    /**
     * Méthode qui permet d'ajouter un arc dans un graphe
     *
     * @param depart départ de l'arc
     * @param destination destination de l'arc
     * @param cout coût (poids) de l'arc
     */
    public void ajouterArc(String depart, String destination, double cout){
        // on regarde si les noeuds de départ et d'arrivé existent
        if(this.ensNom.contains(depart) && this.ensNom.contains(destination)){
            // On parcourt la liste des noeuds
            for(Noeud elt: this.ensNoeuds){
                // On cherche le noeud égal au noeud de départ
                if(elt.equals(new Noeud(depart))){
                    // Et on ajoute l'arc correspondant
                    elt.ajouterArc(destination, cout);
                }
            }
        } else{
            // Si le noeud de départ n'existe pas alors on le crée et on l'ajoute aux listes
            if(!this.ensNom.contains(depart)){
                this.ensNom.add(depart);
                Noeud n=new Noeud(depart);
                this.ensNoeuds.add(n);
            }
            // Si le noeud d'arrivée n'existe pas alors on le crée et on l'ajoute aux listes
            if (!this.ensNom.contains(destination)) {
                this.ensNom.add(destination);
                Noeud n2=new Noeud(destination);
                this.ensNoeuds.add(n2);
            }
            //Puis on parcourt la liste et on ajoute l'arc
            for(Noeud elt: this.ensNoeuds){
                if(elt.equals(new Noeud(depart))){
                    elt.ajouterArc(destination, cout);
                }
            }
        }
    }

    /**
     * méthode qui permet d'obtenir tous les noms des nœuds d'un graphe
     *
     * @return une liste de tous les noms des nœuds d'un graohe
     */
    public List<String> listeNoeuds(){
        return this.ensNom;
    }

    /**
     * Méthode qui permet d'afficher un graphe de manière que ce soit facile de comprendre
     *
     * @return une chaine de caractère représentant le graphe
     */
    public String toString(){
        String res = "";

        //on parcourt tous les noeuds du graphe
        for(Noeud element: this.ensNoeuds){

            //si le noeud possède des arcs, alors on affiche son nom suivi de tous ses arcs et leur destination
            if(element.getArc().size()!=0){
                res += element.getNom() + " -> ";

                //parcourt de tous les arcs du noeud pour obtenir le noeud de destination et le coût de l'arc
                for(Arc element2: element.getArc()){
                    res += element2.getDest() + "(" + (int)element2.getCout() + ") ";
                }
                res += "\n";
            }
        }
        return res;
    }


}
