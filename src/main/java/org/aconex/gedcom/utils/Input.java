package org.aconex.gedcom.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Input {
    public static List<String> readFile(String fullyQualifiedFilename) throws IOException {
        List<String> inputFile = new ArrayList<>();
        String line;
        FileReader fileReader = new FileReader(fullyQualifiedFilename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while((line = bufferedReader.readLine()) != null) {
            inputFile.add(line);
        }
        bufferedReader.close();
        fileReader.close();
        return inputFile;
    }

    public static String readFilename(String fileType) throws IOException {
        System.out.println(String.format("Enter fully qualified %s filename : ", fileType));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
