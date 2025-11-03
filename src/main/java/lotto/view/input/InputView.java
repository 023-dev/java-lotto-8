package lotto.view.input;

import java.util.List;
import lotto.LottoNumber;
import lotto.Money;

public interface InputView {
    Money readPaidMoney();
    List<Integer> readWinningLotto();
    LottoNumber readBonusNumber();
}
