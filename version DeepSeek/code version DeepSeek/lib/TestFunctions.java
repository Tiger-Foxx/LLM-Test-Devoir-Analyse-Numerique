import java.util.Arrays;

public class TestFunctions {

    // Fonction 1 : Calcul de la racine carrée
    public static double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("La racine carrée d’un nombre négatif n’est pas définie.");
        }
        return Math.sqrt(x);
    }

    // Fonction 2 : Calcul des solutions de l’équation quadratique
    public static double[] solveQuadratic(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("a ne peut pas être nul pour une équation quadratique.");
        }
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new double[]{x1, x2};
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            return new double[]{x, x};
        } else {
            throw new IllegalArgumentException("Discriminant négatif : pas de solution réelle.");
        }
    }

    // Programme de test
    public static void main(String[] args) {
        // Test de la fonction sqrt
        System.out.println("=== Tests pour la fonction sqrt ===");
        testSqrt(4, 2.0);
        testSqrt(9, 3.0);
        testSqrt(16, 4.0);
        testSqrt(0, 0.0);
        testSqrt(1e-10, 1e-5);
        testSqrt(1e20, 1e10);
        testSqrt(-1, Double.NaN); // Cas problématique
        System.out.println();

        // Test de la fonction solveQuadratic
        System.out.println("=== Tests pour la fonction solveQuadratic ===");
        testQuadratic(1, -3, 2, new double[]{2.0, 1.0}); // Discriminant positif
        testQuadratic(1, 2, 1, new double[]{-1.0, -1.0}); // Discriminant nul
        testQuadratic(1, 2, 5, null); // Discriminant négatif
        testQuadratic(0, 2, 3, null); // Cas problématique (a = 0)
    }

    // Méthode pour tester la fonction sqrt
    private static void testSqrt(double input, double expected) {
        long startTime = System.nanoTime();
        try {
            double result = sqrt(input);
            System.out.printf("sqrt(%.1f) -> %.1f (Attendu : %.1f)%n", input, result, expected);
        } catch (IllegalArgumentException e) {
            System.out.printf("sqrt(%.1f) -> Erreur : %s%n", input, e.getMessage());
        }
        long endTime = System.nanoTime();
        System.out.printf("Temps d’exécution : %d ns%n", endTime - startTime);
    }

    // Méthode pour tester la fonction solveQuadratic
    private static void testQuadratic(double a, double b, double c, double[] expected) {
        long startTime = System.nanoTime();
        try {
            double[] result = solveQuadratic(a, b, c);
            System.out.printf("solveQuadratic(%.1f, %.1f, %.1f) -> %s (Attendu : %s)%n",
                    a, b, c, Arrays.toString(result), Arrays.toString(expected));
        } catch (IllegalArgumentException e) {
            System.out.printf("solveQuadratic(%.1f, %.1f, %.1f) -> Erreur : %s%n",
                    a, b, c, e.getMessage());
        }
        long endTime = System.nanoTime();
        System.out.printf("Temps d’exécution : %d ns%n", endTime - startTime);
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