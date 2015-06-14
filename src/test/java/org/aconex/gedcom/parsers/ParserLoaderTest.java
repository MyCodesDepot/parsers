package org.aconex.gedcom.parsers;

import org.aconex.gedcom.parsers.blocks.LineParser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserLoaderTest {

    @Test
    public void shouldGetAllTheParsers() throws Exception {
        ArrayList<LineParser> allParsers = new ParserLoader().getAllParsers();
        assertEquals(2, allParsers.size());
        assertTrue(allParsers.get(0) instanceof LineParser);
        assertTrue(allParsers.get(1) instanceof LineParser);
    }
}