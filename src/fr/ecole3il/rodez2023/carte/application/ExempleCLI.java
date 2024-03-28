package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

/**
 * La classe ExempleCLI est un exemple simple d'utilisation des algorithmes de chemin avec une carte.
 * Elle génère une carte, trouve un chemin entre deux points en utilisant un algorithme de chemin spécifié,
 * puis affiche le chemin trouvé.
 */
public class ExempleCLI {
/**
     * Méthode principale pour exécuter l'exemple.
     *
     * @param args les arguments de la ligne de commande (non utilisés dans cet exemple)
     */
	public static void main(String[] args) {
		// Génération d'une carte de test
		GenerateurCarte generateur = new GenerateurCarte();
		Carte test = generateur.genererCarte(100,100);
		// Utilisation de l'algorithme de Dijkstra pour trouver un chemin entre deux points sur la carte
		AlgorithmeChemin algoChemin = new AlgorithmeDijkstra();
		Chemin chemin = AdaptateurAlgorithme.trouverChemin(algoChemin, test, 0, 0, 50, 50);
		// Affichage du chemin trouvé
		chemin.afficherChemin();
		// Utilisation de l'algorithme de Dijkstra pour trouver un autre chemin entre deux points sur la carte
		chemin = AdaptateurAlgorithme.trouverChemin(algoChemin, test, 0, 0, 50, 50);
		// Affichage du deuxième chemin trouvé
		chemin.afficherChemin();
	}
	
}
