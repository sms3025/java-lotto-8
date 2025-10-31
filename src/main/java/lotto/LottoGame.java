package lotto;

import lotto.controller.LottoController;
import lotto.dto.LottoSetupInformationDto;

public class LottoGame {
    private final LottoController lottoController;

    public LottoGame(LottoController lottoController) {
        this.lottoController = lottoController;
    }

    public void start() {
        LottoSetupInformationDto lottoInformationDto = lottoController.setUp();
        lottoController.runWinningStatistic(lottoInformationDto);
    }
}
