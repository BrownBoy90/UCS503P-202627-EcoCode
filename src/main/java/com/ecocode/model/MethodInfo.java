package com.ecocode.model;

public class MethodInfo {
    private final String name;
    private final String returnType;
    private final String signature;
    private final int lineNumber;

    public MethodInfo(
            String name,
            String returnType,
            String signature,
            int lineNumber) {
        this.name = name;
        this.returnType = returnType;
        this.signature = signature;
        this.lineNumber = lineNumber;
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getSignature() {
        return signature;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
