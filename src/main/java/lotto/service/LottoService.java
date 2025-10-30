package lotto.service;

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
    private static final Integer ADD_COUNT = 1;

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
