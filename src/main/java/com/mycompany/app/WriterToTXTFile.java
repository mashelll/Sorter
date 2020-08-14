package com.mycompany.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterToTXTFile implements Writer {
    @Override
    public void write(String fileName, List<String> strings) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String string : strings) {
                writer.write(string + '\n');
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
