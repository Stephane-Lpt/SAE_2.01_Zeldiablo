package Graphes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet de représenter un nœud avec un nom et la liste de nœud adjacents
 */
public class Noeud {

    /**
     * nom du nœud
     */
    private String nom;

    /**
     * Liste des arcs adjacents au nœud
     */
    private List<Arc> adj;

    /**
     * Constructeur de nœud avec un nom
     * @param n nom donné au nœud
     */
    public Noeud(String n){
        this.nom=n;
        this.adj=new ArrayList<Arc>();
    }

    /**
     * méthode qui permet de tester si deux nœuds sont égaux ou pas
     * @param obj objet à comparer avec le nœud courant
     * @return vrai si les deux nœuds sont égaux et faux sinon
     */
    public boolean equals(Object obj) {
        Noeud other = (Noeud) obj;
        return (this.nom.compareTo(other.nom)==0);
    }

    /*
    public boolean equals(String s){
        return (this.nom==s);
    }
    */

    /**
     * Méthode qui permet d'ajouter un arc adjacent à un nœud
     * @param destination nœud destination de l'arc
     * @param cout coût (ou poids) de l'arc
     */
    public void ajouterArc(String destination, double cout){
        //on regarde si l'arc n'existe pas déjà en parcourant toutes les destinations du noeud de départ
        boolean existe = false;
        for(Arc arc: this.adj){
            if(arc.getDest().equals(destination)){
                existe = true;
            }
        }
        if(!existe){
            this.adj.add(new Arc(destination, cout));
        }
    }

    /**
     * Méthode qui permet de récupérer la liste des arcs adjacents au nœud
     * @return la liste des arcs adjacents
     */
    public List<Arc> getArc(){
        return this.adj;
    }

    /**
     * Méthode qui permet de récupérer le nom du nœud
     * @return le nom du nœud
     */
    public String getNom(){
        return this.nom;
    }
}
