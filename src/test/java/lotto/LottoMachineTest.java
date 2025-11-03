package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.base.TestSupport;
import lotto.model.LottoMachine;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.NumberPicker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoMachineTest extends TestSupport {

    @ParameterizedTest
    @CsvSource({
            "1000, 1",
            "10000, 10",
            "2000000000, 2000000"
    })
    void 금액에_따라_로또를_발행할_수_있다(int amount, int expectedCount) {
        // given
        Money money = new Money(amount);
        NumberPicker picker = (start, end, count) -> List.of(1, 2, 3, 4, 5, 6);
        LottoMachine machine = new LottoMachine(picker);

        // when
        Lottos lottos = machine.issueLotto(money);

        // then
        assertThat(lottos.size()).isEqualTo(expectedCount);
    }
}


