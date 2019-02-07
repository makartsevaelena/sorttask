import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SimpleSort {

    public static void main(String[] args) {
        List<String> lines = getInputFromFile("input.txt");
        HashSet<String> subDivisions = getSubdivisionsNames(lines);
        List<String> sortedCodes = subDivisions.
                stream().
                sorted(Comparator.reverseOrder()).
                collect(Collectors.toList());
        for (String code : sortedCodes) {
            System.out.println(code);
        }
    }

    /*
    Input data should look like this:
    “K1\SK1”
    “K1\SK2”
    “K1\SK1\SSK1”
    “K1\SK1\SSK2”
    “K2”
    “K2\SK1\SSK1”
    “K2\SK1\SSK2”
     */
    static List<String> getInputFromFile(String sampleFile) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(sampleFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    static HashSet<String> getSubdivisionsNames(List<String> lines) {
        HashSet<String> subDivisions = new HashSet<>();
        for (String line : lines) {
            line = line.replaceAll("“", "");
            line = line.replaceAll("”", "");
            String[] parts = line.split("\\\\");
            List<String> cumulativeName = new ArrayList<>();
            for (String part : parts) {
                cumulativeName.add(part);
                String formattedSubdivisionName = String.format("“%s”",
                        String.join("\\", cumulativeName)
                );
                subDivisions.add(formattedSubdivisionName);
            }
        }
        return subDivisions;
    }
}
