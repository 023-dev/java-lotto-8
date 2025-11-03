package lotto;

import static lotto.exception.ExceptionMessage.INVALID_WINNING_NUMBER_DUPLICATE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.base.TestSupport;
import lotto.exception.LottoException;
import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.WinningNumber;
import org.junit.jupiter.api.Test;

class WinningNumberTest extends TestSupport {

    @Test
    void 당첨_번호와_보너스_번호로_생성할_수_있다() {
        // given
        Lotto winningLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        // expected
        assertThatCode(() -> WinningNumber.from(winningLotto, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        // given
        Lotto winningLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(6);

        // expected
        assertThatThrownBy(() -> WinningNumber.from(winningLotto, bonusNumber))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(INVALID_WINNING_NUMBER_DUPLICATE.getMessage());
    }
}

