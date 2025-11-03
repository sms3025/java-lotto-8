package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.error.ErrorMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream origin;
    private OutputView outputView;

    @BeforeEach
    void beforeEach() {
        origin = System.out;
        System.setOut(new PrintStream(out));
        outputView = new OutputView();
    }

    @AfterEach
    void afterEach() {
        System.setOut(origin);
    }

    @Test
    @DisplayName("정상: 에러 메시지 출력 테스트")
    void printErrorMessageTest() {
        //given
        String errorMessage = ErrorMessage.INVALID_INPUT.getErrorMessage();
        String expectedMessage = "[ERROR] " + errorMessage;
        //when
        outputView.printErrorMessage(errorMessage);
        String actualMessage = out.toString();
        //then
        assertThat(actualMessage).contains(expectedMessage);
    }

    @Test
    @DisplayName("정상: 로또 장수와 로또 번호 출력 테스트")
    void printCountFromLottoPriceTest() {
        //given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 9, 10)),
                new Lotto(List.of(1, 2, 3, 11, 12, 13)),
                new Lotto(List.of(40, 41, 42, 43, 44, 45))
        );
        //when
        outputView.printCountFromLottoPrice(lottos);
        String actualMessage = out.toString();
        //then
        assertThat(actualMessage)
                .contains("6개를 구매했습니다.")
                .contains("[1, 2, 3, 4, 5, 6]")
                .contains("[1, 2, 3, 4, 5, 7]")
                .contains("[1, 2, 3, 4, 5, 8]")
                .contains("[1, 2, 3, 4, 9, 10]")
                .contains("[1, 2, 3, 11, 12, 13]")
                .contains("[40, 41, 42, 43, 44, 45]");
    }

    @Test
    @DisplayName("정상: 로또 당첨 여부와 수익률을 출력하는 테스트")
    void printWinningStatisticsTest() {
        //given
        EnumMap<Rank, Integer> countOfRank = new EnumMap<>(Rank.class);
        countOfRank.put(Rank.FIFTH_PLACE, 0);
        countOfRank.put(Rank.FOURTH_PLACE, 1);
        countOfRank.put(Rank.THIRD_PLACE, 2);
        countOfRank.put(Rank.SECOND_PLACE, 3);
        countOfRank.put(Rank.FIRST_PLACE, 4);

        BigDecimal rateOfReturn = BigDecimal.valueOf(33.4);
        //when
        outputView.printWinningStatistics(countOfRank, rateOfReturn);
        String actualMessage = out.toString();
        //then
        assertThat(actualMessage)
                .contains("당첨 통계")
                .contains("3개 일치 (5,000원) - 0개")
                .contains("4개 일치 (50,000원) - 1개")
                .contains("5개 일치 (1,500,000원) - 2개")
                .contains("5개 일치, 보너스 볼 일치 (30,000,000원) - 3개")
                .contains("6개 일치 (2,000,000,000원) - 4개")
                .contains("총 수익률은 33.4%입니다.");
    }

}
