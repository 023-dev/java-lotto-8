package lotto;

import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.base.TestSupport;
import lotto.model.LottoNumber;
import org.junit.jupiter.api.Test;
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

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void 로또_번호가_1보다_작으면_예외가_발생한다(int number) {
        // expected
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_NUMBER_RANGE.getMessage());
    }

    @Test
    void 로또_번호가_45보다_크면_예외가_발생한다() {
        // given
        int number = 46;

        // expected
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_NUMBER_RANGE.getMessage());
    }
}

