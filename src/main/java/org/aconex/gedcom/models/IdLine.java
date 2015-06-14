package org.aconex.gedcom.models;

public class IdLine extends Line {

    public IdLine(int level, String tagOrId, String data) {
        super(level, tagOrId, data);
    }

    @Override
    public String getOpeningBlock(boolean inLine) {
        return String.format("<%s id=\"%s\">", getData(), getTagOrId());
    }

    @Override
    public String getClosingBlock() {
        return String.format("</%s>", getData());
    }

    @Override
    public String getDataFormatted(boolean inLine) {
        return "";
    }
}
