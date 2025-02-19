
# Comparaison des résultats des IA pour le test de fonctions mathématiques

Ce document compare les résultats de trois IA (Gemini, ChatGPT et DeepSeek) pour un projet de test logiciel portant sur deux fonctions mathématiques :

1. Calcul de la racine carrée d'un nombre réel positif.
2. Calcul des solutions d'une équation quadratique *ax² + bx + c = 0*.

## Prompt

```
Bonjour ,

Dans le cadre d’un projet de test logiciel, j’ai besoin de générer :
1. **Des données de test** pour deux fonctions :
   - **Fonction 1** : Calculer la racine carrée d’un nombre réel positif. Merci de proposer une série de cas de test incluant :
     - Cas standards (ex : 4, 9, 16)
     - Cas limites (ex : 0, très petits nombres, très grands nombres)
     - Cas potentiellement problématiques (vérifier si on gère bien les entrées négatives, même si la fonction ne devrait accepter que les réels positifs)
   - **Fonction 2** : Calculer les solutions de l’équation quadratique *ax² + bx + c = 0* (avec a, b, c réels). Merci de fournir des cas de test couvrant :
     - Discriminant positif (deux solutions réelles distinctes)
     - Discriminant nul (une solution réelle double)
     - Discriminant négatif (solutions complexes ou gestion d’erreur)
2. **Un programme de test complet en Java** pour chacune des fonctions, qui :
   - Implémente les tests sur les cas décrits.
   - Mesure et affiche le temps d’exécution de chaque test (par exemple, en utilisant `System.nanoTime()` ou `System.currentTimeMillis()`).
   - Comporte des commentaires expliquant la logique et les choix de cas de test.
   
Merci de bien détailler le code, de le rendre lisible et directement exécutable. N’oublie pas d’indiquer également les raisons des choix effectués pour les cas de test.

Enfin, merci d’inclure (en commentaire a la fin du code) dans ta réponse le texte exact de ce prompt pour que je puisse le documenter dans mon rapport.
```

## Résultats

### Gemini

*   **Code fourni **: 62 lignes (hors commentaires)
*   **Résultats des tests **:

```
Tests de la racine carrée :
Entrée : 4.0, Résultat : 2.0, Attendu : 2.0, Temps : 26500 ns
Entrée : 9.0, Résultat : 3.0, Attendu : 3.0, Temps : 900 ns
Entrée : 16.0, Résultat : 4.0, Attendu : 4.0, Temps : 500 ns
Entrée : 0.0, Résultat : 0.0, Attendu : 0.0, Temps : 600 ns
Entrée : 1.0E-10, Résultat : 1.0E-5, Attendu : 1.0E-5, Temps : 500 ns
Entrée : 1.0E10, Résultat : 100000.0, Attendu : 100000.0, Temps : 600 ns
Test -1 OK : Nombre négatif

Tests de l'équation quadratique :
a=1.0, b=-3.0, c=2.0, Solutions : x1=2.0, x2=1.0, Attendu : x1=2.0, x2=1.0, Temps : 2800 ns
a=1.0, b=-2.0, c=1.0, Solutions : x1=1.0, x2=1.0, Attendu : x1=1.0, x2=1.0, Temps : 2000 ns
a=1.0, b=1.0, c=1.0, Solutions : Pas de solution réelle, Attendu : Pas de solution réelle, Temps : 800 ns
a=0.0, b=2.0, c=1.0, Solutions : x1=NaN, x2=-Infinity, Attendu : x1=-0.5, x2=-0.5, Temps : 9100 ns
```

*   **Temps d'exécution **: Globalement rapides, similaires à ChatGPT pour les cas standards.
*   **Gestion des cas complexes **: Ne gère pas nativement les solutions complexes (il faut une gestion d'erreur ou un retour spécifique).
*   **Affichage des résultats **: Moins structuré que ChatGPT.
*   **Erreurs/Problèmes rencontrés **:
    *   Le serveur a planté deux fois pendant la génération du code (problème de stabilité ou de longueur du prompt ?).
    *   Importation inutile de `java.util.concurrent.TimeUnit;`
    *   Calcul incorrect des solutions dans le cas de l'équation linéaire (a=0).

### DeepSeek

*   **Code fourni **: 65 lignes (hors commentaires)
*   **Temps d'exécution **: Plus lents que Gemini et ChatGPT.
*   **Gestion des cas complexes **: Génère une erreur pour les équations sans solution réelle.
*   **Affichage des résultats **: Assez structuré.

### ChatGPT

*   **Code fourni **: 95 lignes (hors commentaires)
*   **Temps d'exécution **: Rapides, similaires à Gemini pour les cas standards.
*   **Gestion des cas complexes **: Gère nativement les solutions complexes.
*   **Affichage des résultats **: Très structuré et clair.

## Conclusion

Gemini a produit un code fonctionnel et rapide, mais présente quelques points faibles :

*   **Stabilité du serveur **: Les plantages répétés sont un problème majeur.
*   **Gestion des cas limites **: Le calcul incorrect pour l'équation linéaire (a=0) et l'absence de gestion des solutions complexes sont des points à améliorer.
*   **Affichage des résultats **: Un affichage plus structuré serait préférable.

En comparaison, ChatGPT offre une meilleure gestion des cas limites et un affichage plus clair, mais son code est plus long. DeepSeek est plus lent et gère moins bien les cas complexes.

Gemini pourrait être un outil puissant avec quelques améliorations au niveau de la stabilité, de la gestion des cas limites et de l'affichage.
