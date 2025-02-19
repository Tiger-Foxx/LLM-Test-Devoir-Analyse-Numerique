import java.util.concurrent.TimeUnit;

public class TestFunctions {

    // Fonction de calcul de la racine carrée
    public static double racineCarree(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Nombre négatif");
        }
        return Math.sqrt(x);
    }

    // Fonction de résolution d'équation quadratique
    public static double[] equationQuadratique(double a, double b, double c) {
        double delta = b * b - 4 * a * c;
        if (delta < 0) {
            return null; // Pas de solution réelle
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return new double[] { x, x };
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return new double[] { x1, x2 };
        }
    }


    // Fonction utilitaire pour tester la racine carrée
    public static void testerRacineCarree(double entree, double resultatAttendu) {
        long startTime = System.nanoTime();
        double resultat = racineCarree(entree);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Entrée : " + entree + ", Résultat : " + resultat +
                ", Attendu : " + resultatAttendu + ", Temps : " + duration + " ns");
    }

    // Fonction utilitaire pour tester l'équation quadratique
    public static void testerEquationQuadratique(double a, double b, double c, double[] solutionsAttendues) {
        long startTime = System.nanoTime();
        double[] solutions = equationQuadratique(a, b, c);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.print("a=" + a + ", b=" + b + ", c=" + c + ", Solutions : ");
        if (solutions == null) {
            System.out.print("Pas de solution réelle");
        } else {
            System.out.print("x1=" + solutions[0] + ", x2=" + solutions[1]);
        }
        System.out.println(", Attendu : " + (solutionsAttendues == null ? "Pas de solution réelle" :
                "x1=" + solutionsAttendues[0] + ", x2=" + solutionsAttendues[1]) + ", Temps : " + duration + " ns");
    }


    public static void main(String[] args) {
        // Tests de la racine carrée
        System.out.println("Tests de la racine carrée :");
        testerRacineCarree(4, 2);
        testerRacineCarree(9, 3);
        testerRacineCarree(16, 4);
        testerRacineCarree(0, 0);
        testerRacineCarree(1e-10, 1e-5);
        testerRacineCarree(1e10, 100000);
        try {
            testerRacineCarree(-1, Double.NaN); // S'attend à une exception
        } catch (IllegalArgumentException e) {
            System.out.println("Test -1 OK : " + e.getMessage());
        }

        // Tests de l'équation quadratique
        System.out.println("\nTests de l'équation quadratique :");
        testerEquationQuadratique(1, -3, 2, new double[] { 2, 1 });
        testerEquationQuadratique(1, -2, 1, new double[] { 1, 1 });
        testerEquationQuadratique(1, 1, 1, null); // S'attend à null
        testerEquationQuadratique(0, 2, 1, new double[] { -0.5, -0.5 }); // Équation linéaire

    }

}

/* 
 

PROMPT COMPLET :
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

 */