package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.Lotto;
import lotto.LottoMachine;
import lotto.LottoNumber;
import lotto.Money;
import lotto.NumberPicker;
import lotto.RandomNumberPicker;
import lotto.WinningNumber;
import lotto.dto.LottoResponse;
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
        final NumberPicker picker = new RandomNumberPicker();
        final LottoMachine lottoMachine = new LottoMachine(picker);

        final Money paidMoney = requestMoney();
        final List<Lotto> lottos = lottoMachine.issueLotto(paidMoney);
        printPurchasedLottos(lottos);

        final WinningNumber winningNumber = requestWinningNumber();
        final
    }

    private Money requestMoney() {
        return requestHandler(() -> {
            outputView.printAskMoney();
            return inputView.readPaidMoney();
        });
    }

    private void printPurchasedLottos(final List<Lotto> lottos) {
        final List<LottoResponse> lottoResponses = lottos.stream()
                .map(Lotto::getNumbers)
                .map(LottoResponse::new)
                .toList();
        outputView.printPurchasedLottos(lottoResponses);
    }

    private WinningNumber requestWinningNumber() {
        return requestHandler(() -> {
            final Lotto winningLotto = requestWinningLotto();
            final LottoNumber bonusNumber = requestBonusNumber();
            return WinningNumber.from(winningLotto, bonusNumber);
        });
    }

    private Lotto requestWinningLotto() {
        return requestHandler(() -> {
            outputView.printAskWinningLotto();
            return Lotto.from(inputView.readWinningLotto());
        });
    }

    private LottoNumber requestBonusNumber() {
        return requestHandler(() -> {
            outputView.printAskBonusNumber();
            return inputView.readBonusNumber();
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