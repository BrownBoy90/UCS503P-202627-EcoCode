package com.ecocode;
import com.ecocode.project.JavaFileInfo;
import com.ecocode.project.ProjectIndexer;

import java.nio.file.Path;
import java.util.List;

// Main Won't care how scanning works it is abstracted by ProjectIndexer

public class Main {

    public static void main(String[] args) {

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

            System.out.println(
                    "File Name: " + file.getFileName()
            );
            System.out.println(
                    "Path: " + file.getPath()
            );

            System.out.println(
                    "Line Count: " + file.getLineCount()
            );

            System.out.println(
                    "Test File: " + file.isTestFile()
            );

            System.out.println();

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