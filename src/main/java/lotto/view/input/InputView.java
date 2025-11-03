package lotto.view.input;

import java.util.List;
import lotto.model.LottoNumber;
import lotto.model.Money;

public interface InputView {
    Money readPaidMoney();
    List<Integer> readWinningLotto();
    LottoNumber readBonusNumber();
}
