package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import java.util.*;
import java.util.function.Function;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    private final Function<Noeud<E>, Double> heuristique;

    public AlgorithmeAEtoile(Function<Noeud<E>, Double> heuristique) {
        this.heuristique = heuristique;
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        Map<Noeud<E>, Double> coutTotalEstime = new HashMap<>(); // This will store the f(n) = g(n) + h(n) values

        // Initialize coutTotalEstime with infinity for all nodes except the start node
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutTotalEstime.put(noeud, Double.POSITIVE_INFINITY);
        }
        coutTotalEstime.put(depart, heuristique.apply(depart));

        PriorityQueue<Noeud<E>> ouverts = new PriorityQueue<>(Comparator.comparingDouble(n -> couts.getOrDefault(n, Double.POSITIVE_INFINITY) + heuristique.apply(n)));
        ouverts.add(depart);
        couts.put(depart, 0.0);

        while (!ouverts.isEmpty()) {
            Noeud<E> courant = ouverts.poll();
            if (courant.equals(arrivee)) {
                return reconstruireChemin(predecesseurs, arrivee);
            }

            for (Noeud<E> voisin : graphe.getVoisins(courant)) {
                double coutActuel = couts.getOrDefault(courant, Double.POSITIVE_INFINITY) + graphe.getCoutArete(courant, voisin);
                if (coutActuel < couts.getOrDefault(voisin, Double.POSITIVE_INFINITY)) {
                    predecesseurs.put(voisin, courant);
                    couts.put(voisin, coutActuel);
                    coutTotalEstime.put(voisin, coutActuel + heuristique.apply(voisin));
                    if (!ouverts.contains(voisin)) {
                        ouverts.add(voisin);
                    }
                }
            }
        }

        return Collections.emptyList(); // Return an empty path if no path is found
    }

    private List<Noeud<E>> reconstruireChemin(Map<Noeud<E>, Noeud<E>> predecesseurs, Noeud<E> arrivee) {
        LinkedList<Noeud<E>> chemin = new LinkedList<>();
        Noeud<E> etape = arrivee;
        while (etape != null) {
            chemin.addFirst(etape);
            etape = predecesseurs.get(etape);
        }
        return chemin;
    }
}

