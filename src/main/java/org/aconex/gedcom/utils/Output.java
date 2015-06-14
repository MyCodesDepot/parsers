package org.aconex.gedcom.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Output {
    public static void writeFile(List<String> strings, String outputFilename) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFilename);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (String string : strings) {
            bufferedWriter.write(string + '\n');
        }
        bufferedWriter.close();
        fileWriter.close();
    }
}
