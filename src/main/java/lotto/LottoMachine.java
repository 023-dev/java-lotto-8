package lotto;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final Money LOTTO_PRICE = new Money(1_000);
    private final NumberPicker picker;

    public LottoMachine(final NumberPicker picker) {
        this.picker = picker;
    }

    public Lottos issueLotto(Money paidMoney) {
        int issueCount = paidMoney.devide(LOTTO_PRICE);
        List<Lotto> lottos = IntStream.range(0, issueCount)
                .mapToObj(i -> Lotto.issue(picker))
                .toList();
        return new Lottos(lottos);
    }
}
