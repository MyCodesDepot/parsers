package org.aconex.gedcom.processors;

import org.aconex.gedcom.helpers.Node;
import org.aconex.gedcom.models.Line;
import org.aconex.gedcom.models.TagLine;

import java.util.ArrayList;
import java.util.List;

public class InputProcessor {

    public Node process(List<Line> lines) {
        Node head = new Node("<gedcom>", "</gedcom>", "");
        head.addToNodeList(createXMLTire(lines, 0, -1));
        return head;
    }

    private List<Node> createXMLTire(List<Line> lines, int lineNumber, int parentLevel) {
        List<Node> nodes = new ArrayList<>();
        if (lineNumber >= lines.size()) {
            return nodes;
        }
        Line line;
        do {
            line = lines.get(lineNumber);
            boolean isToBeInLined = toBeInLined(lines, lineNumber);
            Node node = new Node(line.getOpeningBlock(isToBeInLined), line.getClosingBlock(), line.getDataFormatted(isToBeInLined));
            lineNumber++;
            if (lineNumber >= lines.size()) {
                nodes.add(node);
                continue;
            }
            if (lines.get(lineNumber).getLevel() > line.getLevel()) {
                node.addToNodeList(createXMLTire(lines, lineNumber, line.getLevel()));
                while (lineNumber < lines.size() && lines.get(lineNumber).getLevel() > line.getLevel()) {
                    lineNumber++;
                }
            }
            nodes.add(node);
        } while (lineNumber < lines.size() && parentLevel < lines.get(lineNumber).getLevel());
        return nodes;
    }

    private boolean toBeInLined(List<Line> lines, int start) {
        return (start + 1 < lines.size() ?
                (lines.get(start).getLevel() < lines.get(start + 1).getLevel() && lines.get(start) instanceof TagLine)
                : false);
    }
}
