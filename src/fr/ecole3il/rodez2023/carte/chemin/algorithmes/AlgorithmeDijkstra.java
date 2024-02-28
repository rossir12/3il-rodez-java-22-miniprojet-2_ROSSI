package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

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
