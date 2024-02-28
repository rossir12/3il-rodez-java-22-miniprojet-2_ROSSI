package fr.ecole3il.rodez2023.carte.elements;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Graphe<E> {
    private Map<E, Noeud<E>> noeuds;
    private Map<Noeud<E>, Map<Noeud<E>, Double>> aretes;

    public Graphe() {
        this.noeuds = new HashMap<>();
        this.aretes = new HashMap<>();
    }

    public void ajouterNoeud(Noeud<E> noeud) {
        this.noeuds.putIfAbsent(noeud.getValeur(), noeud);
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        this.ajouterNoeud(depart);
        this.ajouterNoeud(arrivee);

        this.aretes.putIfAbsent(depart, new HashMap<>());
        this.aretes.get(depart).put(arrivee, cout);

        depart.ajouterVoisin(arrivee);
        // Si le graphe est non orienté, ajoutez également l'arête inverse.
        // this.aretes.putIfAbsent(arrivee, new HashMap<>());
        // this.aretes.get(arrivee).put(depart, cout);
        // arrivee.ajouterVoisin(depart);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        if (this.aretes.containsKey(depart) && this.aretes.get(depart).containsKey(arrivee)) {
            return this.aretes.get(depart).get(arrivee);
        } else {
            throw new IllegalArgumentException("L'arête n'existe pas.");
        }
    }

    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(this.noeuds.values());
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        return new ArrayList<>(noeud.getVoisins());
    }
}
