package lotto;

import static lotto.exception.ExceptionMessage.INVALID_MONEY_RANGE;
import static lotto.exception.ExceptionMessage.INVALID_MONEY_UNIT;

import lotto.exception.LottoException;

public record Money(int amount) {
    private static final int AMOUNT_UNIT = 1_000;
    private static final int MIN_AMOUNT = 1_000;
    private static final int MAX_AMOUNT = 2_000_000_000;

    public Money {
        validate(amount);
    }

    private void validate(final int amount) {
        validateRange(amount);
        validateUnit(amount);
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

    private void validateUnit(final int amount) {
        if (amount % AMOUNT_UNIT != 0) {
            throw new LottoException(INVALID_MONEY_UNIT);
        }
    }
}
