package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberConverterTest {
    static final String delimiter = ",";

    @Test
    @DisplayName("정상: 문자열을 일반적인 정수 값으로 파싱")
    void normalStringToIntegerParsingTest() {
        //given
        List<String> inputs = List.of("1000", "-100", "5000");
        List<Integer> expectedNumbers = List.of(1000, -100, 5000);
        //when
        List<Integer> resultNumbers = inputs.stream()
                .map(LottoNumberConverter::parseStringToInteger)
                .toList();
        //then
        assertThat(resultNumbers).isEqualTo(expectedNumbers);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "\n", "a ", "\t", "-1.4", "2/3"})
    @DisplayName("에외: 정수가 아닌 값 ")
    void doesNotNumberParsingTest(String input) {
        //when & then
        assertThatThrownBy(() -> LottoNumberConverter.parseStringToInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INPUT.getErrorMessage());
    }

    @Test
    @DisplayName("정상: 문자열을 구분자를 통해 일반적인 정수 리스트로 분리")
    void normalStringToIntegerListSplitTest() {
        //given
        String input = "-1,1,20,300,500,-1000";
        List<Integer> expectedNumbers = List.of(-1, 1, 20, 300, 500, -1000);
        //when
        List<Integer> resultNumbers = LottoNumberConverter.splitStringToIntegerList(input, delimiter);
        //then
        assertThat(resultNumbers).isEqualTo(expectedNumbers);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "\n", "a ", "\t", "-1.4", "2/3", "-1,1,10, 100, 2000, ", "1,,2,3,4, 5, 6", "1 6, 3"})
    @DisplayName("에외: 정수가 아니거나 잘못된 형식의 문자열 ")
    void doesNotNumberAndInvalidInputSplitTest(String input) {
        //when & then
        assertThatThrownBy(() -> LottoNumberConverter.splitStringToIntegerList(input, delimiter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INPUT.getErrorMessage());
    }

}
