package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.error.ErrorMessage;

public class Lotto {
    private static final Integer LOTTO_NUMBERS_COUNT = 6;
    private static final Integer START_LOTTO_RANGE = 1;
    private static final Integer END_LOTTO_RANGE = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> copyNumbers = new ArrayList<>(numbers);
        Collections.sort(copyNumbers);
        this.numbers = copyNumbers;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplication(numbers);
        validateRange(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (isCorrectCount(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SIX_LOTTO_NUMBERS.getErrorMessage());
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_LOTTO_NUMBERS.getErrorMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (isOutOfRange(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_RANGED_LOTTO_NUMBERS.getErrorMessage());
        }
    }

    private boolean isDuplicated(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }

    private boolean isOutOfRange(List<Integer> numbers) {
        return numbers.stream().anyMatch(number -> number < START_LOTTO_RANGE || number > END_LOTTO_RANGE);
    }

    private boolean isCorrectCount(List<Integer> numbers) {
        return numbers.size() != LOTTO_NUMBERS_COUNT;
    }
}
