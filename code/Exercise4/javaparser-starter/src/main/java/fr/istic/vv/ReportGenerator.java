package fr.istic.vv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

public class ReportGenerator {

    private final Map<String, Set<String>> fieldsWithoutGetters;

     /**
     * Constructor to initialize the ReportGenerator with the given map of fields without getters
     * @param fieldsWithoutGetters : map containing class names as keys and sets of field names
     */
    public ReportGenerator(Map<String, Set<String>> fieldsWithoutGetters) {
        this.fieldsWithoutGetters = fieldsWithoutGetters;
    }

    /**
     * Generates a CSV report of the fields without getters for each class.
     * @throws IOException If there is an error while writing the report to the file.
     */
    public void generateReport() throws IOException {
        String reportPath = Paths.get("").toAbsolutePath().toString() + "/report.csv";
        try (FileWriter csvWriter = new FileWriter(reportPath)) {
            csvWriter.append("Class,Field\n");
            for (Map.Entry<String, Set<String>> entry : fieldsWithoutGetters.entrySet()) {
                for (String field : entry.getValue()) {
                    csvWriter.append(entry.getKey()).append(",").append(field).append("\n");
                }
            }
        }
        System.out.println("Report generated: " + reportPath);
    }
}
