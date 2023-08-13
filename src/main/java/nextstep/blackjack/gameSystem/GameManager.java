package nextstep.blackjack.gameSystem;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.card.CardDeck;
import nextstep.blackjack.gameEnum.GameState;
import nextstep.blackjack.participant.Participants;
import nextstep.blackjack.view.View;

public class GameManager {

    private static GameManager instance = null;
    private GameState gameState;
    private Participants participants;
    private static CardDeck cardDeck;

    private GameManager() {
        participants = new Participants();
        cardDeck = new CardDeck();
    }

    public static GameManager getInstance() {
        if(instance == null)
            instance = new GameManager();

        return instance;
    }

    public Card distribute() {
        return cardDeck.distribute();
    }

    public void doNext(View view) {
        if(!view.isRunnable(gameState))
            return;

        view.view(participants);
    }

    public void changeGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
