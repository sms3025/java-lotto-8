package lotto.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.dto.LottoSetupInformationDto;
import lotto.service.InputValidationService;
import lotto.service.LottoService;
import lotto.utils.LottoNumberConverter;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final String delimiter = ",";
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidationService inputValidationService;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, InputValidationService inputValidationService,
                           LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputValidationService = inputValidationService;
        this.lottoService = lottoService;
    }

    public LottoSetupInformationDto setUp() {
        Integer LottoPrice = validateAndGetLottoPrice();
        Integer lottoCount = lottoService.getLottoCount(LottoPrice);
        List<Lotto> lottos = lottoService.getRandomLottos(lottoCount);
        outputView.printCountFromLottoPrice(lottos);

        List<Integer> winnerNumbers = validateAndGetWinnerNumbers();
        Integer bonusNumber = validateAndGetBonusNumber(winnerNumbers);

        return getLottoSetupInformationDto(lottos, winnerNumbers, bonusNumber, LottoPrice);
    }

    public void runWinningStatistic(LottoSetupInformationDto lottoSetupInformationDto) {
        List<Lotto> lottos = lottoSetupInformationDto.getLottos();
        List<Integer> winnerNumbers = lottoSetupInformationDto.getWinnerNumbers();
        Integer bonusNumber = lottoSetupInformationDto.getBonusNumber();
        Integer lottoPrice = lottoSetupInformationDto.getLottoPrice();

        Map<Rank, Integer> countEachRank = lottoService.getCountEachRank(lottos, winnerNumbers, bonusNumber);
        Long totalPrize = lottoService.getTotalPrize(countEachRank);
        BigDecimal rateOfReturn = lottoService.getRateOfReturn(totalPrize, lottoPrice);
        outputView.printWinningStatistics(countEachRank, rateOfReturn);
    }

    private LottoSetupInformationDto getLottoSetupInformationDto(List<Lotto> lottos, List<Integer> winnerNumbers,
                                                                 Integer bonusNumber, Integer LottoPrice) {
        LottoSetupInformationDto lottoSetupInformationDto = new LottoSetupInformationDto();
        lottoSetupInformationDto.setLottos(lottos);
        lottoSetupInformationDto.setWinnerNumbers(winnerNumbers);
        lottoSetupInformationDto.setBonusNumber(bonusNumber);
        lottoSetupInformationDto.setLottoPrice(LottoPrice);
        return lottoSetupInformationDto;
    }

    private Integer validateAndGetLottoPrice() {
        while (true) {
            try {
                Integer lottoPrice = LottoNumberConverter.parseStringToInteger(inputView.inputLottoPrice());
                inputValidationService.validateLottoPriceAndReturnCount(lottoPrice);
                return lottoPrice;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Integer validateAndGetBonusNumber(List<Integer> winnerNumbers) {
        while (true) {
            try {
                Integer bonusNumber = LottoNumberConverter.parseStringToInteger(inputView.inputBonusNumber());
                inputValidationService.validateBonusNumber(winnerNumbers, bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Integer> validateAndGetWinnerNumbers() {
        while (true) {
            try {
                List<Integer> winnerNumbers = LottoNumberConverter.splitStringToIntegerList(
                        inputView.inputWinnerNumbers(), delimiter);
                inputValidationService.validateWinnerNumbers(winnerNumbers);
                return winnerNumbers;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }


}
