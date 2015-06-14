package org.aconex.gedcom.processors;

import org.aconex.gedcom.helpers.Node;
import org.aconex.gedcom.models.IdLine;
import org.aconex.gedcom.models.Line;
import org.aconex.gedcom.models.TagLine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InputProcessorTest {

    @Test
    public void shouldProcessParsedInputs() throws Exception {
        List<Node> levelZero, levelOne, levelTwo;
        Node xmlNode = new InputProcessor().process(getParsedInputLines());

        assertNotNull(xmlNode);
        assertEquals("<gedcom>", xmlNode.getOpeningTag());
        assertEquals("</gedcom>", xmlNode.getClosingTag());
        assertEquals("", xmlNode.getData());
        levelZero = xmlNode.getNodeList();
        assertEquals(2, levelZero.size());

        Node levelZeroFirst = levelZero.get(0);
        Node levelZeroSecond = levelZero.get(1);

        assertEquals("<SOUR id=\"@S0001@\">", levelZeroFirst.getOpeningTag());
        assertEquals("</SOUR>", levelZeroFirst.getClosingTag());
        assertEquals("", levelZeroFirst.getData());
        levelOne = levelZeroFirst.getNodeList();
        assertEquals(2, levelOne.size());

        Node levelOneFirst = levelOne.get(0);
        Node levelOneSecond = levelOne.get(1);

        assertEquals("<AUTH>", levelOneFirst.getOpeningTag());
        assertEquals("</AUTH>", levelOneFirst.getClosingTag());
        assertEquals("glamis family", levelOneFirst.getData());
        levelTwo = levelOneFirst.getNodeList();
        assertEquals(0, levelTwo.size());

        assertEquals("<PUBL>", levelOneSecond.getOpeningTag());
        assertEquals("</PUBL>", levelOneSecond.getClosingTag());
        assertEquals("Glamis Guide", levelOneSecond.getData());
        levelTwo = levelOneFirst.getNodeList();
        assertEquals(0, levelTwo.size());

        assertEquals("<TRLR>", levelZeroSecond.getOpeningTag());
        assertEquals("</TRLR>", levelZeroSecond.getClosingTag());
        levelOne = levelZeroSecond.getNodeList();
        assertEquals(0, levelOne.size());
    }

    private List<Line> getParsedInputLines() {
        List<Line> lines = new ArrayList<>();
        lines.add(new IdLine(0, "@S0001@", "SOUR"));
        lines.add(new TagLine(1, "AUTH", "glamis family"));
        lines.add(new TagLine(1, "PUBL", "Glamis Guide"));
        lines.add(new TagLine(0, "TRLR", null));
        return lines;
    }
}