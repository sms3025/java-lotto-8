package lotto.utils;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator {
    public static List<Integer> getRandomUniqueNumberList(Integer startNumber, Integer endNumber, Integer count) {
        return Randoms.pickUniqueNumbersInRange(startNumber, endNumber, count);
    }
}
