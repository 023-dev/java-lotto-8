package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public LottoResult getResult(final WinningNumber winningNumber) {
        final Map<LottoPrize, Long> prizeCount = lottos.stream()
                .map(winningNumber::match)
                .collect(Collectors.groupingBy(
                        prize -> prize,
                        Collectors.counting()
                ));

        return new LottoResult(prizeCount);
    }
}

