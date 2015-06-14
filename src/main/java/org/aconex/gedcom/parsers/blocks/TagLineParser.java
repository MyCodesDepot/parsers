package org.aconex.gedcom.parsers.blocks;

import org.aconex.gedcom.models.TagLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagLineParser implements LineParser {
    private static final String REGEX_TO_MATCH_TAG_LINE = "^(\\d+)\\s+([A-Z]{3,4})((\\s*$)|(\\s+(.+)$))";
    private final Pattern pattern;

    public TagLineParser() {
        pattern = Pattern.compile(REGEX_TO_MATCH_TAG_LINE);
    }

    @Override
    public TagLine parseIfMatches(String line) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            return null;
        }
        TagLine tagLine = new TagLine(Integer.parseInt(matcher.group(1)), matcher.group(2), matcher.group(6));
        return tagLine;
    }
}
