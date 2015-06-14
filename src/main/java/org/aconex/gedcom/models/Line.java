package org.aconex.gedcom.models;

public abstract class Line {

    protected int level;
    protected String tagOrId;
    protected String data;

    abstract public String getOpeningBlock(boolean inLine);
    abstract public String getClosingBlock();
    abstract public String getDataFormatted(boolean inLine);

    public Line(int level, String tagOrId, String data) {
        this.level = level;
        this.tagOrId = tagOrId;
        this.data = data;
    }

    public int getLevel() {
        return level;
    }

    public String getTagOrId() {
        return tagOrId;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line)) return false;

        Line line = (Line) o;

        if (level != line.level) return false;
        if (data != null ? !data.equals(line.data) : line.data != null) return false;
        if (!tagOrId.equals(line.tagOrId)) return false;

        return true;
    }
}