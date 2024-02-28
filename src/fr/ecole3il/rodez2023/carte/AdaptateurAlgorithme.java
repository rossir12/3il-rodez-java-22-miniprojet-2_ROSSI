package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptateurAlgorithme {

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = findNoeudByCoordinates(graphe, xDepart, yDepart);
        Noeud<Case> arrivee = findNoeudByCoordinates(graphe, xArrivee, yArrivee);
        
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, depart, arrivee);
        
        List<Case> cheminCases = cheminNoeuds.stream()
                                              .map(Noeud::getValeur)
                                              .collect(Collectors.toList());
        
        return new Chemin(cheminCases);
    }

    static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        // Assuming Carte has a method to iterate over all Cases
        for (Case c : carte.getCases()) {
            graphe.ajouterNoeud(new Noeud<>(c)); // Assuming Graphe has a method to add nodes
        }

        // Add edges based on some logic, assuming Carte provides necessary methods
        // ...
        
        return graphe;
    }

    static Noeud<Case> findNoeudByCoordinates(Graphe<Case> graphe, int x, int y) {
        // Assuming Graphe provides a way to access its nodes, and Case has methods getX() and getY()
        return graphe.getNoeuds().stream()
                     .filter(noeud -> noeud.getValeur().getX() == x && noeud.getValeur().getY() == y)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("No node found at the given coordinates"));
    }

    // Assuming we have a method in Graphe to get all nodes
    // This method would need to be implemented inside Graphe class
    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase) {
        // Logic to add edges to neighboring nodes
        // ...
    }

    static double calculerCout(Case from, Case to) {
        // Implement your logic to calculate cost here
        return 1.0; // Just a placeholder value
    }

    static void afficherChemin(Chemin chemin) {
        chemin.afficherChemin(); // Assuming Chemin has a method to print itself
    }
}
