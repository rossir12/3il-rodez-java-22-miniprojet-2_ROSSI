package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;

import java.util.ArrayList;
import java.util.List;

/**
 * AdaptateurAlgorithme est une classe fournissant des méthodes pour trouver un
 * chemin dans une carte
 * en utilisant un algorithme spécifié.
 */
public class AdaptateurAlgorithme {

    /**
     * Trouve un chemin entre deux points donnés sur une carte en utilisant un
     * algorithme de chemin spécifié.
     *
     * @param algorithme l'algorithme de chemin à utiliser
     * @param carte      la carte sur laquelle chercher le chemin
     * @param xDepart    la coordonnée x du point de départ
     * @param yDepart    la coordonnée y du point de départ
     * @param xArrivee   la coordonnée x du point d'arrivée
     * @param yArrivee   la coordonnée y du point d'arrivée
     * @return le chemin trouvé entre les deux points
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart,
            int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = findNoeudByCoordinates(graphe, xDepart, yDepart);
        Noeud<Case> arrivee = findNoeudByCoordinates(graphe, xArrivee, yArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, depart, arrivee);
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : cheminNoeuds) {
            cheminCases.add(noeud.getValeur());
        }
        return new Chemin(cheminCases);
    }

    /**
     * Crée un graphe à partir d'une carte donnée.
     *
     * @param carte la carte à partir de laquelle créer le graphe
     * @return le graphe créé
     */
    static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeurGraphe = carte.getLargeur();
        int hauteurGraphe = carte.getHauteur();

        // Création des nœuds
        for (int x = 0; x < largeurGraphe; x++) {
            for (int y = 0; y < hauteurGraphe; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                Noeud<Case> noeud = new Noeud<Case>(caseActuelle);
                graphe.ajouterNoeud(noeud);
            }
        }

        // Ajout des arêtes voisines
        for (int x = 0; x < largeurGraphe; x++) {
            for (int y = 0; y < hauteurGraphe; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                ajouterAretesVoisines(graphe, caseActuelle, x, y, largeurGraphe, hauteurGraphe);
            }
        }
        return graphe;
    }

    /**
     * Trouve un nœud correspondant aux coordonnées spécifiées dans le graphe donné.
     *
     * @param graphe le graphe dans lequel chercher le nœud
     * @param x      la coordonnée x du nœud
     * @param y      la coordonnée y du nœud
     * @return le nœud correspondant aux coordonnées spécifiées, ou null s'il
     *         n'existe pas
     */
    static Noeud<Case> findNoeudByCoordinates(Graphe<Case> graphe, int x, int y) {
        for (Noeud<Case> noeud : graphe.getNoeuds()) {
            Case c = noeud.getValeur();
            if (c.getX() == x && c.getY() == y) {
                return noeud;
            }
        }
        return null; // Si aucun nœud correspondant n'est trouvé
    }

    /**
     * Ajoute les arêtes voisines pour un nœud donné dans le graphe.
     *
     * @param graphe        le graphe dans lequel ajouter les arêtes
     * @param caseActuelle  la case actuelle pour laquelle ajouter les arêtes voisines
     * @param x             la coordonnée x de la case actuelle
     * @param y             la coordonnée y de la case actuelle
     * @param largeurGraphe la largeur du graphe
     * @param hauteurGraphe la hauteur du graphe
     */
    static void ajouterAretesVoisines(Graphe<Case> graphe, Case caseActuelle, int x, int y, int largeurGraphe,
            int hauteurGraphe) {
        Noeud<Case> currentNode = findNoeudByCoordinates(graphe, x, y);
        if (currentNode == null)
            return;
        for (Noeud<Case> noeud : graphe.getNoeuds()) {
            Case c = noeud.getValeur();
            if (c.equals(caseActuelle)) {
                currentNode = noeud;
                break;
            }
        }
        assert currentNode != null;
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (newX >= 0 && newX < largeurGraphe && newY >= 0 && newY < hauteurGraphe) {
                Noeud<Case> neighborNode = findNoeudByCoordinates(graphe, newX, newY);
                if (neighborNode != null) {
                    Case neighborCase = neighborNode.getValeur();
                    double cout = calculerCout(caseActuelle, neighborCase);
                    graphe.ajouterArete(currentNode, neighborNode, cout);
                    currentNode.ajouterVoisin(neighborNode);
                }
            }
        }
    }

    /**
     * Calcule le coût entre deux cases.
     *
     * @param from la case de départ
     * @param to la case d'arrivée
     * @return le coût entre les deux cases
     */
    static double calculerCout(Case from, Case to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    /**
     * Affiche les cases d'un chemin.
     *
     * @param chemin la liste de nœuds représentant le chemin
     */
    static void afficherChemin(List<Noeud<Case>> chemin) {
        chemin.forEach(noeud -> {
            Case caseActuelle = noeud.getValeur();
            System.out.println("Case: x = " + caseActuelle.getX() + ", y = " + caseActuelle.getY());
        });
    }
}
