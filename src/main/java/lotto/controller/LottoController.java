package lotto.controller;

import java.util.function.Supplier;
import lotto.Money;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Money paidMoney = requestMoney();
    }

    private Money requestMoney() {
        return requestHandler(() -> {
            outputView.printAskMoney();
            return inputView.readPaidMoney();
        });
    }
    private <T> T requestHandler(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}