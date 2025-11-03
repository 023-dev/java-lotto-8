package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.List;

public class RandomNumberPicker implements NumberPicker {

    public List<Integer> pickNumbers(int startInclusive, int endInclusive, int count) {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count);
        return Collections.unmodifiableList(numbers);
    }
}
