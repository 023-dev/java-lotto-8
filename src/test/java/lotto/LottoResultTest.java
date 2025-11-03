package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import lotto.base.TestSupport;
import lotto.model.LottoPrize;
import lotto.model.LottoResult;
import lotto.model.Money;
import org.junit.jupiter.api.Test;

class LottoResultTest extends TestSupport {

    @Test
    void 당첨_등수별_개수를_조회할_수_있다() {
        // given
        Map<LottoPrize, Long> prizeCount = new HashMap<>();
        prizeCount.put(LottoPrize.FIRST, 1L);
        prizeCount.put(LottoPrize.FIFTH, 2L);
        LottoResult result = new LottoResult(prizeCount);

        // when & then
        assertThat(result.getCountByPrize(LottoPrize.FIRST)).isEqualTo(1);
        assertThat(result.getCountByPrize(LottoPrize.FIFTH)).isEqualTo(2);
        assertThat(result.getCountByPrize(LottoPrize.SECOND)).isZero();
    }

    @Test
    void 총_당첨_금액을_계산할_수_있다() {
        // given
        Map<LottoPrize, Long> prizeCount = new HashMap<>();
        prizeCount.put(LottoPrize.FIFTH, 2L);
        prizeCount.put(LottoPrize.FOURTH, 1L);
        LottoResult result = new LottoResult(prizeCount);

        // when
        long totalPrize = result.getTotalPrizeMoney();

        // then
        assertThat(totalPrize).isEqualTo(60_000);
    }

    @Test
    void 수익률을_계산할_수_있다() {
        // given
        Map<LottoPrize, Long> prizeCount = new HashMap<>();
        prizeCount.put(LottoPrize.FIFTH, 1L);
        LottoResult result = new LottoResult(prizeCount);
        Money investment = new Money(8_000);

        // when
        double profitRate = result.calculateProfitRate(investment);

        // then
        assertThat(profitRate).isEqualTo(62.5);
    }
}

