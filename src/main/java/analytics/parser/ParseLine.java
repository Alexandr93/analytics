package analytics.parser;

import analytics.models.Line;

public interface ParseLine {

    public Line parse(String lineString);
}
