package com.ecocode.model;

import java.util.ArrayList;
import java.util.List;

public class ClassInfo {
    private final String name;
    private final List<MethodInfo> methods;

    public ClassInfo(String name, List<MethodInfo> methods) {
        this.name = name;
        this.methods = methods;
    }

    public String getName() {
        return name;
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void addMethod(MethodInfo method) {
        methods.add(method);
    }
}
