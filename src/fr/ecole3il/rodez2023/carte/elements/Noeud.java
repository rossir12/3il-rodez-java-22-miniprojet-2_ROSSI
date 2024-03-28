package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un noeud dans un graphe.
 * @param <E> le type de valeur stockée dans le noeud
 */
public class Noeud<E> {
    private E valeur;
    private List<Noeud<E>> voisins;

    /**
     * Initialise un nouveau noeud avec une valeur donnée.
     * @param valeur la valeur du noeud
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Récupère la valeur du noeud.
     * @return la valeur du noeud
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Récupère la liste des voisins du noeud.
     * @return la liste des voisins du noeud
     */
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    /**
     * Ajoute un voisin au noeud.
     * @param voisin le voisin à ajouter
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        this.voisins.add(voisin);
    }
}
