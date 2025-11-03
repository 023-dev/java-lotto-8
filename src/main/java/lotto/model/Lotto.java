package lotto.model;

import static lotto.model.LottoNumber.*;
import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_DUPLICATE;
import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_SIZE;

import java.util.Collections;
import java.util.List;
import lotto.exception.LottoException;

public class Lotto {
    private static final int NUMBERS_SIZE = 6;

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto from(final List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }

    public static Lotto issue(final NumberPicker picker) {
        final List<LottoNumber> numbers = picker.pickNumbers(getMinLottoNumber(), getMaxLottoNumber(), NUMBERS_SIZE)
                .stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(numbers);
    }

    public boolean contains(final LottoNumber number) {
        return numbers.contains(number);
    }

    private void validate(final List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(final List<LottoNumber> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new LottoException(INVALID_LOTTO_NUMBER_SIZE);
        }
    }

    private void validateDuplicate(final List<LottoNumber> numbers) {
        if (isDuplicate(numbers)) {
            throw new LottoException(INVALID_LOTTO_NUMBER_DUPLICATE);
        }
    }

    private boolean isDuplicate(final List<LottoNumber> numbers) {
        return numbers.stream()
                .distinct()
                .count() != numbers.size();
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public int countMatches(final Lotto other) {
        return (int) numbers.stream()
                .filter(other.numbers::contains)
                .count();
    }
}
