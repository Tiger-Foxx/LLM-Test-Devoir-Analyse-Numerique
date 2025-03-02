import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestFunctionsTest {

    private static final double TOLERANCE = 1e-4; // Tolérance pour les comparaisons de flottants
    private static final Map<String, Double> featureMap = new HashMap<>();
    // Liste pour stocker les logs détaillés de chaque test
    private static final List<String> testLogs = new ArrayList<>();

    static {
        // Mappage des clés aux valeurs pour a
        featureMap.put("1a", 0.0);
        featureMap.put("1b", 1.0 / 3.0);
        featureMap.put("1c", -1.0 / 3.0);
        featureMap.put("1d", 1e-10);
        featureMap.put("1e", -1e-10);
        featureMap.put("1f", 1e10);
        featureMap.put("1g", -1e10);

        // Mappage pour b
        featureMap.put("2a", 0.0);
        featureMap.put("2b", 1.0 / 3.0);
        featureMap.put("2c", -1.0 / 3.0);
        featureMap.put("2d", 1e-10);
        featureMap.put("2e", -1e-10);
        featureMap.put("2f", 1e10);
        featureMap.put("2g", -1e10);

        // Mappage pour c
        featureMap.put("3a", 0.0);
        featureMap.put("3b", 1.0 / 3.0);
        featureMap.put("3c", -1.0 / 3.0);
        featureMap.put("3d", 1e-10);
        featureMap.put("3e", -1e-10);
        featureMap.put("3f", 1e10);
        featureMap.put("3g", -1e10);
    }

    @TestFactory
    Collection<DynamicTest> generateTestsFromFile() throws IOException {
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        // Lecture du fichier en UTF-16 (adapté à ton encodage)
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("test_cases.txt"), StandardCharsets.UTF_16))) {
            String line;
            int testNumber = 1;
            while ((line = br.readLine()) != null) {
                // Nettoyer la ligne (suppression des espaces en début/fin, du BOM, et des
                // caractères parasites)
                line = line.trim().replace("\uFEFF", "");
                if (line.isEmpty()) {
                    continue; // Ignorer les lignes vides
                }
                String[] features = line.split(" ");
                // Nettoyer chaque clé (suppression de "ÿþ" par exemple)
                for (int i = 0; i < features.length; i++) {
                    features[i] = features[i].replaceAll("ÿþ", "");
                }
                if (features.length != 3) {
                    throw new IllegalArgumentException("Chaque ligne doit contenir exactement 3 clés.");
                }

                // Vérification que les clés existent dans le mapping
                if (!featureMap.containsKey(features[0])) {
                    throw new IllegalArgumentException("Clé non trouvée pour a : " + features[0]);
                }
                if (!featureMap.containsKey(features[1])) {
                    throw new IllegalArgumentException("Clé non trouvée pour b : " + features[1]);
                }
                if (!featureMap.containsKey(features[2])) {
                    throw new IllegalArgumentException("Clé non trouvée pour c : " + features[2]);
                }

                double a = featureMap.get(features[0]);
                double b = featureMap.get(features[1]);
                double c = featureMap.get(features[2]);
                final int currentTestNumber = testNumber; // Copie de testNumber pour la lambda

                // Création d'un test dynamique pour cette ligne
                dynamicTests.add(DynamicTest.dynamicTest(
                        "Test #" + currentTestNumber + " (a=" + a + ", b=" + b + ", c=" + c + ")",
                        () -> {
                            StringBuilder logEntry = new StringBuilder();
                            logEntry.append("Test #").append(currentTestNumber)
                                    .append(" (a=").append(a)
                                    .append(", b=").append(b)
                                    .append(", c=").append(c).append("): ");

                            // Si a est nul, on attend une exception indiquant que l'équation n'est pas
                            // quadratique.
                            if (a == 0.0) {
                                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                                    TestFunctions.solveQuadratic(a, b, c);
                                }, "L'exception attendue pour a=0 n'a pas été levée.");
                                // Vérification que le message d'exception contient l'information attendue
                                assertTrue(exception.getMessage().contains("a ne peut pas être nul"),
                                        "Le message de l'exception n'est pas celui attendu.");
                                logEntry.append("Succès: exception correctement levée pour a=0.");
                            } else {
                                double discriminant = b * b - 4 * a * c;
                                if (discriminant < 0) {
                                    // Pour un discriminant négatif, on attend aucune solution réelle.
                                    double[] roots = TestFunctions.solveQuadratic(a, b, c);
                                    assertEquals(0, roots.length,
                                            "Aucune solution réelle attendue pour discriminant négatif.");
                                    logEntry.append("Succès: aucune solution réelle (discriminant = ")
                                            .append(discriminant).append(").");
                                } else {
                                    // Pour discriminant positif ou nul, on attend les solutions
                                    double[] roots = TestFunctions.solveQuadratic(a, b, c);
                                    // Vérifier la validité de chaque racine calculée
                                    for (double root : roots) {
                                        double result = a * root * root + b * root + c;
                                        assertTrue(Math.abs(result) < TOLERANCE,
                                                String.format("La racine %.10f ne satisfait pas l'équation : %.10f",
                                                        root, result));
                                        // logEntry.append("Echec ! : La racine " + root
                                        // + " ne satisfait pas l'équation : " + result);
                                    }
                                    if (discriminant > 0) {
                                        assertEquals(2, roots.length,
                                                "Deux racines attendues pour un discriminant positif.");
                                        logEntry.append("Succès: deux racines trouvées (discriminant = ")
                                                .append(discriminant).append(").");
                                    } else { // discriminant == 0
                                        assertEquals(1, roots.length,
                                                "Une racine double attendue pour un discriminant nul.");
                                        logEntry.append("Succès: une racine double trouvée (discriminant = 0).");
                                    }
                                }
                            }
                            // Ajouter le log du test dans la liste
                            synchronized (testLogs) {
                                testLogs.add(logEntry.toString());
                            }
                        }));
                testNumber++;
            }
        }
        return dynamicTests;
    }

    @AfterAll
    static void exportResults() {
        // Exporter tous les logs dans le fichier "test_results.txt"
        try (PrintWriter writer = new PrintWriter(new FileWriter("test_results.txt"))) {
            writer.println("Résultats détaillés des tests :");
            writer.println("================================");
            for (String log : testLogs) {
                writer.println(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
