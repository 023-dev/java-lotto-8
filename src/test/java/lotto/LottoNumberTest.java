package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.base.TestSupport;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest extends TestSupport {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 30, 40, 45})
    void 유효한_로또_번호로_객체를_생성할_수_있다(int number) {
        // when
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertThat(lottoNumber.number()).isEqualTo(number);
    }
}

