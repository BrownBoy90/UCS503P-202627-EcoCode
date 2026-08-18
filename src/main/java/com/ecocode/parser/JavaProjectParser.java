package com.ecocode.parser;

import com.github.javaparser.StaticJavaParser;// This is the main entry point for the library, you can use its methods to parse Java Code from a string, file or input stream

import com.github.javaparser.ast.CompilationUnit; // It represents absolute root of the parsed java file, it acts as the container for everything inside that specific file, incl. the package declaration, other imports, classes and interfaces.

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration; // Represents a specific class and its attributes

import com.github.javaparser.ast.body.MethodDeclaration; // Represents a specific method inside a class

import java.io.IOException;
import java.nio.file.Path;

public class JavaProjectParser {
    public void parseFile(Path path) {
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(path);

            System.out.println("Parsing: " + path.getFileName());

            System.out.println("\nClasses:");

            for (ClassOrInterfaceDeclaration declaration : compilationUnit.findAll(ClassOrInterfaceDeclaration.class)) {

            }

        } catch (IOException e) {
            System.out.println("Could not parse:" +path);
        }
    }
}
