package com.mycompany.app;

import java.util.HashMap;
import java.util.Map;

public class ArgumentParser {
    public Map<String,String> parse(String[] args) {
        Map<String,String> argsMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i")) {
                argsMap.put("inputFileName", args[i + 1]);
            }
            if (args[i].equals("-o")) {
                argsMap.put("outputFileName", args[i + 1]);
            }
        }
        return argsMap;
    }
}
