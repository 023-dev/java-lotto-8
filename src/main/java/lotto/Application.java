package lotto;

import lotto.controller.LottoController;
import lotto.view.input.ConsoleInputView;
import lotto.view.input.InputView;
import lotto.view.output.ConsoleOutputView;
import lotto.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        final OutputView outputView = new ConsoleOutputView();
        final InputView inputView = new ConsoleInputView();

        final LottoController controller = new LottoController(inputView, outputView);
        controller.run();
    }
}
