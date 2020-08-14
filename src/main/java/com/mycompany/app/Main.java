package com.mycompany.app;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static ArrayList<String> readFromInputFile(String fileName) {
        ArrayList<String> strings = new ArrayList<>();

        try(FileReader reader = new FileReader(fileName)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                strings.add(scan.nextLine());
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return strings;
    }


    public static void wrightToOutputFile(String fileName, ArrayList<String> strings) {

        try( FileWriter writer = new FileWriter(fileName)){
            for (String string : strings) {
                writer.write(string + '\n');
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args)  {

        String inputFileName = args[1];
        String outputFileName = args[3];

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-i"))
                inputFileName = args[i + 1];

            if (args[i].equals("-o"))
                outputFileName = args[i + 1];
        }

        ArrayList<String> strings;
        strings = readFromInputFile(inputFileName);

        String outputString;
        ArrayList<String> outputStrings = new ArrayList<>();
        for (String string : strings) {
            TermsTree tree = new TermsTree(string);
            outputString = tree.sort();
            outputStrings.add(outputString);
        }
        wrightToOutputFile(outputFileName, outputStrings);
    }
}
