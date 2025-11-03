package lotto.view.output;

public class ConsoleOutputView implements OutputView {
    private static final String ASK_MONEY_MESSAGE = "구입금액을 입력해 주세요.";

    @Override
    public void printAskMoney() {
        print(ASK_MONEY_MESSAGE);
    }

    private void print(final String message) {
        System.out.println(message);
    }
}
