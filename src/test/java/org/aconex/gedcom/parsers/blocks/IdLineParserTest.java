package org.aconex.gedcom.parsers.blocks;

import org.aconex.gedcom.models.IdLine;
import org.aconex.gedcom.models.Line;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class IdLineParserTest {
    IdLineParser idLineParser = new IdLineParser();

    @Test
    public void shouldMatchAndGetIdLineObject() throws Exception {
        String line = "0 @I0001@ INDI";
        Line idLine = idLineParser.parseIfMatches(line);
        assertEquals(0, idLine.getLevel());
        assertEquals("@I0001@", idLine.getTagOrId());
        assertEquals("INDI", idLine.getData());
        assertTrue(idLine instanceof IdLine);
    }

    @Test
    public void shouldReturnNull() throws Exception {
        String line = "1 NAME Elizabeth Alexandra Mary /Windsor/";
        assertNull(idLineParser.parseIfMatches(line));
        line = "1 nAME Elizabeth Alexandra Mary /Windsor/";
        assertNull(idLineParser.parseIfMatches(line));
        line = "0 @I0001@INDI";
        assertNull(idLineParser.parseIfMatches(line));
    }
}