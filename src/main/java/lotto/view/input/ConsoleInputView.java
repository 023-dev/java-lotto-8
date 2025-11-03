package lotto.view.input;

import static lotto.exception.ExceptionMessage.INVALID_NUMBER_FORMAT;

import camp.nextstep.edu.missionutils.Console;
import lotto.Money;
import lotto.exception.LottoException;

public class ConsoleInputView {
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String EMPTY = "";

    public Money readPaidMoney() {
        final String input = read();
        final int number = parseNumber(input);
        return new Money(number);
    }

    private String read() {
        return Console.readLine().trim();
    }

    private int parseNumber(final String input) {
        try {
            String normalized = input.replaceAll(WHITESPACE_REGEX, EMPTY);
            return Integer.parseInt(normalized);
        } catch (NumberFormatException e) {
            throw new LottoException(INVALID_NUMBER_FORMAT);
        }
    }
}
