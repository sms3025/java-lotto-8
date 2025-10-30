package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class RandomNumberGeneratorTest {
    @RepeatedTest(value = 30)
    @DisplayName("정상: 1 ~ 45 사이의 랜덤 숫자를 생성하는지 테스트")
    void rangeInOneToFortyFiveAndSixCountAndNotDuplicatedTest() {
        //given
        final Integer COUNT = 6;
        final Integer START_NUMBER = 1;
        final Integer END_NUMBER = 45;
        //when
        List<Integer> resultRandomUniqueNumbers = RandomNumberGenerator.getRandomUniqueNumberList(START_NUMBER,
                END_NUMBER, COUNT);
        //then
        assertThat(resultRandomUniqueNumbers)
                .hasSize(COUNT)
                .doesNotHaveDuplicates()
                .allMatch(number -> number >= START_NUMBER && number <= END_NUMBER);
    }
}
