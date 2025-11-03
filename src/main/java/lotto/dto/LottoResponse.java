package lotto.dto;

import java.util.List;
import lotto.model.LottoNumber;

public record LottoResponse(List<LottoNumber> numbers) {
}
