package org.aconex.gedcom.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IdLineTest {

    IdLine idLine;

    @Before
    public void setUp() throws Exception {
        idLine = new IdLine(0, "@kjk@", "data");
    }

    @Test
    public void shouldGetFormattedOpeningBlock() throws Exception {
        assertEquals("<data id=\"@kjk@\">", idLine.getOpeningBlock(true));
        assertEquals("<data id=\"@kjk@\">", idLine.getOpeningBlock(false));
    }

    @Test
    public void shouldGetFormattedClosingBlock() throws Exception {
        assertEquals("</data>", idLine.getClosingBlock());
    }

    @Test
    public void shouldGetEmptyData() throws Exception {
        assertTrue("".equals(idLine.getDataFormatted(true)));
        assertTrue("".equals(idLine.getDataFormatted(false)));
    }
}