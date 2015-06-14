package org.aconex.gedcom.processors;

import org.aconex.gedcom.helpers.Node;
import org.aconex.gedcom.models.IdLine;
import org.aconex.gedcom.models.Line;
import org.aconex.gedcom.models.TagLine;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OutputProcessorTest {

    @Test
    public void shouldProcessOutput() throws Exception {
        List<String> xml = new OutputProcessor().process(createTrie());
        Assert.assertEquals(createXML(), xml);
    }

    private List<String> createXML() {
        List<String> list = new ArrayList<>();
        list.add("<gedcom>");
        list.add("    <SOUR id=\"@S0001@\">");
        list.add("        <AUTH>glamis family</AUTH>");
        list.add("        <PUBL>Glamis Guide</PUBL>");
        list.add("    </SOUR>");
        list.add("    <TRLR></TRLR>");
        list.add("</gedcom>");
        return list;
    }

    private Node createTrie() {
        Node head = new Node("<gedcom>", "</gedcom>", "");
        Node one = new Node("<SOUR id=\"@S0001@\">", "</SOUR>", "");
        Node two = new Node("<AUTH>", "</AUTH>", "glamis family");
        Node three = new Node("<PUBL>", "</PUBL>", "Glamis Guide");
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(two);
        nodes.add(three);
        one.addToNodeList(nodes);
        Node four = new Node("<TRLR>", "</TRLR>", "");
        nodes = new ArrayList<>();
        nodes.add(one);
        nodes.add(four);
        head.addToNodeList(nodes);
        return head;
    }
}