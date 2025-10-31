package lotto.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class LottoService {
    private static final Integer INITIAL_COUNT = 0;
    private static final Long INITIAL_PRIZE = 0L;
    private static final Integer ADD_COUNT = 1;
    private static final Integer DIVIDED_DECIMAL_POINT = 20;
    private static final Integer PERCENT = 100;
    private static final Integer DECIMAL_PLACE = 1;

    public Map<Rank, Integer> getCountEachRank(List<Lotto> lottos, List<Integer> winnerNumbers, Integer bonusNumber) {
        EnumMap<Rank, Integer> countOfRank = new EnumMap<>(Rank.class);
        initCountOfRank(countOfRank);
        Set<Integer> winnerNumberGroup = new HashSet<>(winnerNumbers);

        lottos.forEach(lotto -> {
            Set<Integer> numberOfWinners = new HashSet<>(lotto.getNumbers());
            numberOfWinners.retainAll(winnerNumberGroup);

            checkRankAndAddCount(bonusNumber, numberOfWinners, lotto.getNumbers(), countOfRank);
        });
        return countOfRank;
    }

    public Long getTotalPrize(Map<Rank, Integer> countOfRank) {
        Long totalPrize = INITIAL_PRIZE;
        for (Map.Entry<Rank, Integer> entry : countOfRank.entrySet()) {
            Rank rank = entry.getKey();
            Integer count = entry.getValue();
            totalPrize += rank.getPrize() * count;
        }
        return totalPrize;
    }

    public BigDecimal getRateOfReturn(Long totalPrize, Integer lottoPrice) {
        BigDecimal preciseTotalPrize = BigDecimal.valueOf(totalPrize);
        BigDecimal preciseLottoPrice = BigDecimal.valueOf(lottoPrice);

        return preciseTotalPrize.divide(preciseLottoPrice, DIVIDED_DECIMAL_POINT, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(PERCENT))
                .setScale(DECIMAL_PLACE, RoundingMode.HALF_UP);
    }

    private void checkRankAndAddCount(Integer bonusNumber, Set<Integer> numberOfWinners, List<Integer> lottoNumbers,
                                      EnumMap<Rank, Integer> countOfRank) {
        Integer matchCount = numberOfWinners.size();
        Boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);
        Optional<Rank> rankOfOptional = Rank.getRankOfOptional(matchCount, hasBonusNumber);
        rankOfOptional.ifPresent(rank -> countOfRank.put(rank, countOfRank.get(rank) + ADD_COUNT));
    }

    private void initCountOfRank(EnumMap<Rank, Integer> countOfRank) {
        for (Rank rank : Rank.values()) {
            countOfRank.put(rank, INITIAL_COUNT);
        }
    }
}
