package lotto.model;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final Money LOTTO_PRICE = new Money(1_000);
    private final NumberPicker picker;

    public LottoMachine(final NumberPicker picker) {
        this.picker = picker;
    }

    public Lottos issueLotto(final Money paidMoney) {
        final int issueCount = paidMoney.divide(LOTTO_PRICE);
        List<Lotto> lottos = IntStream.range(0, issueCount)
                .mapToObj(i -> Lotto.issue(picker))
                .toList();
        return new Lottos(lottos);
    }
}
