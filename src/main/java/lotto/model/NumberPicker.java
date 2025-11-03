package lotto.model;

import java.util.List;

@FunctionalInterface
public interface NumberPicker {
    List<Integer> pickNumbers(final int startInclusive, final int endInclusive, final int count);
}
