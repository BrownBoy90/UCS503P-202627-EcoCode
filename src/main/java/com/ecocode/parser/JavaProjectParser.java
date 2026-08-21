package com.ecocode.parser;

import com.ecocode.model.ClassInfo;
import com.ecocode.model.MethodInfo;
import com.github.javaparser.StaticJavaParser;// This is the main entry point for the library, you can use its methods to parse Java Code from a string, file or input stream

import com.github.javaparser.ast.CompilationUnit; // It represents absolute root of the parsed java file, it acts as the container for everything inside that specific file, incl. the package declaration, other imports, classes and interfaces.

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration; // Represents a specific class and its attributes

import com.github.javaparser.ast.body.MethodDeclaration; // Represents a specific method inside a class

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JavaProjectParser {
    public List<ClassInfo> parseFile(Path path) {
        List<ClassInfo> classes = new ArrayList<>();

        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(path);

            for (ClassOrInterfaceDeclaration declaration : compilationUnit.findAll(ClassOrInterfaceDeclaration.class)) {

                List<MethodInfo> methods = new ArrayList<>();
                for (MethodDeclaration method : declaration.getMethods()) {
                    MethodInfo methodInfo = new MethodInfo(method.getNameAsString(), method.getType().asString(), method.getDeclarationAsString(false, false, false), method.getBegin().map(position -> position.line).orElse(-1));
                    methods.add(methodInfo);
                }

                ClassInfo classInfo = new ClassInfo(declaration.getNameAsString(), methods);
                classes.add(classInfo);
            }

//            System.out.println("\nMethods:");

//            for (MethodDeclaration method : compilationUnit.findAll(MethodDeclaration.class)) {
//                System.out.println("- " + method.getDeclarationAsString(true, false, false));
//                System.out.println("Method: " + method.getNameAsString());
//                System.out.println("Return Type:" + method.getTypeAsString());
//                System.out.println("Signature:" + method.getDeclarationAsString(false, false, false));
//                System.out.println(method.getBegin());
//                method.getBegin().ifPresent(position -> System.out.println("Line: "+ position.line));
//                System.out.println();
//            }

        } catch (IOException e) {
            System.out.println("Could not parse:" + path);
        }
        return classes;
    }
}
