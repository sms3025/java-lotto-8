package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void beforeEach() {
        lottoService = new LottoService();
    }

    @Test
    @DisplayName("정상: 로또, 당첨 번호, 보너스 번호를 통해 각 등수마다 몇개가 당첨됬는지 확인")
    void getCountEachRankTest() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 9, 10)),
                new Lotto(List.of(1, 2, 3, 11, 12, 13)),
                new Lotto(List.of(40, 41, 42, 43, 44, 45))
        );
        List<Integer> winnerNumbers = List.of(1, 2, 3, 4, 5, 6);
        Integer bonusNumber = 7;
        //when
        Map<Rank, Integer> resultCount = lottoService.getCountEachRank(lottos, winnerNumbers, bonusNumber);
        // then
        assertThat(resultCount.get(Rank.FIRST_PLACE)).isEqualTo(1);
        assertThat(resultCount.get(Rank.SECOND_PLACE)).isEqualTo(1);
        assertThat(resultCount.get(Rank.THIRD_PLACE)).isEqualTo(1);
        assertThat(resultCount.get(Rank.FOURTH_PLACE)).isEqualTo(1);
        assertThat(resultCount.get(Rank.FIFTH_PLACE)).isEqualTo(1);
    }

    @Test
    @DisplayName("정상: 각 Rank마다의 카운드 개수에 따른 총 삼금 계산")
    void getTotalPrizeTest() {
        // given
        EnumMap<Rank, Integer> countOfRank = new EnumMap<>(Rank.class);
        countOfRank.put(Rank.FIFTH_PLACE, 0);
        countOfRank.put(Rank.FOURTH_PLACE, 1);
        countOfRank.put(Rank.THIRD_PLACE, 2);
        countOfRank.put(Rank.SECOND_PLACE, 3);
        countOfRank.put(Rank.FIRST_PLACE, 4);
        Long expectedTotalPrize = 8093050000L;
        //when
        Long resultTotalPrize = lottoService.getTotalPrize(countOfRank);
        // then
        assertThat(resultTotalPrize).isEqualTo(expectedTotalPrize);
    }

    @ParameterizedTest
    @CsvSource({
            "5000, 3000, 166.7",
            "1500000, 1000, 150000.0",
            "1550000, 7000, 22142.9",
            "0, 50000, 0.0"
    })
    @DisplayName("정상: 총 상금과 구매한 가격에 따라서 소수점 둘째자리 에서 반올림한 결과를 계산")
    void getRateOfReturnTest(Long totalPrize, Integer lottoPrice, Double rate) {
        //given
        BigDecimal expectedRate = BigDecimal.valueOf(rate);
        //when
        BigDecimal resultRate = lottoService.getRateOfReturn(totalPrize, lottoPrice);
        //then
        assertThat(resultRate).isEqualTo(expectedRate);
    }
}
