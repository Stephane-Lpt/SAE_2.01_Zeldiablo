package Graphes;

import java.util.List;

/**
 * L'interface Graphe définit les opérations de base pour un graphe
 */
public interface Graphe {
    /**
     * méthode qui permet d'obtenir tous les noms des nœuds d'un graphe
     *
     * @return une liste de tous les noms des nœuds d'un graohe
     */
    public List<String> listeNoeuds();

    /**
     * méthode qui permet de retourner la liste des arcs partant du nœud passé en paramètre
     *
     * @param n nœud pour lequel on veut connaitre la liste des arcs
     * @return la liste des arcs du nœud passé en paramètre
     */
    public List<Arc> suivants(String n);

}