package nextstep.blackjack.view;

import nextstep.blackjack.gameEnum.GameState;
import nextstep.blackjack.participant.Participants;

public class BettingView implements View {

    @Override
    public boolean isRunnable(GameState gameState) {
        return gameState == GameState.RUN;
    }

    @Override
    public void view(Participants participants) {
        participants.getPlayers()
                .forEach(participant -> {
                    System.out.println(participant.getName() + "의 배팅 금액은?");
                    participant.bet(Integer.valueOf(Input.getInstance().input()));
                });
    }
}
