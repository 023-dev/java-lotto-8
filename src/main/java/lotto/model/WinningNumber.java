package lotto.model;

import static lotto.exception.ExceptionMessage.INVALID_WINNING_NUMBER_DUPLICATE;

import lotto.exception.LottoException;

public class WinningNumber {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    private WinningNumber(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validateDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumber from(final Lotto winningLotto, final LottoNumber bonusNumber) {
        return new WinningNumber(winningLotto, bonusNumber);
    }

    private void validateDuplicate(final Lotto winningLotto, final LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new LottoException(INVALID_WINNING_NUMBER_DUPLICATE);
        }
    }

    public LottoPrize match(final Lotto lotto) {
        int matchCount = winningLotto.countMatches(lotto);
        boolean bonusMatch = lotto.contains(bonusNumber);
        return LottoPrize.from(matchCount, bonusMatch);
    }
}
