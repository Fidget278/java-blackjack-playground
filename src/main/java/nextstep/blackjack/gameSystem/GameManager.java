package nextstep.blackjack.gameSystem;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.card.CardDeck;
import nextstep.blackjack.gameState.GameState;
import nextstep.blackjack.participant.Dealer;
import nextstep.blackjack.participant.Participant;
import nextstep.blackjack.participant.Player;
import nextstep.blackjack.view.Input;
import nextstep.blackjack.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameManager {

    private static GameManager instance = null;

    private List<Participant> participants;
    private static CardDeck cardDeck;

    private GameManager() {
        participants = new ArrayList<>();
        cardDeck = new CardDeck();
    }

    public static GameManager getInstance() {
        if(instance == null)
            instance = new GameManager();

        return instance;
    }

    public void start(View view) {
        GameState.start(); // 필요한가?


        addDealer();

        view.view(null);

        String[] names = Input.getInstance().input().replaceAll("\\s+", "").split(",");
        Arrays.stream(names).forEach(name -> addPlayer(name));

        // debug
        //participants.stream().forEach(participant -> System.out.println(participant.getName()));
    }

    public void betting(View view) {
        view.view(participants);

        // debug
        //participants.stream().forEach(participant -> System.out.println(participant.getBetMoney()));
    }

    public void cardOpen(View view) {
        view.view(participants);
    }

    public void addPlayer(String name) {
        participants.add(new Player(name));
    }

    public void addDealer() {
        participants.add(new Dealer());
    }

    public void distributeCards(View view) {
        view.view(participants);
    }

    public Card distribute() {
        return cardDeck.distribute();
    }

    public void doNext(View view) {
        view.view(participants);
    }

    public boolean isBust() {
        return participants.stream()
                .filter(participant -> !participant.isDealer())
                .anyMatch(participant -> participant.calcScore() >= 21);
    }
}
