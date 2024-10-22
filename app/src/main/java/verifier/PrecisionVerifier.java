package verifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PrecisionVerifier implements IVerifier {
    private final double tolerance;

    public PrecisionVerifier(double tolerance) {
        this.tolerance = tolerance;
    }

    @Override
    public boolean verify(File expectedFile, File actualFile) throws IOException {
        try (BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFile));
             BufferedReader actualReader = new BufferedReader(new FileReader(actualFile))) {

            String expectedLine;
            String actualLine;

            while ((expectedLine = expectedReader.readLine()) != null) {
                actualLine = actualReader.readLine();

                if (actualLine == null) {
                    return false;  // Sorties différentes
                }

                // Convertir les lignes en nombres réels et comparer leur différence
                try {
                    double expectedValue = Double.parseDouble(expectedLine.trim());
                    double actualValue = Double.parseDouble(actualLine.trim());

                    if (Math.abs(expectedValue - actualValue) > tolerance) {
                        return false;  // Différence supérieure à la tolérance
                    }

                } catch (NumberFormatException ignored){
                    //Nothing to see here ¯\_(ツ)_/¯
                }


            }

            return actualReader.readLine() == null;  // Vérifier si le fichier produit est plus long
        }
    }
}
