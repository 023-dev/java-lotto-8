package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.base.TestSupport;
import org.junit.jupiter.api.Test;

class LottosTest extends TestSupport {

    @Test
    void 로또_리스트로_생성할_수_있다() {
        // given
        List<Lotto> lottoList = List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(7, 8, 9, 10, 11, 12))
        );

        // when
        Lottos lottos = new Lottos(lottoList);

        // then
        assertThat(lottos.size()).isEqualTo(2);
    }

    @Test
    void 로또_개수를_조회할_수_있다() {
        // given
        List<Lotto> lottoList = List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6))
        );
        Lottos lottos = new Lottos(lottoList);

        // when
        int size = lottos.size();

        // then
        assertThat(size).isEqualTo(1);
    }

    @Test
    void 당첨_결과를_계산할_수_있다() {
        // given
        List<Lotto> lottoList = List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 7))
        );
        Lottos lottos = new Lottos(lottoList);
        WinningNumber winningNumber = WinningNumber.from(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7)
        );

        // when
        LottoResult result = lottos.getResult(winningNumber);

        // then
        assertThat(result.getCountByPrize(LottoPrize.FIRST)).isEqualTo(1);
        assertThat(result.getCountByPrize(LottoPrize.SECOND)).isEqualTo(1);
    }
}

