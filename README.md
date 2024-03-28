# Miniprojet 2 - Repérage et direction sur une carte en 2D

## Classe Graphe 

### `Question`

**Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes ?**

### `Réponse`

L'utilisation d'une map où chaque nœud est associé à ses voisins, et les coûts des arêtes sont stockés comme des valeurs dans cette map.

### `Question`

**Pourquoi pensez-vous que les classes Noeud et Graphe ont été définies avec des paramètres génériques ?**

### `Réponse`

Les classes Noeud et Graphe ont été définies avec des paramètres génériques pour offrir une flexibilité maximale.

## Interface AlgorithmeChemin

### `Question`

**Pourquoi pensez-vous que la création d'une interface est une bonne pratique dans ce contexte ?**

### `Réponse`

La création d'une interface est une bonne pratique dans ce contexte car elle permet de définir un contrat commun que différentes implémentations peuvent suivre. En définissant une interface pour les algorithmes de recherche de chemin, on peut avoir plusieurs implémentations d'algorithmes comme Dijkstra ou A* qui respectent toutes le même contrat.