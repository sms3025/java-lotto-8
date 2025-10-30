package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    @DisplayName("정상: 일반적인 로또 번호 입력시 정렬되서 보여주는 테스트")
    void normalLottoNumbersAndAscendingOrderTest() {
        //given
        List<Integer> numbers = List.of(1, 2, 20, 21, 5, 45);
        List<Integer> expectedNumbers = List.of(1, 2, 5, 20, 21, 45);
        //when
        List<Integer> resultNumbers = new Lotto(numbers).getNumbers();
        //then
        assertThat(resultNumbers).isEqualTo(expectedNumbers);

    }

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_SIX_LOTTO_NUMBERS.getErrorMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.DUPLICATED_LOTTO_NUMBERS.getErrorMessage());
    }

    @Test
    @DisplayName("예외: 1 ~ 45 사이의 숫자가 아닐경우, 예외가 발생한다.")
    void doesNotRangedInLottoNumbersTest() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_RANGED_LOTTO_NUMBERS.getErrorMessage());
    }
}
