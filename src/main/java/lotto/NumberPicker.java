package lotto;

import java.util.List;

public interface NumberPicker {
    List<Integer> pickNumbers(int startInclusive, int endInclusive, int count);
}
