package lotto.service;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.error.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidationServiceTest {
    private InputValidationService inputValidationService;

    @BeforeEach
    void beforeEach() {
        inputValidationService = new InputValidationService();
    }

    @Test
    @DisplayName("정상 : 로또 가격으로 100000원 들어오면 에러가 발생하지 않음")
    void normalLottoPriceTest() {
        //given
        Integer lottoPrice = 100000;
        Integer expectedResult = 100;
        //when & then
        assertThatCode(() -> inputValidationService.validateLottoPrice(lottoPrice))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {900, 1800, 2100, 99999})
    @DisplayName("예외 : 로또 가격이 1000으로 나누어 떨어지지 않는 경우")
    void indivisibleLottoPriceTest(int lottoPrice) {
        //when & then
        assertThatThrownBy(() -> inputValidationService.validateLottoPrice(lottoPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INDIVISIBLE_LOTTO_PRICE.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -100000, -1})
    @DisplayName("예외 : 로또 가격이 0과 같거나 음수인 경우")
    void zeroOrNegativeLottoPriceTest(int lottoPrice) {
        //when & then
        assertThatThrownBy(() -> inputValidationService.validateLottoPrice(lottoPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.ZERO_OR_NEGATIVE_LOTTO_PRICE.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {100001, 999999, 10000000})
    @DisplayName("예외 : 로또 가격이 100000원을 넘기는 경우")
    void overMaximumLottoPriceTest(int lottoPrice) {
        //when & then
        assertThatThrownBy(() -> inputValidationService.validateLottoPrice(lottoPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.OVER_MAXIMUM_LOTTO_PRICE.getErrorMessage());
    }

    @Test
    @DisplayName("정상: 중복 없이 1 ~ 45 사이 당첨 번호 6개 들어온 경우")
    void normalWinnerNumbersTest() {
        //given
        List<Integer> winnerNumbers = List.of(1, 2, 30, 31, 44, 45);
        //when & then
        assertThatCode(() -> inputValidationService.validateWinnerNumbers(winnerNumbers))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("에러: 당첨 번호가 6개 들어오지 않은 경우")
    void doesNotSixWinnerNumbersTest() {
        //given
        List<Integer> winnerNumbers = List.of(1, 2, 30, 31, 44);
        //when & then
        assertThatThrownBy(() -> inputValidationService.validateWinnerNumbers(winnerNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_SIX_WINNER_NUMBERS.getErrorMessage());
    }

    @Test
    @DisplayName("에러: 중복된 당첨 번호가 들어온 경우")
    void duplicatedWinnerNumbersTest() {
        //given
        List<Integer> winnerNumbers = List.of(1, 2, 2, 31, 44, 45);
        //when & then
        assertThatThrownBy(() -> inputValidationService.validateWinnerNumbers(winnerNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.DUPLICATED_WINNER_NUMBERS.getErrorMessage());
    }

    @Test
    @DisplayName("에러: 1 ~ 45 사이가 아닌 당첨 번호가 들어온 경우")
    void doesNotRangedInWinnerNumbersTest() {
        //given
        List<Integer> winnerNumbers = List.of(-1, 0, 2, 31, 44, 48);
        //when & then
        assertThatThrownBy(() -> inputValidationService.validateWinnerNumbers(winnerNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_RANGED_WINNER_NUMBERS.getErrorMessage());
    }

    @Test
    @DisplayName("정상: 1 ~ 45 사이 보너스 번호가 당첨 번호와 중복 없이 들어온 경우")
    void normalBonusNumberTest() {
        //given
        List<Integer> winnerNumbers = List.of(1, 2, 30, 31, 44, 45);
        Integer bonusNumber = 7;
        //when & then
        assertThatCode(() -> inputValidationService.validateBonusNumber(winnerNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("에러: 당첨 번호와 중복된 보너스 번호가 들어온 경우")
    void duplicatedBonusNumberTest() {
        //given
        List<Integer> winnerNumbers = List.of(1, 2, 30, 31, 44, 45);
        Integer bonusNumber = 45;
        //when & then
        assertThatThrownBy(() -> inputValidationService.validateBonusNumber(winnerNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.DUPLICATED_BONUS_NUMBER.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    @DisplayName("에러: 1 ~ 45 사이를 벗어나는 보너스 번호가 들어온 경우")
    void doesNotRangedInBonusNumberTest(int bonusNumber) {
        //given
        List<Integer> winnerNumbers = List.of(1, 2, 30, 31, 44, 45);
        //when & then
        assertThatThrownBy(() -> inputValidationService.validateBonusNumber(winnerNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_RANGED_BONUS_NUMBER.getErrorMessage());
    }

}
