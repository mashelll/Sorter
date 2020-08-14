package com.mycompany.app;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderFromTXTFile implements Reader {
    @Override
    public List<String> read(String fileName) {
        List<String> strings = new ArrayList<>();
        try (FileReader reader = new FileReader(fileName)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                strings.add(scan.nextLine());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return strings;
    }
}
