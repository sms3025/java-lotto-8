package lotto.utils;

import java.util.Arrays;
import java.util.List;
import lotto.error.ErrorCode;

public class LottoNumberConverter {
    public static Integer parseStringToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getErrorMessage());
        }
    }

    public static List<Integer> splitStringToIntegerList(String input, String delimiter) {
        return Arrays.stream(input.replace(" ", "")
                        .split(delimiter, -1))
                .map(LottoNumberConverter::parseStringToInteger)
                .toList();
    }
}
