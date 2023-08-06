package nextstep.blackjack;

import nextstep.blackjack.gameSystem.GameManager;
import nextstep.blackjack.view.*;

public class BlackJackApplication {
    public static void main(String[] args) {
        GameManager gameManager = GameManager.getInstance();

        gameManager.start(new CollectParticipantView());
        gameManager.betting(new BettingView());
        gameManager.distributeCards(new FirstDistributeView());
        gameManager.cardOpen(new CardOpenView());

        while(gameManager.isBust()) {
            gameManager.doNext(new CardTurnView());
            gameManager.doNext(new DealerCardTurnView());
            gameManager.doNext(new CardOpenView());
        }

        // 결과뷰
        // 아 에반데 ㅡㅡ
    }
}
