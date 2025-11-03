package lotto.view.output;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.LottoNumber;
import lotto.model.LottoPrize;
import lotto.model.LottoResult;
import lotto.dto.LottoResponse;

public class ConsoleOutputView implements OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String PURCHASED_LOTTOS_FORMAT = "%d개를 구매했습니다." + LINE_SEPARATOR;
    private static final String NUMBER_DELIMITER = ", ";
    private static final String LOTTO_PREFIX = "[";
    private static final String LOTTO_SUFFIX = "]";
    private static final String ASK_WINNING_NUMBER_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String RESULT_HEADER = LINE_SEPARATOR + "당첨 통계" + LINE_SEPARATOR + "---";
    private static final String RESULT_FORMAT = "%s (%s원) - %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";

    @Override
    public void printAskMoney() {
        print(ASK_MONEY_MESSAGE);
    }

    @Override
    public void printPurchasedLottos(final List<LottoResponse> purchasedLottos) {
        print(PURCHASED_LOTTOS_FORMAT, purchasedLottos.size());
       purchasedLottos.forEach(this::printLotto);
    }

    @Override
    public void printAskWinningLotto() {
        print(ASK_WINNING_NUMBER_MESSAGE);
    }

    @Override
    public void printAskBonusNumber() {
        print(ASK_BONUS_NUMBER_MESSAGE);
    }

    @Override
    public void printLottoResult(final LottoResult result) {
        print(RESULT_HEADER);
        printPrizeStatistics(result);
    }

    @Override
    public void printProfitRate(final double profitRate) {
        print(PROFIT_RATE_FORMAT, profitRate);
    }

    private void printPrizeStatistics(final LottoResult result) {
        printPrizeCount(result, LottoPrize.FIFTH);
        printPrizeCount(result, LottoPrize.FOURTH);
        printPrizeCount(result, LottoPrize.THIRD);
        printPrizeCount(result, LottoPrize.SECOND);
        printPrizeCount(result, LottoPrize.FIRST);
    }

    private void printPrizeCount(final LottoResult result, final LottoPrize prize) {
        long count = result.getCountByPrize(prize);
        String prizeInfo = String.format(RESULT_FORMAT,
                prize.getDescription(),
                formatMoney(prize.getPrizeMoney()),
                count);
        print(prizeInfo);
    }

    private String formatMoney(int money) {
        return String.format("%,d", money);
    }

    private void printLotto(final LottoResponse lotto) {
        String numbers = lotto.numbers().stream()
                .sorted()
                .map(LottoNumber::number)
                .map(String::valueOf)
                .collect(Collectors.joining(NUMBER_DELIMITER));
        print(LOTTO_PREFIX + numbers + LOTTO_SUFFIX);
    }

    private void print(final String message) {
        System.out.println(message);
    }

    private void print(final String format, final Object... args) {
        System.out.printf(format, args);
    }
}
