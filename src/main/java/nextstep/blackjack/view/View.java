package nextstep.blackjack.view;

import nextstep.blackjack.gameEnum.GameState;
import nextstep.blackjack.participant.Participants;

public interface View {
    boolean isRunnable(GameState gameState);
    void view(Participants participants);
}
