package lotto;

import static lotto.exception.ExceptionMessage.INVALID_MONEY_RANGE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.base.TestSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest extends TestSupport {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 5_000, 2_000_000_000})
    void 정수로_생성할_수_있다(int amount) {
        // expected
        assertThatCode(() -> new Money(amount))
                .doesNotThrowAnyException();
    }

    @Test
    void 최소값보다_작은_금액은_예외가_발생한다() {
        // given
        int amount = 999;

        // expected
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MONEY_RANGE.getMessage());
    }

    @Test
    void 최대값보다_큰_금액은_예외가_발생한다() {
        // given
        int amount = 2_000_000_001;

        // expected
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MONEY_RANGE.getMessage());
    }
}
