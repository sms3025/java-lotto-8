package lotto.utils;

import java.util.Arrays;
import java.util.List;
import lotto.error.ErrorMessage;

public class LottoNumberConverter {
    public static Integer parseStringToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }

    public static List<Integer> splitStringToIntegerList(String input, String delimiter) {
        try {
            return Arrays.stream(input.replace(" ", "")
                            .split(delimiter, -1))
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }
}
