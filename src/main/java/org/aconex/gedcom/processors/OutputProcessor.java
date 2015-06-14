package org.aconex.gedcom.processors;

import org.aconex.gedcom.helpers.Node;

import java.util.ArrayList;
import java.util.List;

public class OutputProcessor {
    private static String FOUR_SPACES = "    ";

    public List<String> process(Node head) {
        if (null == head) {
            return null;
        }
        return getFormattedXML(head, "");
    }

    private List<String> getFormattedXML(Node node, String spaces) {
        List<String> xml = new ArrayList<>();
        List<Node> subNodeList = node.getNodeList();
        if (0 == subNodeList.size()) {
            xml.add(spaces + node.getOpeningTag() + node.getData() + node.getClosingTag());
            return xml;
        }
        xml.add(spaces + node.getOpeningTag());
        for (int i = 0; i < subNodeList.size(); i++) {
            xml.addAll(getFormattedXML(subNodeList.get(i), spaces + FOUR_SPACES));
        }
        xml.add(spaces + node.getClosingTag());
        return xml;
    }
}
