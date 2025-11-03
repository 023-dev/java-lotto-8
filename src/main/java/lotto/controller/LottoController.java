package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoNumber;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.NumberPicker;
import lotto.model.RandomNumberPicker;
import lotto.model.WinningNumber;
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
        final Lottos lottos = lottoMachine.issueLotto(paidMoney);
        printPurchasedLottos(lottos);

        final WinningNumber winningNumber = requestWinningNumber();
        final LottoResult result = lottos.getResult(winningNumber);
        printLottoResult(result, paidMoney);
    }

    private Money requestMoney() {
        return requestHandler(() -> {
            outputView.printAskMoney();
            return inputView.readPaidMoney();
        });
    }

    private void printPurchasedLottos(final Lottos lottos) {
        final List<LottoResponse> lottoResponses = lottos.getLottos().stream()
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

    private void printLottoResult(final LottoResult result, final Money investment) {
        outputView.printLottoResult(result);
        outputView.printProfitRate(result.calculateProfitRate(investment));
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