package fr.ecole3il.rodez2023.carte.elements;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Représente un graphe générique avec des noeuds et des arêtes pondérées.
 * @param <E> le type d'élément stocké dans chaque noeud du graphe
 */
public class Graphe<E> {
    private Map<Noeud<E>, Map<Noeud<E>, Double>> voisins;

    /**
     * Initialise un nouveau graphe vide.
     */
    public Graphe() {
        this.voisins = new HashMap<>();
    }

    /**
     * Ajoute un nouveau noeud au graphe.
     * @param noeud le noeud à ajouter
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        this.voisins.putIfAbsent(noeud, new HashMap<>());
    }

    /**
     * Ajoute une arête pondérée entre deux noeuds du graphe.
     * @param depart le noeud de départ de l'arête
     * @param arrivee le noeud d'arrivée de l'arête
     * @param cout le coût (pondération) de l'arête
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        assert depart != null && arrivee != null;
        this.ajouterNoeud(depart);
        this.ajouterNoeud(arrivee);

        this.voisins.get(depart).put(arrivee, cout);
    }

    /**
     * Obtient le coût de l'arête entre deux noeuds du graphe.
     * @param depart le noeud de départ de l'arête
     * @param arrivee le noeud d'arrivée de l'arête
     * @return le coût de l'arête s'il existe, Double.POSITIVE_INFINITY sinon
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        if (this.voisins.containsKey(depart) && this.voisins.get(depart).containsKey(arrivee)) {
            return this.voisins.get(depart).get(arrivee);
        } 
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Obtient la liste de tous les noeuds du graphe.
     * @return la liste de tous les noeuds du graphe
     */
    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(this.voisins.keySet());
    }

    /**
     * Obtient la liste des voisins d'un noeud donné.
     * @param noeud le noeud dont on souhaite obtenir les voisins
     * @return la liste des voisins du noeud donné
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (voisins.containsKey(noeud)) {
            return new ArrayList<>(voisins.get(noeud).keySet());
        }
        return new ArrayList<>();
    }
}
