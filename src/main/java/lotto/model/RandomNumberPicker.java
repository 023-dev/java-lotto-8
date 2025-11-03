package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.List;

public class RandomNumberPicker implements NumberPicker {

    public List<Integer> pickNumbers(final int startInclusive, final int endInclusive, final int count) {
        final List<Integer> numbers = Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count);
        return Collections.unmodifiableList(numbers);
    }
}
