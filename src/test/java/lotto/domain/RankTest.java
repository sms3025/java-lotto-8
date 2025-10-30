package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {
    @ParameterizedTest
    @DisplayName("정상: 값이 있는 Rank가 얻어지는지 테스트")
    @CsvSource(value = {
            "6, true, FIRST_PLACE",
            "6, false, FIRST_PLACE",
            "5, true, SECOND_PLACE",
            "5, false, THIRD_PLACE",
            "4, true, FOURTH_PLACE",
            "4, false, FOURTH_PLACE",
            "3, true, FIFTH_PLACE",
            "3, false, FIFTH_PLACE",
    })
    void getExistRankTest(Integer matchCount, Boolean hasBonusNumber, Rank expectedRank) {
        //when
        Optional<Rank> resultRank = Rank.getRankOfOptional(matchCount, hasBonusNumber);
        //then
        assertThat(resultRank).isEqualTo(Optional.of(expectedRank));
    }

    @ParameterizedTest
    @DisplayName("정상: 값이 없는 empty Rank가 얻어지는지 테스트")
    @CsvSource(value = {
            "2, true",
            "2, false",
            "1, true",
            "1, false",
            "0, true",
            "0, false"
    })
    void getEmptyRankTest(Integer matchCount, Boolean hasBonusNumber) {
        //when
        Optional<Rank> resultRank = Rank.getRankOfOptional(matchCount, hasBonusNumber);
        //then
        assertThat(resultRank).isEmpty();
    }
}
