# Rapport de Comparaison des Résultats : DeepSeek vs ChatGPT

Ce document compare les résultats fournis par **DeepSeek** et **ChatGPT** pour un projet de test logiciel impliquant deux fonctions : le calcul de la racine carrée et la résolution d’équations quadratiques. Le but est d’analyser les différences en termes de logique de code, de temps d’exécution, de structure des résultats et de gestion des cas particuliers.

---

## **Prompt Initial**
Le prompt fourni était le suivant :

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

---

## **Contexte et Problématiques Techniques**

- **Serveur DeepSeek** : Pendant la génération des réponses, le serveur de DeepSeek a rencontré **deux interruptions** ("busy"), ce qui a rallongé le temps de réponse global et potentiellement augmenté le taux d’erreur.  
- **ChatGPT** : Aucune instabilité signalée pendant l’exécution. 

## **Résultats Fournis par DeepSeek**

### **1. Données de Test**
DeepSeek a fourni des cas de test pour les deux fonctions, couvrant les cas standards, limites et problématiques. Les cas de test étaient bien structurés et pertinents.

#### **Fonction 1 : Racine Carrée**
- Cas standards : 4, 9, 16.
- Cas limites : 0, 1e-10 (très petit nombre), 1e20 (très grand nombre).
- Cas problématiques : -1 (négatif).

#### **Fonction 2 : Équation Quadratique**
- Discriminant positif : a=1, b=-3, c=2.
- Discriminant nul : a=1, b=2, c=1.
- Discriminant négatif : a=1, b=2, c=5.
- Cas problématique : a=0 (équation linéaire).

---

### **2. Programme de Test en Java**
DeepSeek a fourni un programme Java de **65 lignes** (commentaires exclus). Le code est bien structuré, lisible et directement exécutable. Il inclut :
- Une méthode `sqrt` pour calculer la racine carrée.
- Une méthode `solveQuadratic` pour résoudre l’équation quadratique.
- Des tests pour chaque cas de test, avec mesure du temps d’exécution.

#### **Points Forts du Code**
- Gestion des erreurs pour les entrées invalides (nombres négatifs pour la racine carrée, discriminant négatif pour l’équation quadratique).
- Mesure précise du temps d’exécution avec `System.nanoTime()`.
- Commentaires explicatifs pour chaque cas de test.

#### **Points Faibles du Code**
- **Pas de gestion des solutions complexes** : DeepSeek a choisi de lever une exception pour les discriminants négatifs au lieu de retourner des solutions complexes.
- **Affichage moins structuré** : Les résultats sont affichés de manière simple, sans formatage avancé.

---

### **Résultats des Tests**

#### **Fonction 1 : Racine Carrée**
```
=== Tests pour la fonction sqrt ===
sqrt(4,0) -> 2,0 (Attendu : 2,0)
Temps d’exécution : 40087200 ns
sqrt(9,0) -> 3,0 (Attendu : 3,0)
Temps d’exécution : 949700 ns
sqrt(16,0) -> 4,0 (Attendu : 4,0)
Temps d’exécution : 1658100 ns
sqrt(0,0) -> 0,0 (Attendu : 0,0)
Temps d’exécution : 2043100 ns
sqrt(1e-10) -> 1e-5 (Attendu : 1e-5)
Temps d’exécution : 2100200 ns
sqrt(1e20) -> 1e10 (Attendu : 1e10)
Temps d’exécution : 1258500 ns
sqrt(-1,0) -> Erreur : La racine carrée d’un nombre négatif n’est pas définie.
Temps d’exécution : 672900 ns
```

#### **Fonction 2 : Équation Quadratique**
```
=== Tests pour la fonction solveQuadratic ===
solveQuadratic(1,0, -3,0, 2,0) -> [2.0, 1.0] (Attendu : [2.0, 1.0])
Temps d’exécution : 1421500 ns
solveQuadratic(1,0, 2,0, 1,0) -> [-1.0, -1.0] (Attendu : [-1.0, -1.0])
Temps d’exécution : 1122800 ns
solveQuadratic(1,0, 2,0, 5,0) -> Erreur : Discriminant négatif : pas de solution réelle.
Temps d’exécution : 1307900 ns
solveQuadratic(0,0, 2,0, 3,0) -> Erreur : a ne peut pas être nul pour une équation quadratique.
Temps d’exécution : 1781700 ns
```

---

### **Commentaires sur les Résultats**

1. **Temps d’Exécution** :
   - Les temps d’exécution de DeepSeek sont **relativement longs** par rapport à ceux de ChatGPT. Par exemple :
     - `sqrt(4,0)` : 40 087 200 ns (DeepSeek) vs 37 200 ns (ChatGPT).
     - `solveQuadratic(1,0, 2,0, 5,0)` : 1 307 900 ns (DeepSeek) vs 32 915 700 ns (ChatGPT).
   - Ces différences peuvent être dues à des facteurs externes (charge de l’ordinateur, environnement d’exécution) ou à des optimisations de code.

2. **Gestion des Cas Complexes** :
   - DeepSeek n’a pas géré les solutions complexes pour les discriminants négatifs, contrairement à ChatGPT qui a retourné des solutions complexes sous forme de chaînes formatées.

3. **Structure des Résultats** :
   - L’affichage des résultats de DeepSeek est **moins structuré** que celui de ChatGPT. Par exemple, ChatGPT utilise un formatage clair pour les solutions complexes, tandis que DeepSeek se contente de lever une exception.

4. **Nombre de Lignes de Code** :
   - Le code fourni par DeepSeek est **compact** (65 lignes), ce qui le rend facile à maintenir et à comprendre.

---

## **Conclusion**

- **DeepSeek** a fourni un code fonctionnel, bien structuré et facile à exécuter. Cependant, il manque certaines fonctionnalités avancées (comme la gestion des solutions complexes) et l’affichage des résultats est moins élaboré que celui de ChatGPT.
- **ChatGPT** a proposé une solution plus complète en termes de gestion des cas complexes et d’affichage des résultats, mais avec des temps d’exécution parfois plus longs.

En résumé, le choix entre les deux dépend des priorités du projet : simplicité et rapidité de développement (DeepSeek (si le serveur est stable)) ou fonctionnalités avancées et affichage structuré (ChatGPT).