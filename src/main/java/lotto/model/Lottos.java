package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public LottoResult getResult(WinningNumber winningNumber) {
        Map<LottoPrize, Long> prizeCount = lottos.stream()
                .map(winningNumber::match)
                .collect(Collectors.groupingBy(
                        prize -> prize,
                        Collectors.counting()
                ));

        return new LottoResult(prizeCount);
    }
}

