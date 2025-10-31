package lotto.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {
    private static final String START_ERROR_MESSAGE = "[ERROR] ";
    private static final String START_WINNING_STATISTICS = "당첨 통계";

    public OutputView() {
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(START_ERROR_MESSAGE + errorMessage);
    }

    public void printCountFromLottoPrice(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto -> {
            System.out.println(lotto.getNumbers());
        });
    }

    public void printWinningStatistics(Map<Rank, Integer> countOfRank, BigDecimal rateOfReturn) {
        System.out.println(START_WINNING_STATISTICS);
        System.out.println();
        countOfRank.forEach((rank, count) -> {
            System.out.println(buildWinningStatistics(rank, count));
        });
        System.out.println("총 수익률은 " + rateOfReturn + "%입니다.");
    }

    private String buildWinningStatistics(Rank rank, Integer count) {
        if (rank.getHasBonusNumber()) {
            return String.format("%d개 일치, 보너스 볼 일치 (%,d원) - %d개", rank.getMatchCount(), rank.getPrize(), count);
        }
        return String.format("%d개 일치 (%,d원) - %d개", rank.getMatchCount(), rank.getPrize(), count);
    }

}
