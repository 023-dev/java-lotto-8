package lotto.exception;

public enum ExceptionMessage {
    INVALID_NUMBER_FORMAT("숫자로 입력해야 합니다."),
    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_LOTTO_NUMBER_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_DUPLICATE("로또 번혼는 중복되지 않아야 합니다."),
    INVALID_MONEY_RANGE("금액은 최소 1,000원 이상, 최대 2,000,000,000원 이하여야 합니다."),
    INVALID_MONEY_UNIT("금액은 1,000원 단위여야 합니다."),
    INVALID_WINNING_NUMBER_DUPLICATE("보너스 번호는 로또 번호와 중복될 수 없습니다."),
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
