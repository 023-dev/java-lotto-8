package lotto.model;

import java.util.Map;

public class LottoResult {
    private final Map<LottoPrize, Long> prizeCount;

    public LottoResult(Map<LottoPrize, Long> prizeCount) {
        this.prizeCount = prizeCount;
    }

    public long getCountByPrize(LottoPrize prize) {
        return prizeCount.getOrDefault(prize, 0L);
    }

    public long getTotalPrizeMoney() {
        return prizeCount.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public double calculateProfitRate(Money investment) {
        if (investment.amount() == 0) {
            return 0.0;
        }
        return (double) getTotalPrizeMoney() / investment.amount() * 100;
    }

    public Map<LottoPrize, Long> getPrizeCount() {
        return Map.copyOf(prizeCount);
    }
}

