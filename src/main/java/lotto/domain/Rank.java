package lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Rank {
    FIFTH_PLACE(3, false, 5000L),
    FOURTH_PLACE(4, false, 50000L),
    THIRD_PLACE(5, false, 1500000L),
    SECOND_PLACE(5, true, 30000000L),
    FIRST_PLACE(6, false, 2000000000L);

    private final Integer matchCount;
    private final Boolean hasBonusNumber;
    private final Long prize;

    Rank(Integer matchCount, Boolean hasBonusNumber, Long prize) {
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
    }

    public static Optional<Rank> getRankOfOptional(Integer matchCount, Boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> {
                    if (checkSecondOrThirdRank(matchCount, hasBonusNumber, rank)) {
                        return findSecondOrThirdRank(matchCount, hasBonusNumber, rank);
                    }
                    return findOtherRank(matchCount, rank);
                })
                .findFirst();
    }

    public Integer getMatchCount() {
        return matchCount;
    }

    public Boolean getHasBonusNumber() {
        return hasBonusNumber;
    }

    public Long getPrize() {
        return prize;
    }

    private static boolean checkSecondOrThirdRank(Integer matchCount, Boolean hasBonusNumber, Rank rank) {
        if (matchCount.equals(SECOND_PLACE.matchCount)) {
            return true;
        }
        return false;
    }

    private static boolean findSecondOrThirdRank(Integer matchCount, Boolean hasBonusNumber, Rank rank) {
        return rank.matchCount.equals(matchCount) && rank.hasBonusNumber.equals(hasBonusNumber);
    }

    private static boolean findOtherRank(Integer matchCount, Rank rank) {
        return rank.matchCount.equals(matchCount);
    }
}
