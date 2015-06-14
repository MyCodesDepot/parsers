package org.aconex.gedcom.models;

public class TagLine extends Line {

    public TagLine(int level, String tagOrId, String data) {
        super(level, tagOrId, data);
    }

    @Override
    public String getOpeningBlock(boolean inLine) {
        return (inLine && null != getData() && !"".equals(getData())) ?
                String.format("<%s value=\"%s\">", getTagOrId(), getData()) :
                String.format("<%s>", getTagOrId());
    }

    @Override
    public String getClosingBlock() {
        return String.format("</%s>", getTagOrId());
    }

    @Override
    public String getDataFormatted(boolean inLine) {
        return inLine || null == getData() ? "" : getData();
    }
}
