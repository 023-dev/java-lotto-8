package lotto.view.output;

import java.util.List;
import lotto.LottoResult;
import lotto.dto.LottoResponse;

public interface OutputView {
    void printAskMoney();
    void printPurchasedLottos(final List<LottoResponse> purchasedLottos);
    void printAskWinningLotto();
    void printAskBonusNumber();
    void printLottoResult(final LottoResult result);
    void printProfitRate(final double profitRate);
}
