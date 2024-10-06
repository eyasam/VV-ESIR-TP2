package fr.istic.vv;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.stmt.*;

import java.io.IOException;

public class CyclomaticComplexityCalculator extends VoidVisitorAdapter<Void> {
    private int complexity;
    private ReportGenerator report;

    public CyclomaticComplexityCalculator(ReportGenerator report) {
        this.report = report;
    }

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
