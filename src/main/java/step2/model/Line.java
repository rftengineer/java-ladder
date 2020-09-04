package step2.model;

import step2.strategy.DrawStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Line {
    private List<Boolean> rungs = new ArrayList<>();

    public void drawNewLine(int countOfPerson, DrawStrategy strategy) {
        rungs = Stream
                .generate(() -> draw(strategy))
                .limit(countOfPerson - 1)
                .collect(Collectors.toList());
    }

    public boolean draw(DrawStrategy strategy) {
        boolean drawResult = strategy.draw();
        if (isEmptyAndDrawed(drawResult) || isRungFalseAndDrawedNow(drawResult)) {
            return true;
        }
        return false;
    }

    private boolean isEmptyAndDrawed(boolean drawResult) {
        return rungs.isEmpty() && drawResult;
    }

    private boolean isRungFalseAndDrawedNow(boolean drawResult) {
        return !rungs.get(rungs.size() - 1) && drawResult;
    }

    public String printRungs() {
        return rungs.toString();
    }
}
