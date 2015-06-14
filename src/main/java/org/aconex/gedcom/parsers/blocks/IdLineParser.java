package org.aconex.gedcom.parsers.blocks;

import org.aconex.gedcom.models.IdLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdLineParser implements LineParser {
    private static final String REGEX_TO_MATCH_ID_LINE = "^(\\d+)\\s+(@.+@)\\s+(.+)$";
    private final Pattern pattern;

    public IdLineParser() {
        pattern = Pattern.compile(REGEX_TO_MATCH_ID_LINE);
    }

    @Override
    public IdLine parseIfMatches(String line) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            return null;
        }
        IdLine idLine = new IdLine(Integer.parseInt(matcher.group(1)), matcher.group(2), matcher.group(3));
        return idLine;
    }
}
