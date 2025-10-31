package lotto.service;

import java.util.List;
import lotto.error.ErrorMessage;

public class InputValidationService {
    private static final Integer MAX_LOTTO_PRICE = 100000;
    private static final Integer ZERO = 0;
    private static final Integer DIVISIBLE_LOTTO_PRICE = 1000;
    private static final Integer WINNER_NUMBERS_COUNT = 6;
    private static final Integer START_LOTTO_RANGE = 1;
    private static final Integer END_LOTTO_RANGE = 45;

    public void validateLottoPriceAndReturnCount(Integer lottoPrice) {
        validateMaximum(lottoPrice);
        validateZeroOrNegative(lottoPrice);
        validateDivisibility(lottoPrice);
    }

    public void validateWinnerNumbers(List<Integer> winnerNumbers) {
        validateCount(winnerNumbers);
        validateDuplication(winnerNumbers);
        validateRange(winnerNumbers);
    }

    public void validateBonusNumber(List<Integer> winnerNumbers, Integer bonusNumber) {
        validateDuplication(winnerNumbers, bonusNumber);
        validateRange(bonusNumber);
    }

    private void validateDivisibility(Integer lottoPrice) {
        if (isDivisible(lottoPrice)) {
            throw new IllegalArgumentException(ErrorMessage.INDIVISIBLE_LOTTO_PRICE.getErrorMessage());
        }
    }

    private void validateZeroOrNegative(Integer lottoPrice) {
        if (isZeroOrNegative(lottoPrice)) {
            throw new IllegalArgumentException(ErrorMessage.ZERO_OR_NEGATIVE_LOTTO_PRICE.getErrorMessage());
        }
    }

    private void validateMaximum(Integer lottoPrice) {
        if (isOverMaximum(lottoPrice)) {
            throw new IllegalArgumentException(ErrorMessage.OVER_MAXIMUM_LOTTO_PRICE.getErrorMessage());
        }
    }

    private void validateRange(List<Integer> winnerNumbers) {
        if (isRangedOut(winnerNumbers)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_RANGED_WINNER_NUMBERS.getErrorMessage());
        }
    }

    private void validateDuplication(List<Integer> winnerNumbers) {
        if (isDuplicated(winnerNumbers)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_WINNER_NUMBERS.getErrorMessage());
        }
    }

    private void validateCount(List<Integer> winnerNumbers) {
        if (isCorrectCount(winnerNumbers)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SIX_WINNER_NUMBERS.getErrorMessage());
        }
    }

    private void validateRange(Integer bonusNumber) {
        if (isRangedOut(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_RANGED_BONUS_NUMBER.getErrorMessage());
        }
    }

    private void validateDuplication(List<Integer> winnerNumbers, Integer bonusNumber) {
        if (isDuplicated(winnerNumbers, bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_BONUS_NUMBER.getErrorMessage());
        }
    }

    private boolean isDivisible(Integer lottoPrice) {
        return (lottoPrice % DIVISIBLE_LOTTO_PRICE) != ZERO;
    }

    private boolean isZeroOrNegative(Integer lottoPrice) {
        return lottoPrice <= ZERO;
    }

    private boolean isOverMaximum(Integer lottoPrice) {
        return lottoPrice > MAX_LOTTO_PRICE;
    }

    private boolean isDuplicated(List<Integer> winnerNumbers) {
        return winnerNumbers.size() != winnerNumbers.stream().distinct().count();
    }

    private boolean isCorrectCount(List<Integer> winnerNumbers) {
        return winnerNumbers.size() != WINNER_NUMBERS_COUNT;
    }

    private boolean isRangedOut(List<Integer> winnerNumbers) {
        return winnerNumbers.stream()
                .anyMatch(this::isRangedOut);
    }

    private boolean isRangedOut(Integer number) {
        return number < START_LOTTO_RANGE || number > END_LOTTO_RANGE;
    }

    private boolean isDuplicated(List<Integer> winnerNumbers, Integer bonusNumber) {
        return winnerNumbers.contains(bonusNumber);
    }
}
