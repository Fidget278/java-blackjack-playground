package nextstep.blackjack;

import nextstep.blackjack.gameEnum.GameState;
import nextstep.blackjack.gameSystem.GameManager;
import nextstep.blackjack.view.*;

import java.util.Arrays;
import java.util.List;

public class BlackJackApplication {
    public static void main(String[] args) {
        GameManager gameManager = GameManager.getInstance();

        List<View> gameViews = Arrays.asList(
                new CollectParticipantView(),
                new BettingView(),
                new FirstDistributeView(),
                new CardTurnView(),
                new DealerCardTurnView(),
                new ProfitView()
        );

        gameManager.changeGameState(GameState.RUN);
        gameViews.stream().forEach(v -> gameManager.doNext(v));
    }
}
