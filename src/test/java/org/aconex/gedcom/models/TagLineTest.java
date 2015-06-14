package org.aconex.gedcom.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TagLineTest {
    TagLine tagLine;

    @Test
    public void shouldGetFormattedOpeningBlock() throws Exception {
        tagLine = new TagLine(1, "DATE", "21 Apr 1926");
        assertEquals("<DATE value=\"21 Apr 1926\">", tagLine.getOpeningBlock(true));
        assertEquals("<DATE>", tagLine.getOpeningBlock(false));
        tagLine = new TagLine(1, "DATE", "");
        assertEquals("<DATE>", tagLine.getOpeningBlock(true));
        assertEquals("<DATE>", tagLine.getOpeningBlock(false));
        tagLine = new TagLine(1, "DATE", null);
        assertEquals("<DATE>", tagLine.getOpeningBlock(true));
        assertEquals("<DATE>", tagLine.getOpeningBlock(false));
    }

    @Test
    public void shouldGetFormattedClosingBlock() throws Exception {
        tagLine = new TagLine(1, "DATE", "21 Apr 1926");
        assertEquals("</DATE>", tagLine.getClosingBlock());
    }

    @Test
    public void shouldGetFormattedData() throws Exception {
        tagLine = new TagLine(1, "DATE", "21 Apr 1926");
        assertTrue("".equals(tagLine.getDataFormatted(true)));
        assertTrue("21 Apr 1926".equals(tagLine.getDataFormatted(false)));
    }

    @Test
    public void shouldReturnEmptyData() throws Exception {
        tagLine = new TagLine(0, "DATE", null);
        assertTrue("".equals(tagLine.getDataFormatted(true)));
        assertTrue("".equals(tagLine.getDataFormatted(false)));
    }
}