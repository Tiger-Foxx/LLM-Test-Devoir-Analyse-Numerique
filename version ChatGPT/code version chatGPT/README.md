# Rapport d'analyse des tests

## 1. Prompt utilisé

```plaintext
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
```

## 2. Données de test fournies

### Fonction 1 : `calculerRacineCarree`
| Input | Résultat attendu | Description |
|--------|----------------|-------------|
| 4.0 | 2.0 | Cas standard |
| 9.0 | 3.0 | Cas standard |
| 16.0 | 4.0 | Cas standard |
| 0.0 | 0.0 | Cas limite |
| 1.0E-10 | 1.0E-5 | Très petit nombre |
| 1.0E10 | 100000.0 | Très grand nombre |
| -4.0 | Exception | Cas d'entrée invalide |

### Fonction 2 : `resoudreEquationQuadratique`
| a | b | c | Discriminant | Résultat attendu |
|---|---|---|-------------|-----------------|
| 1.0 | -5.0 | 6.0 | 1 | Deux solutions réelles (3.0, 2.0) |
| 1.0 | 2.0 | 1.0 | 0 | Solution unique (-1.0) |
| 1.0 | 2.0 | 3.0 | -8 | Deux solutions complexes (-1.0 ± 1.4142i) |

## 3. Analyse du code

### Structure du code fourni
- **Nombre total de lignes (hors commentaires) :** 95 lignes
- **Langage utilisé :** Java
- **Mesure du temps d'exécution :** `System.nanoTime()`
- **Gestion des erreurs :** Vérification des entrées négatives pour `calculerRacineCarree`, gestion des solutions complexes pour `resoudreEquationQuadratique`

### Logique de code
#### `calculerRacineCarree`
1. Vérifie si l'entrée est négative et lève une exception le cas échéant.
2. Calcule la racine carrée avec `Math.sqrt()`.
3. Affiche le résultat et le temps d'exécution.

#### `resoudreEquationQuadratique`
1. Calcule le discriminant `D = b² - 4ac`.
2. Selon la valeur de D :
   - **D > 0** : Deux solutions réelles distinctes.
   - **D = 0** : Une solution unique.
   - **D < 0** : Solutions complexes affichées sous forme `a + bi`.
3. Affiche le résultat et le temps d'exécution.

## 4. Résultats des tests

```
=== Tests pour calculerRacineCarree ===
Test input: 4.0 => sqrt: 2.0 | Temps d'exécution: 37200 ns
Test input: 9.0 => sqrt: 3.0 | Temps d'exécution: 1200 ns
Test input: 16.0 => sqrt: 4.0 | Temps d'exécution: 600 ns
Test input: 0.0 => sqrt: 0.0 | Temps d'exécution: 600 ns
Test input: 1.0E-10 => sqrt: 1.0E-5 | Temps d'exécution: 700 ns
Test input: 1.0E10 => sqrt: 100000.0 | Temps d'exécution: 500 ns
Test input: -4.0 => Exception: Le nombre doit être positif ou nul. | Temps d'exécution: 55700 ns

=== Tests pour resoudreEquationQuadratique ===
Test équation: 1.0x² + -5.0x + 6.0 = 0 => Solutions: 3.0 2.0 | Temps d'exécution: 44000 ns
Test équation: 1.0x² + 2.0x + 1.0 = 0 => Solutions: -1.0 | Temps d'exécution: 25100 ns
Test équation: 1.0x² + 2.0x + 3.0 = 0 => Solutions: -1,0000 + 1,4142i -1,0000 - 1,4142i | Temps d'exécution: 32915700 ns
```

## 5. Analyse des résultats

1. **Temps d'exécution moyen pour `calculerRacineCarree`** : Très faible (< 1µs en général), sauf pour l'entrée négative où l'exception prend plus de temps (~55µs).
2. **Temps d'exécution pour `resoudreEquationQuadratique`** :
   - Cas réels : ~25-44µs
   - Cas complexe : Beaucoup plus long (~33ms), dû au calcul des racines complexes.
3. **Gestion des cas limites** :
   - `calculerRacineCarree` rejette correctement les valeurs négatives.
   - `resoudreEquationQuadratique` gère bien les trois cas du discriminant.

## 6. Conclusion

Les résultats montrent un bon comportement des fonctions testées, avec un temps d'exécution faible pour les cas simples et une complexité accrue pour les calculs plus lourds (notamment les nombres complexes). Le code Java fourni est efficace, bien structuré et documenté. Une optimisation potentielle pourrait être d'améliorer la gestion des nombres complexes pour `resoudreEquationQuadratique` afin de réduire son temps d'exécution.

---
**Ce document servira de base pour la comparaison des résultats avec d'autres IA.**

