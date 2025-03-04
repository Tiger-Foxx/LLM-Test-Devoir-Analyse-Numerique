import java.util.Arrays;

public class TestFunctions {

    
    public static double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("La racine carrée d’un nombre négatif n’est pas définie.");
        }
        return Math.sqrt(x);
    }

    
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

    
    public static void main(String[] args) {
        
        System.out.println("=== Tests pour la fonction sqrt ===");
        testSqrt(4, 2.0);
        testSqrt(9, 3.0);
        testSqrt(16, 4.0);
        testSqrt(0, 0.0);
        testSqrt(1e-10, 1e-5);
        testSqrt(1e20, 1e10);
        testSqrt(-1, Double.NaN); 
        System.out.println();

        
        System.out.println("=== Tests pour la fonction solveQuadratic ===");
        testQuadratic(1, -3, 2, new double[]{2.0, 1.0}); 
        testQuadratic(1, 2, 1, new double[]{-1.0, -1.0}); 
        testQuadratic(1, 2, 5, null); 
        testQuadratic(0, 2, 3, null); 
    }

    
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

