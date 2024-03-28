package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import java.util.*;

/**
 * L'algorithme A* est une méthode de recherche de chemin dans un graphe qui
 * utilise à la fois
 * les informations sur le coût pour atteindre un nœud et une estimation du coût
 * restant
 * pour atteindre la destination.
 *
 * @param <E> Le type de données contenu dans chaque nœud du graphe.
 */
public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {
    /**
     * Trouve un chemin entre un nœud de départ et un nœud d'arrivée dans un graphe
     * donné en utilisant
     * l'algorithme A*.
     *
     * @param graphe  Le graphe dans lequel chercher le chemin.
     * @param depart  Le nœud de départ.
     * @param arrivee Le nœud d'arrivée.
     * @return Une liste de nœuds représentant le chemin trouvé.
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        Map<Noeud<E>, Double> coutEstime = new HashMap<>();
        Map<Noeud<E>, Double> coutActuel = new HashMap<>();

        // Initialisation des coûts pour chaque nœud du graphe
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutActuel.put(noeud, Double.POSITIVE_INFINITY);
            coutEstime.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        // Le coût actuel et estimé pour le nœud de départ est initialisé à 0
        coutActuel.put(depart, 0.0);
        coutEstime.put(depart, 0.0);

        // File de priorité pour stocker les nœuds ouverts, triés par le coût estimé
        // total
        PriorityQueue<Noeud<E>> ouverts = new PriorityQueue<>(
                (n1, n2) -> (int) (coutEstime.get(n1) - coutEstime.get(n2)));
        ouverts.add(depart);

        // Boucle principale de l'algorithme A*
        while (!ouverts.isEmpty()) {
            Noeud<E> courant = ouverts.poll();
            // Si le nœud courant est le nœud d'arrivée, on a trouvé le chemin
            if (courant.equals(arrivee))
                break;
            // Parcours des voisins du nœud courant
            for (Noeud<E> voisin : graphe.getVoisins(courant)) {
                double cout = coutActuel.get(courant) + graphe.getCoutArete(courant, voisin);
                // Si le nouveau coût pour atteindre le voisin est meilleur que le coût actuel,
                // on met à jour les informations du voisin et on l'ajoute à la file de priorité
                if (cout < coutActuel.get(voisin)) {
                    predecesseurs.put(voisin, courant);
                    coutActuel.put(voisin, cout);
                    coutEstime.put(voisin, cout);
                    ouverts.add(voisin);
                }
            }
        }
        // Reconstruction du chemin à partir des prédécesseurs
        List<Noeud<E>> chemin = new LinkedList<>();
        Noeud<E> etape = arrivee;
        while (etape != null) {
            chemin.add(etape);
            etape = predecesseurs.get(etape);
        }
        // Inversion du chemin pour obtenir l'ordre correct des nœuds
        Collections.reverse(chemin);
        return chemin;
    }
}
