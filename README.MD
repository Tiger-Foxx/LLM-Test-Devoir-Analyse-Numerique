# README GLOBAL

## Description du Projet
Ce projet a pour but de comparer les performances et la qualité des réponses fournies par différentes IA pour la génération et l'exécution de tests logiciels. Les IA utilisées sont :
- **ChatGPT**
- **DeepSeek**
- **Gemini**

Chaque version du projet contient :
- Un dossier contenant le code généré par chaque IA
- Un fichier README spécifique à chaque version détaillant la logique du code et les résultats obtenus
- Un ensemble de tests portant sur :
  1. Le calcul de la racine carrée d'un nombre réel positif
  2. La résolution d'une équation quadratique de la forme *ax² + bx + c = 0*

## Structure du Projet
```
C:.
├───version ChatGPT
│   └───code version ChatGPT
│       ├───.vscode
│       ├───bin
│       ├───lib
│       └───src
├───version DeepSeek
│   └───code version DeepSeek
│       ├───.vscode
│       ├───bin
│       ├───lib
│       └───src
└───version Gemini
    └───code version Gemini
        ├───.vscode
        ├───bin
        ├───lib
        └───src
```

## Comment Lancer les Tests
1. Ouvrir un terminal dans le répertoire correspondant à la version de l'IA que vous souhaitez tester.
2. Compiler le fichier `TestFunctions.java` (situé dans `src`) avec la commande :
   ```sh
   javac -d bin src/TestFunctions.java
   ```
3. Exécuter les tests avec la commande :
   ```sh
   java -cp bin TestFunctions
   ```
4. Observer et analyser les résultats affichés dans la console.

## Utilisation des README Individuels
Chaque version dispose de son propre README, qui contient :
- La logique de génération du code
- La liste des tests effectués
- L'analyse des résultats obtenus
- Une discussion sur les performances (temps d'exécution, gestion des erreurs, etc.)

Ces documents serviront à la rédaction d'un rapport comparatif des réponses fournies par chaque IA, en mettant en avant :
- La précision des calculs
- La gestion des erreurs
- L'efficacité en termes de performances
- La clarté et la lisibilité du code généré

## Objectif du Rapport
L'objectif du rapport est d'évaluer et comparer la qualité du code et des résultats fournis par chaque IA, afin d'identifier leurs forces et faiblesses respectives dans un contexte de test logiciel automatisé.

