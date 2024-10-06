package fr.istic.vv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

public class ReportGenerator {

    private final Map<String, Set<String>> fieldsWithoutGetters;

    public ReportGenerator(Map<String, Set<String>> fieldsWithoutGetters) {
        this.fieldsWithoutGetters = fieldsWithoutGetters;
    }

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
