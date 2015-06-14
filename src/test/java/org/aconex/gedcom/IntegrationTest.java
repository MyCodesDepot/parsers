package org.aconex.gedcom;

import org.aconex.gedcom.Exceptions.SourceFileCannotBeOverritten;
import org.aconex.gedcom.Exceptions.UnableToParseInput;
import org.junit.After;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class IntegrationTest {

    @Test(expected = SourceFileCannotBeOverritten.class)
    public void shouldThrowExceptionIfInputAndOutputFilesAreSame() throws Exception {
        Main.convertGedcomToXml("./src/test/resources/ImproperInputFile.txt", "./src/test/resources/ImproperInputFile.txt");
    }

    @Test
    public void shouldCreateXmlFile() throws Exception {
        FileReader expectedOutputFile = new FileReader("./src/test/resources/ExpectedOutputFile.txt");
        Main.convertGedcomToXml("./src/test/resources/ProperInputFile.txt", "./src/test/resources/output.txt");
        FileReader actualOutputFile = new FileReader("./src/test/resources/output.txt");
        assertTrue(hasSameContent(expectedOutputFile, actualOutputFile));
    }

    @Test(expected = UnableToParseInput.class)
    public void shouldThrowExceptionForImproperInputFile() throws Exception {
        Main.convertGedcomToXml("./src/test/resources/ImproperInputFile.txt", "./src/test/resources/SomeFile.txt");
    }

    private boolean hasSameContent(FileReader expectedOutputFile, FileReader actualOutputFile) throws IOException {
        String line;
        List<String> expected = new ArrayList<>();
        List<String> actual = new ArrayList<>();
        BufferedReader bufferedReaderExpected = new BufferedReader(expectedOutputFile);
        while((line = bufferedReaderExpected.readLine()) != null) {
            expected.add(line);
        }
        BufferedReader bufferedReaderActual = new BufferedReader(actualOutputFile);
        while((line = bufferedReaderActual.readLine()) != null) {
            actual.add(line);
        }
        return expected.equals(actual);
    }

    @After
    public void tearDown() throws Exception {
        try {
            File file = new File("./src/test/resources/output.txt");
            if (file.exists() && file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}