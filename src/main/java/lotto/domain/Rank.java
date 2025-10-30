package lotto.domain;

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
        return null;
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
}
