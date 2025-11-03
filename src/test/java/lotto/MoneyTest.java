package lotto;

import static lotto.exception.ExceptionMessage.INVALID_MONEY_RANGE;
import static lotto.exception.ExceptionMessage.INVALID_MONEY_UNIT;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.base.TestSupport;
import lotto.model.Money;
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

    @ParameterizedTest
    @ValueSource(ints = {1_000, 2_000, 5_000, 10_000, 100_000, 1_000_000})
    void 천원_단위로_생성할_수_있다(int amount) {
        // expected
        assertThatCode(() -> new Money(amount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {1_001, 1_500, 2_500, 5_999, 10_100})
    void 천원_단위가_아니면_예외가_발생한다(int amount) {
        // expected
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MONEY_UNIT.getMessage());
    }

    @Test
    void 범위는_맞지만_단위가_틀리면_예외가_발생한다() {
        // given
        int amount = 5_500;

        // expected
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MONEY_UNIT.getMessage());
    }
}
