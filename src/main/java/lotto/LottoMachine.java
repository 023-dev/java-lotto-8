package lotto;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final Money LOTTO_PRICE = new Money(1_000);
    private final NumberPicker picker;

    public LottoMachine(final NumberPicker picker) {
        this.picker = picker;
    }

    public List<Lotto> issueLotto(Money paidMoney) {
        int issueCount = calculateIssueCount(paidMoney);
        return IntStream.range(0, issueCount)
                .mapToObj(i -> Lotto.issue(picker))
                .toList();
    }

    private int calculateIssueCount(Money paidMoney) {
        return paidMoney.amount() / LOTTO_PRICE.amount();
    }
}
