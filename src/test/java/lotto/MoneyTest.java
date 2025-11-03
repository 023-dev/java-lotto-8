package lotto;

import static org.assertj.core.api.Assertions.assertThatCode;

import lotto.base.TestSupport;
import org.junit.jupiter.api.Test;

class MoneyTest extends TestSupport {

    @Test
    void 정수로_생성할_수_있다() {
        // given
        int amount = 5000;

        // expected
        assertThatCode(() -> new Money(amount))
                .doesNotThrowAnyException();
    }
}

