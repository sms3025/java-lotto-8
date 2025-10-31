package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;

public class LottoSetupInformationDto {
    private List<Lotto> lottos;
    private List<Integer> winnerNumbers;
    private Integer bonusNumber;
    private Integer lottoPrice;

    public LottoSetupInformationDto() {
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void setLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Integer> getWinnerNumbers() {
        return winnerNumbers;
    }

    public void setWinnerNumbers(List<Integer> winnerNumbers) {
        this.winnerNumbers = winnerNumbers;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(Integer bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public Integer getLottoPrice() {
        return lottoPrice;
    }

    public void setLottoPrice(Integer lottoPrice) {
        this.lottoPrice = lottoPrice;
    }
    
}
