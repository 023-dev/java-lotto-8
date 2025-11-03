package lotto.view.input;

import static lotto.exception.ExceptionMessage.INVALID_NUMBER_FORMAT;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import lotto.model.LottoNumber;
import lotto.model.Money;
import lotto.exception.LottoException;

public class ConsoleInputView implements InputView {
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String EMPTY = "";
    private static final String DELIMITER_COMMA = ",";

    @Override
    public Money readPaidMoney() {
        final String input = read();
        final int number = parseNumber(input);
        return new Money(number);
    }

    @Override
    public List<Integer> readWinningLotto() {
        final String input = read();
        return parseNumbers(input, DELIMITER_COMMA);
    }

    @Override
    public LottoNumber readBonusNumber() {
        final String input = read();
        final int number = parseNumber(input);
        return new LottoNumber(number);
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

    private List<Integer> parseNumbers(final String input, final String delimiter) {
        String normalized = input.replaceAll(WHITESPACE_REGEX, EMPTY);
        String escapedDelimiter = Pattern.quote(delimiter);
        return Arrays.stream(normalized.split(escapedDelimiter))
                .map(this::parseNumber)
                .toList();
    }
}
