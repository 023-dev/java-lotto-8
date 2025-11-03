package lotto.exception;

public enum ExceptionMessage {
    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_LOTTO_NUMBER_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_DUPLICATE("로또 번혼는 중복되지 않아야 합니다."),
    ;

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_MESSAGE_PREFIX + message;
    }
}
