package lotto.config;

import lotto.controller.LottoController;
import lotto.service.InputValidationService;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    public InputView getInputView() {
        return new InputView();
    }

    public OutputView getOutputView() {
        return new OutputView();
    }

    public InputValidationService getInputValidationService() {
        return new InputValidationService();
    }

    public LottoService getLottoService() {
        return new LottoService();
    }

    public LottoController getLottoController() {
        return new LottoController(
                getInputView(), getOutputView(), getInputValidationService(), getLottoService()
        );
    }
}
