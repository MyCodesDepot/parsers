package org.aconex.gedcom.parsers.blocks;

import org.aconex.gedcom.models.Line;

public interface LineParser {
    Line parseIfMatches(String line);
}
