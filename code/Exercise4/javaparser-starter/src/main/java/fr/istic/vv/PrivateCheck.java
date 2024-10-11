package fr.istic.vv;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrivateCheck extends VoidVisitorAdapter<Void> {

    private final Map<String, Set<String>> fieldsWithoutGetters = new HashMap<>();

    /**
     * Visits a class declaration and identifies private fields that do not have public getter methods
     * @param clazz : The class declaration being visited.
     * @param arg: Additional argument
     */
    @Override
    public void visit(ClassOrInterfaceDeclaration clazz, Void arg) {
        // only public classes
        if (!clazz.isPublic()) return;

        String className = clazz.getFullyQualifiedName().orElse("[Anonymous]");
        Set<String> privateFields = new HashSet<>();
        Set<String> publicGetters = new HashSet<>();

        // Find private fields
        clazz.getFields().forEach(field -> {
            if (field.isPrivate()) {
                field.getVariables().forEach(variable -> privateFields.add(variable.getNameAsString()));
            }
        });

        // Find public getter methods and xtract field name 
        clazz.getMethods().forEach(method -> {
            if (method.isPublic() && method.getParameters().isEmpty() && method.getNameAsString().startsWith("get")) {
                publicGetters.add(method.getNameAsString().substring(3)); 
            }
        });

        // Identify private fields without a getter
        Set<String> fieldsWithNoGetter = new HashSet<>(privateFields);
        fieldsWithNoGetter.removeIf(publicGetters::contains);

        // Add class to the map if any private fields lack a getter
        if (!fieldsWithNoGetter.isEmpty()) {
            fieldsWithoutGetters.put(className, fieldsWithNoGetter);
        }
    }

    
    /**
     * Retrieves the map of classes and their corresponding private fields without getter
     * @return A map where the key is the class name and the value is a set of private fields without getters.
     */
    public Map<String, Set<String>> getFieldsWithoutGetters() {
        return fieldsWithoutGetters;
    }
}
