package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import java.util.*;

/**
 * Implémente l'algorithme de Dijkstra pour trouver le chemin le plus court entre deux noeuds dans un graphe pondéré.
 * @param <E> le type d'élément stocké dans chaque noeud du graphe
 */
public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {
    /**
     * Trouve le chemin le plus court entre un noeud de départ et un noeud d'arrivée dans un graphe pondéré en utilisant l'algorithme de Dijkstra.
     * @param graphe le graphe dans lequel rechercher le chemin
     * @param depart le noeud de départ du chemin
     * @param arrivee le noeud d'arrivée du chemin
     * @return la liste des noeuds formant le chemin le plus court, du noeud de départ au noeud d'arrivée
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des structures de données
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparing(couts::get));
        
        graphe.getNoeuds().forEach(noeud -> couts.put(noeud, Double.POSITIVE_INFINITY));
        couts.put(depart, 0.0);
        filePriorite.add(depart);

        // Exploration des nœuds
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            
            if (noeudActuel.equals(arrivee)) {
                break;
            }

            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double coutActuel = couts.get(noeudActuel);
                double coutVoisin = couts.get(voisin);
                double coutTraverse = graphe.getCoutArete(noeudActuel, voisin);
                
                double coutTotal = coutActuel + coutTraverse;
                if (coutTotal < coutVoisin) {
                    couts.put(voisin, coutTotal);
                    predecesseurs.put(voisin, noeudActuel);
                    filePriorite.add(voisin);
                }
            }
        }

        // Reconstruction du chemin le plus court
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> etape = arrivee;
        while (etape != null) {
            chemin.add(etape);
            etape = predecesseurs.get(etape);
        }
        Collections.reverse(chemin);
        return chemin;
    }
}
