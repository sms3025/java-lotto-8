package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String LOTTO_PRICE_INPUT_MESSAGE = "구입금액을 입력해 주세요. (최대 10만원 까지, 1000원 단위로 구매 가능합니다.)";
    private static final String WINNER_NUMBERS_INPUT_MESSAGE = "당첨 번호를 입력해 주세요. (쉼표(,)로 구분합니다.)";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";

    public InputView() {
    }

    public String inputLottoPrice() {
        OutputView.printMessage(LOTTO_PRICE_INPUT_MESSAGE);
        return Console.readLine();
    }

    public String inputWinnerNumbers() {
        OutputView.printMessage(WINNER_NUMBERS_INPUT_MESSAGE);
        return Console.readLine();
    }

    public String inputBonusNumber() {
        OutputView.printMessage(BONUS_NUMBER_INPUT_MESSAGE);
        return Console.readLine();
    }
}
