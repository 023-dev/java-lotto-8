package lotto.exception;

public class LottoException extends IllegalArgumentException {
    public LottoException(final ExceptionMessage message) {
        super(message.getMessage());
    }
}
