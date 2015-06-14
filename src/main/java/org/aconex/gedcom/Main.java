package org.aconex.gedcom;

import org.aconex.gedcom.Exceptions.SourceFileCannotBeOverritten;
import org.aconex.gedcom.helpers.Node;
import org.aconex.gedcom.models.Line;
import org.aconex.gedcom.parsers.GedcomParser;
import org.aconex.gedcom.parsers.ParserLoader;
import org.aconex.gedcom.parsers.blocks.LineParser;
import org.aconex.gedcom.processors.InputProcessor;
import org.aconex.gedcom.processors.OutputProcessor;
import org.aconex.gedcom.utils.Input;
import org.aconex.gedcom.utils.Output;

import java.io.IOException;
import java.util.List;

public class Main {

    public static final String INPUT = "input";
    public static final String OUTPUT = "output";

    public static void main(String[] args) {
        try {
            String inputFilename = Input.readFilename(INPUT);
            String outputFilename = Input.readFilename(OUTPUT);
            convertGedcomToXml(inputFilename, outputFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertGedcomToXml(String inputFilename, String outputFilename) throws IOException {
        if (inputFilename.equals(outputFilename)) {
            throw new SourceFileCannotBeOverritten("Input & output files cannot be same!");
        }
        List<String> gedcom = Input.readFile(inputFilename);
        List<LineParser> parsers = new ParserLoader().getAllParsers();
        List<Line> parsedInput = new GedcomParser().parseInput(gedcom, parsers);
        Node xmlTrie = new InputProcessor().process(parsedInput);
        List<String> output = new OutputProcessor().process(xmlTrie);
        Output.writeFile(output, outputFilename);
    }
}
