package org.aconex.gedcom.parsers.blocks;

import org.aconex.gedcom.models.Line;
import org.aconex.gedcom.models.TagLine;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TagLineParserTest {
    private TagLineParser tagLineParser = new TagLineParser();

    @Test
    public void shouldMatchAndGetTagLineObjectWithAllTheFields() throws Exception {
        String line = "1 NAME Elizabeth Alexandra Mary /Windsor/";
        Line tagLine = tagLineParser.parseIfMatches(line);
        assertEquals(1, tagLine.getLevel());
        assertEquals("NAME", tagLine.getTagOrId());
        assertEquals("Elizabeth Alexandra Mary /Windsor/", tagLine.getData());
        assertTrue(tagLine instanceof TagLine);
    }

    @Test
    public void shouldMatchAndGetTagLineObjectWithBlankValue() throws Exception {
        String line = "1 BIRT ";
        Line tagLine = tagLineParser.parseIfMatches(line);
        assertEquals(1, tagLine.getLevel());
        assertEquals("BIRT", tagLine.getTagOrId());
        assertNull(tagLine.getData());
        assertTrue(tagLine instanceof TagLine);
    }

    @Test
    public void shouldReturnNull() throws Exception {
        String line = "0 @I0001@ INDI";
        assertNull(tagLineParser.parseIfMatches(line));
        line = "1 nAME Elizabeth Alexandra Mary /Windsor/";
        assertNull(tagLineParser.parseIfMatches(line));
        line = "1 NAMEElizabeth Alexandra Mary /Windsor/";
        assertNull(tagLineParser.parseIfMatches(line));
    }
}