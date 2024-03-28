package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import java.util.List;

/**
 * Interface représentant un algorithme permettant de trouver un chemin dans un graphe.
 * @param <E> le type d'élément stocké dans chaque noeud du graphe
 */
public interface AlgorithmeChemin<E> {

    /**
     * Trouve un chemin dans le graphe entre un noeud de départ et un noeud d'arrivée.
     * @param graphe le graphe dans lequel rechercher le chemin
     * @param depart le noeud de départ du chemin
     * @param arrivee le noeud d'arrivée du chemin
     * @return la liste des noeuds formant le chemin trouvé
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}