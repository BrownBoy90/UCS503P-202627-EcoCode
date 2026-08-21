package com.ecocode;
import com.ecocode.model.ClassInfo;
import com.ecocode.model.MethodInfo;
import com.ecocode.project.JavaFileInfo;
import com.ecocode.project.ProjectIndexer;
import com.ecocode.parser.JavaProjectParser;
import java.nio.file.Path;
import java.util.List;

// Main Won't care how scanning works it is abstracted by ProjectIndexer

public class Main {

    public static void main(String[] args) {

        JavaProjectParser parser = new JavaProjectParser();
        Path projectPath =
                Path.of("/Users/gurnoorsingh/Documents/EcoCodeTestProject");

        ProjectIndexer indexer =
                new ProjectIndexer();

        List<JavaFileInfo> files =
                indexer.indexProject(projectPath);

        int productionFiles = 0;
        int testFiles = 0;
        long totalLines = 0;

        for (JavaFileInfo file : files) {
            if(file.getFileName().equals("JsonObject.java")) {
                List<ClassInfo> classes = parser.parseFile(file.getPath());

                System.out.println("Parsing: " + file.getFileName());
                for(ClassInfo classInfo : classes) {
                    System.out.println("\nClass: " + classInfo.getName());

                    for(MethodInfo method : classInfo.getMethods()){
                        System.out.println("Method: " + method.getName());
                        System.out.println("Return Type: " + method.getReturnType());
                        System.out.println("Signature: " + method.getSignature());
                        System.out.println("Line: " + method.getLineNumber());
                        System.out.println();
                    }
                }
            }
//            System.out.println(
//                    "File Name: " + file.getFileName()
//            );
//            System.out.println(
//                    "Path: " + file.getPath()
//            );
//
//            System.out.println(
//                    "Line Count: " + file.getLineCount()
//            );
//
//            System.out.println(
//                    "Test File: " + file.isTestFile()
//            );
//
//            System.out.println();

            totalLines += file.getLineCount();

            if (file.isTestFile()) {
                testFiles++;
            } else {
                productionFiles++;
            }
        }

        System.out.println(
                "Total Java files: " + files.size()
        );

        System.out.println(
                "Production files: " + productionFiles
        );

        System.out.println(
                "Test files: " + testFiles
        );

        System.out.println(
                "Total lines: " + totalLines
        );


    }
}