package fr.istic.vv;

import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator {

    private FileWriter csvWriter;

    /**
     * Constructor to initialize the ReportGenerator and create a CSV file for writing the report
     * @param fileName The name of the CSV file to which the report will be written
     * @throws IOException If an error occurs while creating or writing to the file
     */
    public ReportGenerator(String fileName) throws IOException {
        csvWriter = new FileWriter(fileName);
        csvWriter.append("Package,Class,Method,Parameters,CC\n");
    }

      /**
     * Writes the method information to the CSV report.
     * 
     * @param packageName The name of the package containing the method
     * @param className The name of the class containing the method
     * @param methodName The name of the method
     * @param parameters The parameters of the method
     * @param complexity The cyclomatic complexity of the method
     * @throws IOException If an error occurs while writing to the file.
     */
    public void writeMethodInfo(String packageName, String className, String methodName, String parameters, int complexity) throws IOException {
        csvWriter.append(String.format("%s,%s,%s,%s,%d\n", packageName, className, methodName, parameters, complexity));
    }

    /**
     * Closes the FileWriter and flushes any remaining data to the CSV file
     * @throws IOException If an error occurs while closing the file
     */
    public void close() throws IOException {
        csvWriter.flush();
        csvWriter.close();
    }
}
