package fr.istic.vv;

import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator {

    private FileWriter csvWriter;

    public ReportGenerator(String fileName) throws IOException {
        csvWriter = new FileWriter(fileName);
        csvWriter.append("Package,Class,Method,Parameters,CC\n");
    }

    public void writeMethodInfo(String packageName, String className, String methodName, String parameters, int complexity) throws IOException {
        csvWriter.append(String.format("%s,%s,%s,%s,%d\n", packageName, className, methodName, parameters, complexity));
    }

    public void close() throws IOException {
        csvWriter.flush();
        csvWriter.close();
    }
}
