package lotto.model;

import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_RANGE;

import lotto.exception.LottoException;

public record LottoNumber(int number) implements Comparable<LottoNumber> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public LottoNumber {
        validate(number);
    }

    private void validate(final int number) {
        if (isOutOfRange(number)) {
            throw new LottoException(INVALID_LOTTO_NUMBER_RANGE);
        }
    }

    private boolean isOutOfRange(final int number) {
        return number < MIN_NUMBER
                || number > MAX_NUMBER;
    }

    public static int getMinLottoNumber() {
        return MIN_NUMBER;
    }

    public static int getMaxLottoNumber() {
        return MAX_NUMBER;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }
}
