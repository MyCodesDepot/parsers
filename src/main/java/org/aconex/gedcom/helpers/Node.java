package org.aconex.gedcom.helpers;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String openingTag;
    private String closingTag;
    private String data;
    private List<Node> nodeList;

    public Node(String openingTag, String closingTag, String data) {
        this.openingTag = openingTag;
        this.closingTag = closingTag;
        this.data = data;
        this.nodeList = new ArrayList<>();
    }

    public String getClosingTag() {
        return closingTag;
    }

    public String getOpeningTag() {
        return openingTag;
    }

    public String getData() {
        return data;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void addToNodeList(List<Node> node) {
        this.nodeList.addAll(node);
    }
}
