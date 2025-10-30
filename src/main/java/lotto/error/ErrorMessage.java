package lotto.error;

public enum ErrorMessage {
    INVALID_INPUT("잘못된 입력값입니다."),
    INDIVISIBLE_LOTTO_PRICE("1000원으로 나누어 떨어지지 않는 로또 가격입니다."),
    ZERO_OR_NEGATIVE_LOTTO_PRICE("로또 가격은 0보다 커야 합니다."),
    OVER_MAXIMUM_LOTTO_PRICE("로또 가격은 10만원을 넘을 수 없습니다."),
    NOT_SIX_WINNER_NUMBERS("당첨 번호가 6개가 아닙니다."),
    DUPLICATED_WINNER_NUMBERS("중복된 당첨 번호가 있습니다."),
    NOT_RANGED_WINNER_NUMBERS("1 ~ 45 사이의 당첨 번호가 아닙니다."),
    DUPLICATED_BONUS_NUMBER("당첨 번호와 중복된 보너스 번호가 있습니다."),
    NOT_RANGED_BONUS_NUMBER("1 ~ 45 사이의 보너스 번호가 아닙니다."),
    NOT_SIX_LOTTO_NUMBERS("로또 번호가 6개가 아닙니다."),
    DUPLICATED_LOTTO_NUMBERS("중복된 로또 번호가 있습니다."),
    NOT_RANGED_LOTTO_NUMBERS("1 ~ 45 사이의 로또 번호가 아닙니다.");

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
