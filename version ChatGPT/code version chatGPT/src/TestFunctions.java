/**
 * Programme de test pour deux fonctions :
 * 1. Calculer la racine carrée d’un nombre réel positif.
 * 2. Calculer les solutions de l’équation quadratique ax² + bx + c = 0.
 *
 * Les tests mesurent le temps d’exécution de chaque cas.
 */
public class TestFunctions {

    /**
     * Fonction 1 : Calculer la racine carrée d’un nombre réel positif.
     * Si le nombre est négatif, une IllegalArgumentException est levée.
     *
     * @param x le nombre dont on veut la racine carrée
     * @return la racine carrée de x
     */
    public static double calculerRacineCarree(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Le nombre doit être positif ou nul.");
        }
        return Math.sqrt(x);
    }

    /**
     * Fonction 2 : Résoudre l’équation quadratique ax² + bx + c = 0.
     * Pour a == 0, l’équation n’est pas quadratique.
     * 
     * @param a coefficient de x² (doit être non nul)
     * @param b coefficient de x
     * @param c terme constant
     * @return un tableau de chaînes représentant les solutions de l’équation
     */
    public static String[] resoudreEquationQuadratique(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("Le coefficient a ne doit pas être 0 pour une équation quadratique.");
        }
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            // Deux solutions réelles distinctes
            double sol1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double sol2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new String[]{String.valueOf(sol1), String.valueOf(sol2)};
        } else if (discriminant == 0) {
            // Une solution réelle double
            double sol = -b / (2 * a);
            return new String[]{String.valueOf(sol)};
        } else {
            // Discriminant négatif : calcul des solutions complexes
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            String sol1 = String.format("%.4f + %.4fi", realPart, imaginaryPart);
            String sol2 = String.format("%.4f - %.4fi", realPart, imaginaryPart);
            return new String[]{sol1, sol2};
        }
    }

    /**
     * Méthode de test pour la fonction calculerRacineCarree.
     * On teste :
     * - Cas standards : 4, 9, 16
     * - Cas limites : 0, très petit (1e-10), très grand (1e10)
     * - Cas problématique : nombre négatif (-4)
     */
    public static void testCalculerRacineCarree() {
        System.out.println("=== Tests pour calculerRacineCarree ===");

        // Cas standards et limites
        double[] testCases = {4.0, 9.0, 16.0, 0.0, 1e-10, 1e10};
        for (double test : testCases) {
            long startTime = System.nanoTime();
            try {
                double result = calculerRacineCarree(test);
                long endTime = System.nanoTime();
                System.out.println("Test input: " + test + " => sqrt: " + result +
                        " | Temps d'exécution: " + (endTime - startTime) + " ns");
            } catch (IllegalArgumentException e) {
                long endTime = System.nanoTime();
                System.out.println("Test input: " + test + " => Exception: " + e.getMessage() +
                        " | Temps d'exécution: " + (endTime - startTime) + " ns");
            }
        }

        // Cas problématique : nombre négatif
        double negativeTest = -4.0;
        long startTime = System.nanoTime();
        try {
            double result = calculerRacineCarree(negativeTest);
            long endTime = System.nanoTime();
            System.out.println("Test input: " + negativeTest + " => sqrt: " + result +
                    " | Temps d'exécution: " + (endTime - startTime) + " ns");
        } catch (IllegalArgumentException e) {
            long endTime = System.nanoTime();
            System.out.println("Test input: " + negativeTest + " => Exception: " + e.getMessage() +
                    " | Temps d'exécution: " + (endTime - startTime) + " ns");
        }
    }

    /**
     * Méthode de test pour la fonction resoudreEquationQuadratique.
     * On teste :
     * - Discriminant positif : a=1, b=-5, c=6 (deux solutions réelles distinctes)
     * - Discriminant nul : a=1, b=2, c=1 (solution réelle double)
     * - Discriminant négatif : a=1, b=2, c=3 (solutions complexes)
     */
    public static void testResoudreEquationQuadratique() {
        System.out.println("\n=== Tests pour resoudreEquationQuadratique ===");

        // Tableau des cas de test : {a, b, c}
        double[][] testCases = {
            {1, -5, 6},  // Discriminant positif
            {1, 2, 1},   // Discriminant nul
            {1, 2, 3}    // Discriminant négatif
        };

        for (double[] params : testCases) {
            double a = params[0], b = params[1], c = params[2];
            long startTime = System.nanoTime();
            try {
                String[] solutions = resoudreEquationQuadratique(a, b, c);
                long endTime = System.nanoTime();
                System.out.print("Test équation: " + a + "x² + " + b + "x + " + c + " = 0 => Solutions: ");
                for (String sol : solutions) {
                    System.out.print(sol + " ");
                }
                System.out.println("| Temps d'exécution: " + (endTime - startTime) + " ns");
            } catch (IllegalArgumentException e) {
                long endTime = System.nanoTime();
                System.out.println("Test équation: " + a + "x² + " + b + "x + " + c +
                        " = 0 => Exception: " + e.getMessage() +
                        " | Temps d'exécution: " + (endTime - startTime) + " ns");
            }
        }
    }

    /**
     * Méthode main qui lance tous les tests.
     */
    public static void main(String[] args) {
        testCalculerRacineCarree();
        testResoudreEquationQuadratique();
    }
}


/*

TEXTE EXACT DU PROMPT :

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