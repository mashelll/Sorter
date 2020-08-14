package com.mycompany.app;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser();
        Map<String,String> argsMap = parser.parse(args);

        String inputFileName = argsMap.get("inputFileName");
        String outputFileName = argsMap.get("outputFileName");

        Reader reader = new ReaderFromTXTFile();
        Writer writer = new WriterToTXTFile();

        List<String> strings = reader.read(inputFileName);

        String outputString;
        List<String> outputStrings = new ArrayList<>();
        for (String string : strings) {
            TermsTree tree = new TermsTree(string);
            outputString = tree.sort();
            outputStrings.add(outputString);
        }
        writer.write(outputFileName, outputStrings);
    }
}
