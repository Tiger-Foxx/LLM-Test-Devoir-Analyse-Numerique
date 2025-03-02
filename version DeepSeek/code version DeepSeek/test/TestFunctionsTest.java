import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.util.stream.Stream;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;
import java.util.logging.SimpleFormatter;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe de test pour les fonctions de TestFunctions.
 * Cette classe utilise JUnit 5 pour tester les méthodes sqrt et solveQuadratic
 * selon les critères spécifiés.
 */
public class TestFunctionsTest {

    private static final Logger LOGGER = Logger.getLogger(TestFunctionsTest.class.getName());
    private static final double TOLERANCE = 1e-6;
    private long startTime;
    private Handler consoleHandler;

    @BeforeEach
    void setUp() {
        // Configuration du logger pour les tests
        LOGGER.setLevel(Level.INFO);
        consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(consoleHandler);
        
        // Enregistrement du temps de début
        startTime = System.nanoTime();
        LOGGER.info("Démarrage du test");
    }

    @AfterEach
    void tearDown() {
        // Calcul et affichage du temps d'exécution
        long duration = System.nanoTime() - startTime;
        LOGGER.info(String.format("Temps d'exécution: %d ns", duration));
        LOGGER.removeHandler(consoleHandler);
    }

    /**
     * Tests pour la fonction de calcul de racine carrée.
     */
    @Nested
    @DisplayName("Tests pour la fonction sqrt")
    class SqrtTests {

        @ParameterizedTest(name = "Test #{index}: sqrt({0}) = {1}")
        @MethodSource("sqrtTestCases")
        void testSqrtWithValidInputs(double input, double expected) {
            LOGGER.info(String.format("Test de sqrt(%.10f), résultat attendu: %.10f", input, expected));
            double result = TestFunctions.sqrt(input);
            assertEquals(expected, result, TOLERANCE, 
                String.format("La racine carrée de %.10f devrait être %.10f", input, expected));
            LOGGER.info(String.format("Résultat obtenu: %.10f, test réussi", result));
        }

        // Données pour les tests paramétrés de sqrt
        static Stream<Arguments> sqrtTestCases() {
            return Stream.of(
                // Test des carrés parfaits
                Arguments.of(4.0, 2.0),
                Arguments.of(9.0, 3.0),
                Arguments.of(16.0, 4.0),
                // Test de zéro
                Arguments.of(0.0, 0.0),
                // Test d'un très petit nombre positif
                Arguments.of(1.0e-8, 1.0e-4),
                // Test d'un très grand nombre
                Arguments.of(1.0e20, 1.0e10),
                // Test de nombres non carrés parfaits
                Arguments.of(2.0, 1.4142135623730951),
                Arguments.of(3.0, 1.7320508075688772),
                Arguments.of(0.5, 0.7071067811865475)
            );
        }

        @Test
        @DisplayName("Test de sqrt avec une entrée négative")
        void testSqrtWithNegativeInput() {
            double input = -1.0;
            LOGGER.info(String.format("Test de sqrt(%.1f), une exception est attendue", input));
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                TestFunctions.sqrt(input);
            });
            
            String expectedMessage = "La racine carrée d'un nombre négatif n'est pas définie.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
            LOGGER.info("Exception correctement levée: " + actualMessage);
        }

        @Test
        @DisplayName("Test de sqrt avec un très petit nombre négatif")
        void testSqrtWithVerySmallNegativeInput() {
            double input = -1.0e-8;
            LOGGER.info(String.format("Test de sqrt(%.10e), une exception est attendue", input));
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                TestFunctions.sqrt(input);
            });
            
            String expectedMessage = "La racine carrée d'un nombre négatif n'est pas définie.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
            LOGGER.info("Exception correctement levée: " + actualMessage);
        }

        @Test
        @DisplayName("Test de sqrt avec NaN")
        void testSqrtWithNaN() {
            double input = Double.NaN;
            LOGGER.info("Test de sqrt(NaN), NaN est attendu");
            
            double result = TestFunctions.sqrt(input);
            assertTrue(Double.isNaN(result));
            LOGGER.info("Résultat obtenu: NaN, test réussi");
        }

        @Test
        @DisplayName("Test de sqrt avec l'infini positif")
        void testSqrtWithPositiveInfinity() {
            double input = Double.POSITIVE_INFINITY;
            LOGGER.info("Test de sqrt(+∞), +∞ est attendu");
            
            double result = TestFunctions.sqrt(input);
            assertTrue(Double.isInfinite(result) && result > 0);
            LOGGER.info("Résultat obtenu: +∞, test réussi");
        }

        @Test
        @DisplayName("Test de sqrt avec l'infini négatif")
        void testSqrtWithNegativeInfinity() {
            double input = Double.NEGATIVE_INFINITY;
            LOGGER.info("Test de sqrt(-∞), une exception est attendue");
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                TestFunctions.sqrt(input);
            });
            
            String expectedMessage = "La racine carrée d'un nombre négatif n'est pas définie.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
            LOGGER.info("Exception correctement levée: " + actualMessage);
        }
    }

    /**
     * Tests pour la fonction de résolution d'équation quadratique.
     */
    @Nested
    @DisplayName("Tests pour la fonction solveQuadratic")
    class QuadraticTests {

        // Fonction helper pour comparer deux tableaux de solutions
        private void assertSolutionsEqual(double[] expected, double[] actual, double tolerance) {
            if (expected == null || actual == null) {
                assertEquals(expected, actual, "L'un des tableaux est null, l'autre non.");
                return;
            }
            
            assertEquals(expected.length, actual.length, "Les tableaux n'ont pas la même longueur");
            
            // Trier les tableaux pour une comparaison correcte
            Arrays.sort(expected);
            Arrays.sort(actual);
            
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], actual[i], tolerance, 
                    String.format("La solution #%d diffère: attendu=%.10f, obtenu=%.10f", i, expected[i], actual[i]));
            }
        }

        @ParameterizedTest(name = "Test #{index}: solveQuadratic({0}, {1}, {2}) = {3}")
        @MethodSource("quadraticTestCases")
        void testQuadraticWithValidInputs(double a, double b, double c, double[] expected) {
            LOGGER.info(String.format("Test de solveQuadratic(%.2f, %.2f, %.2f), résultat attendu: %s", 
                a, b, c, Arrays.toString(expected)));
            
            double[] result = TestFunctions.solveQuadratic(a, b, c);
            assertSolutionsEqual(expected, result, TOLERANCE);
            
            LOGGER.info(String.format("Résultat obtenu: %s, test réussi", Arrays.toString(result)));
        }

        // Données pour les tests paramétrés de solveQuadratic
        static Stream<Arguments> quadraticTestCases() {
            return Stream.of(
                // Deux solutions réelles
                Arguments.of(1.0, -5.0, 6.0, new double[]{3.0, 2.0}),
                // Solution unique
                Arguments.of(1.0, 2.0, 1.0, new double[]{-1.0, -1.0}),
                // a négatif
                Arguments.of(-1.0, 2.0, 1.0, new double[]{-1.0 - Math.sqrt(2), -1.0 + Math.sqrt(2)}),
                // b et c négatifs
                Arguments.of(1.0, -2.0, -1.0, new double[]{-0.414213562373095, 2.414213562373095}),
                // cas extrêmes avec a très grand
                Arguments.of(1e8, 2.0, 1.0, new double[]{-1.0e-8 - Math.sqrt(1.0e-16 - 1.0e-8), -1.0e-8 + Math.sqrt(1.0e-16 - 1.0e-8)}),
                // cas extrêmes avec b très grand
                Arguments.of(1.0, 1e8, 1.0, new double[]{-1.0e8 + 0.00001, -1.0e-8}),
                // Cas avec a très proche de zéro (mais non nul)
                Arguments.of(1e-8, 2.0, 1.0, new double[]{-1.0e8, -0.5}),
                // Cas avec c très proche de zéro
                Arguments.of(1.0, 2.0, 1e-8, new double[]{-2.0, 0.0}),
                // b avec valeur décimale
                Arguments.of(1.0, -1.0/3.0, 1.0, new double[]{(-1.0/3.0 - Math.sqrt(1.0/9.0 - 4.0))/2.0, (-1.0/3.0 + Math.sqrt(1.0/9.0 - 4.0))/2.0})
            );
        }

        @Test
        @DisplayName("Test de solveQuadratic avec a = 0")
        void testQuadraticWithZeroA() {
            double a = 0.0, b = 2.0, c = 1.0;
            LOGGER.info(String.format("Test de solveQuadratic(%.1f, %.1f, %.1f), une exception est attendue", a, b, c));
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                TestFunctions.solveQuadratic(a, b, c);
            });
            
            String expectedMessage = "a ne peut pas être nul pour une équation quadratique.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
            LOGGER.info("Exception correctement levée: " + actualMessage);
        }

        @Test
        @DisplayName("Test de solveQuadratic avec discriminant négatif")
        void testQuadraticWithNegativeDiscriminant() {
            double a = 1.0, b = 2.0, c = 3.0;
            LOGGER.info(String.format("Test de solveQuadratic(%.1f, %.1f, %.1f), une exception est attendue car le discriminant est négatif", a, b, c));
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                TestFunctions.solveQuadratic(a, b, c);
            });
            
            String expectedMessage = "Discriminant négatif : pas de solution réelle.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
            LOGGER.info("Exception correctement levée: " + actualMessage);
        }

        @Test
        @DisplayName("Test de solveQuadratic avec discriminant négatif (cas 1,1,1)")
        void testQuadraticWithNegativeDiscriminant2() {
            double a = 1.0, b = 1.0, c = 1.0;
            LOGGER.info(String.format("Test de solveQuadratic(%.1f, %.1f, %.1f), une exception est attendue car le discriminant est négatif", a, b, c));
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                TestFunctions.solveQuadratic(a, b, c);
            });
            
            String expectedMessage = "Discriminant négatif : pas de solution réelle.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
            LOGGER.info("Exception correctement levée: " + actualMessage);
        }

        @Test
        @DisplayName("Test de solveQuadratic avec b très proche de zéro")
        void testQuadraticWithBNearZero() {
            double a = 1.0, b = 1e-8, c = 1.0;
            LOGGER.info(String.format("Test de solveQuadratic(%.1f, %.10e, %.1f), une exception est attendue (discriminant négatif)", a, b, c));
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                TestFunctions.solveQuadratic(a, b, c);
            });
            
            String expectedMessage = "Discriminant négatif : pas de solution réelle.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
            LOGGER.info("Exception correctement levée: " + actualMessage);
        }

        @Test
        @DisplayName("Test de solveQuadratic avec c très grand")
        void testQuadraticWithLargeC() {
            double a = 1.0, b = 2.0, c = 1e8;
            LOGGER.info(String.format("Test de solveQuadratic(%.1f, %.1f, %.10e), une exception est attendue (discriminant négatif)", a, b, c));
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                TestFunctions.solveQuadratic(a, b, c);
            });
            
            String expectedMessage = "Discriminant négatif : pas de solution réelle.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
            LOGGER.info("Exception correctement levée: " + actualMessage);
        }
    }
}

