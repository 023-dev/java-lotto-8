package lotto.dto;

import java.util.List;
import lotto.LottoNumber;

public record LottoResponse(List<LottoNumber> numbers) {
}
