package fr.ecole3il.rodez2023.carte.elements;

/**
 * L'énumération Tuile représente les différents types de tuiles pouvant être présents sur une carte.
 * Chaque type de tuile a une pénalité associée.
 */
public enum Tuile {
    DESERT(1), // Tuile désert avec une pénalité de déplacement de 1
    MONTAGNES(3), // Tuile montagnes avec une pénalité de déplacement de 3
    PLAINE(2), // Tuile plaine avec une pénalité de déplacement de 2
    FORET(4); // Tuile forêt avec une pénalité de déplacement de 4

    private final int penalite; // Pénalité de déplacement associée à la tuile

    /**
     * Construit une nouvelle tuile avec la pénalité spécifiée.
     * @param penalite La pénalité de déplacement associée à la tuile.
     */
    Tuile(int penalite) {
        this.penalite = penalite;
    }

    /**
     * Récupère la pénalité de déplacement associée à la tuile.
     * @return La pénalité de déplacement associée à la tuile.
     */
    public int getPenalite() {
        return penalite;
    }
}
