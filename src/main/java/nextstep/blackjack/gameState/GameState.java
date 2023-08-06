package nextstep.blackjack.gameState;

public class GameState {
    private static StateEnum state = StateEnum.READY;

    public static void start() {
        state = StateEnum.START;
    }

    public static void run() {
        state = StateEnum.RUN;
    }

    public static void end() {
        state = StateEnum.END;
    }

    // 외부에서 변경 가능한 위험성이 있는거 아닌가?
    public StateEnum getState() {
        return state;
    }
}
