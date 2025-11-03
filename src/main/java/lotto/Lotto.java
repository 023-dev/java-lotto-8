package lotto;

import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_SIZE;

import java.util.List;
import lotto.exception.LottoException;

public class Lotto {
    private static final int NUMBERS_SIZE = 6;

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto from(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
    }

    private void validateSize(final List<LottoNumber> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new LottoException(INVALID_LOTTO_NUMBER_SIZE);
        }
    }

}
