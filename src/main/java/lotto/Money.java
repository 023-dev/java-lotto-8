package lotto;

import static lotto.exception.ExceptionMessage.INVALID_MONEY_RANGE;

import lotto.exception.LottoException;

public record Money(int amount) {
    private static final int MIN_AMOUNT = 1_000;
    private static final int MAX_AMOUNT = 2_000_000_000;

    public Money {
        validate(amount);
    }

    private void validate(final int amount) {
        validateRange(amount);
    }

    private void validateRange(final int amount) {
        if (isOutOfRange(amount)) {
            throw new LottoException(INVALID_MONEY_RANGE);
        }
    }

    private boolean isOutOfRange(final int amount) {
        return amount < MIN_AMOUNT ||
                amount > MAX_AMOUNT;
    }
}
