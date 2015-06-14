package org.aconex.gedcom.parsers;

import org.aconex.gedcom.parsers.blocks.IdLineParser;
import org.aconex.gedcom.parsers.blocks.LineParser;
import org.aconex.gedcom.parsers.blocks.TagLineParser;

import java.util.ArrayList;

public class ParserLoader {

    public ArrayList<LineParser> getAllParsers() {
        ArrayList<LineParser> lineParsers = new ArrayList<>();
        lineParsers.add(new IdLineParser());
        lineParsers.add(new TagLineParser());
        return lineParsers;
    }
}