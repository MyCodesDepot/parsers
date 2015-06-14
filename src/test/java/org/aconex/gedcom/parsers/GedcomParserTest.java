package org.aconex.gedcom.parsers;

import org.aconex.gedcom.Exceptions.UnableToParseInput;
import org.aconex.gedcom.models.IdLine;
import org.aconex.gedcom.models.Line;
import org.aconex.gedcom.models.TagLine;
import org.aconex.gedcom.parsers.blocks.IdLineParser;
import org.aconex.gedcom.parsers.blocks.LineParser;
import org.aconex.gedcom.parsers.blocks.TagLineParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GedcomParserTest {

    @Test
    public void shouldParseInput() throws Exception {
        List<Line> parsedInput = new GedcomParser().parseInput(createProperInputBlock(), loadAllParsers());
        List<Line> expected = expectedList();
        assertEquals(expected.size(), parsedInput.size());
        assertEquals(expected, parsedInput);
    }

    @Test(expected = UnableToParseInput.class)
    public void shouldThrowExceptionForImproperTagLine() throws Exception {
        List<String> input = new ArrayList<>();
        input.add("0 @I0001@ INDI");
        input.add("1 NAMEElizabeth Alexandra Mary /Windsor/");
        input.add("1 SEX F");
        new GedcomParser().parseInput(input, loadAllParsers());
    }

    @Test(expected = UnableToParseInput.class)
    public void shouldThrowExceptionForImproperIdLine() throws Exception {
        List<String> input = new ArrayList<>();
        input.add("0 @I0001@");
        input.add("1 NAME Elizabeth Alexandra Mary /Windsor/");
        input.add("1 SEX F");
        new GedcomParser().parseInput(input, loadAllParsers());
    }

    private List<Line> expectedList() {
        List<Line> lines = new ArrayList<>();
        lines.add(new IdLine(0, "@I0001@", "INDI"));
        lines.add(new TagLine(1, "NAME", "Elizabeth Alexandra Mary /Windsor/"));
        lines.add(new TagLine(1, "SEX", "F"));
        lines.add(new TagLine(1, "BIRT", null));
        lines.add(new TagLine(2, "DATE", "21 Apr 1926"));
        lines.add(new TagLine(2, "PLAC", "17 Bruton Street, London, W1"));
        lines.add(new TagLine(1, "OCCU", "Queen"));
        lines.add(new TagLine(1, "FAMC", "@F0003@"));
        lines.add(new TagLine(1, "FAMS", "@F0001@"));
        lines.add(new TagLine(1, "NOTE", "@N0002@"));
        lines.add(new TagLine(1, "CHAN", null));
        lines.add(new TagLine(2, "DATE", "13 Dec 200"));
        lines.add(new IdLine(0, "@S0001@", "SOUR"));
        lines.add(new TagLine(1, "AUTH", "glamis family"));
        lines.add(new TagLine(1, "PUBL", "Glamis Guide"));
        lines.add(new TagLine(0, "TRLR", null));
        return lines;
    }

    private List<String> createProperInputBlock() {
        List<String> input = new ArrayList<>();
        input.add("0 @I0001@ INDI");
        input.add("1 NAME Elizabeth Alexandra Mary /Windsor/");
        input.add("1 SEX F");
        input.add("1 BIRT");
        input.add("2 DATE 21 Apr 1926");
        input.add("2 PLAC 17 Bruton Street, London, W1");
        input.add("1 OCCU Queen");
        input.add("1 FAMC @F0003@");
        input.add("1 FAMS @F0001@");
        input.add("1 NOTE @N0002@");
        input.add("1 CHAN");
        input.add("2 DATE 13 Dec 200");
        input.add("0 @S0001@ SOUR");
        input.add("1 AUTH glamis family");
        input.add("1 PUBL Glamis Guide");
        input.add("0 TRLR");
        return input;
    }

    private List<LineParser> loadAllParsers() {
        List<LineParser> parsers = new ArrayList<>();
        parsers.add(new IdLineParser());
        parsers.add(new TagLineParser());
        return parsers;
    }
}