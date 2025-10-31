package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
