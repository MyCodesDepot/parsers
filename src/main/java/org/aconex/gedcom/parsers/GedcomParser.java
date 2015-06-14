package org.aconex.gedcom.parsers;

import org.aconex.gedcom.Exceptions.UnableToParseInput;
import org.aconex.gedcom.models.Line;
import org.aconex.gedcom.parsers.blocks.LineParser;

import java.util.ArrayList;
import java.util.List;

public class GedcomParser {

    public List<Line> parseInput(List<String> input, List<LineParser> lineParsers) {
        List<Line> lines = new ArrayList<>();
        for (String s : input) {
            boolean isParsed = false;
            for (LineParser lineParser : lineParsers) {
                Line line = lineParser.parseIfMatches(s);
                if (null != line) {
                    lines.add(line);
                    isParsed = true;
                    break;
                }
            }
            if (!isParsed) {
                throw new UnableToParseInput(String.format("Cannot parse input line number %s : [%s]", input.indexOf(s) + 1, s));
            }
        }
        return lines;
    }
}
