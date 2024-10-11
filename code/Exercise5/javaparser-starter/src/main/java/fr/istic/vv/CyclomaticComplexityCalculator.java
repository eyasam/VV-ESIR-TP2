package fr.istic.vv;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.stmt.*;

import java.io.IOException;

public class CyclomaticComplexityCalculator extends VoidVisitorAdapter<Void> {
    private int complexity;
    private ReportGenerator report;

    /**
     * Constructor to initialize the CyclomaticComplexityCalculator with a report generator
     * @param report 
     */
    public CyclomaticComplexityCalculator(ReportGenerator report) {
        this.report = report;
    }

      /**
     * Visits a method declaration and calculates its cyclomatic complexity.
     * @param method The method declaration being visited.
     * @param arg Additional argument
     */
    @Override
    public void visit(MethodDeclaration method, Void arg) {
    	
        complexity = calculateCyclomaticComplexity(method);

        String packageName = method.findCompilationUnit()
                .flatMap(unit -> unit.getPackageDeclaration())
                .map(pd -> pd.getName().toString())
                .orElse("[Unknown Package]");
        String className = method.findAncestor(ClassOrInterfaceDeclaration.class)
                .map(ClassOrInterfaceDeclaration::getNameAsString)
                .orElse("[Anonymous Class]");
        String methodName = method.getNameAsString();
        String parameters = method.getParameters().toString();

        try {
            report.writeMethodInfo(packageName, className, methodName, parameters, complexity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     /**
     * Calculates the cyclomatic complexity of a method (based on control flow statements)
     * @param method The method whose cyclomatic complexity is being calculated.
     * @return The cyclomatic complexity of the method.
     */
    private int calculateCyclomaticComplexity(MethodDeclaration method) {
        complexity = 1; 
        
        // Complexity for each control structure
        complexity += method.findAll(IfStmt.class).size();
        complexity += method.findAll(ForStmt.class).size();
        complexity += method.findAll(WhileStmt.class).size();
        complexity += method.findAll(CatchClause.class).size();

        // Complexity for each switch (case)
        method.findAll(SwitchStmt.class).forEach(switchStmt -> complexity += switchStmt.getEntries().size());

        return complexity;
    }
}
