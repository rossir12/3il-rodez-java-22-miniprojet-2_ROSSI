package fr.ecole3il.rodez2023.carte.elements;

/**
 * La classe Carte représente une carte composée de tuiles disposées en une grille bidimensionnelle.
 * 
 */
public class Carte {
    private Tuile[][] tuiles; // Matrice de tuiles représentant la carte

    /**
     * Construit une nouvelle carte avec les tuiles spécifiées.
     * @param tuiles La matrice de tuiles représentant la carte.
     */
    public Carte(Tuile[][] tuiles) {
        this.tuiles = tuiles;
    }

    /**
     * Récupère la tuile située aux coordonnées spécifiées.
     * @param x La coordonnée x de la tuile.
     * @param y La coordonnée y de la tuile.
     * @return La tuile située aux coordonnées spécifiées.
     */
    public Tuile getTuile(int x, int y) {
        return tuiles[x][y];
    }

    /**
     * Récupère la largeur de la carte (nombre de colonnes).
     * @return La largeur de la carte.
     */
    public int getLargeur() {
        return tuiles.length;
    }

    /**
     * Récupère la hauteur de la carte (nombre de lignes).
     * @return La hauteur de la carte.
     */
    public int getHauteur() {
        return tuiles[0].length;
    }
}
