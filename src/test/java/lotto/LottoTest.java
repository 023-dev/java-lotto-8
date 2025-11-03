package lotto;

import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_DUPLICATE;
import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_SIZE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.base.TestSupport;
import lotto.model.Lotto;
import lotto.model.NumberPicker;
import org.junit.jupiter.api.Test;

class LottoTest extends TestSupport {

    @Test
    void 로또는_6개의_로또_번호로_생성할_수_있다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // expected
        assertThatCode(() -> Lotto.from(numbers))
                .doesNotThrowAnyException();
    }

    @Test
    void 최소값과_최대값을_포함한_로또_번호로_생성할_수_있다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 43, 44, 45);

        // expected
        assertThatCode(() -> Lotto.from(numbers))
                .doesNotThrowAnyException();
    }

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        // expected
        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_NUMBER_SIZE.getMessage());
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        // expected
        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_NUMBER_DUPLICATE.getMessage());
    }

    @Test
    void NumberPicker를_사용하여_로또를_발행할_수_있다() {
        // given
        NumberPicker picker = (start, end, count) -> List.of(1, 2, 3, 4, 5, 6);

        // expected
        assertThatCode(() -> Lotto.issue(picker))
                .doesNotThrowAnyException();
    }
}
