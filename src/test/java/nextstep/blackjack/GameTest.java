package nextstep.blackjack;

import nextstep.blackjack.card.CardDeck;
import nextstep.blackjack.participant.Dealer;
import nextstep.blackjack.participant.Participant;
import nextstep.blackjack.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    Participant player1;
    Participant player2;
    Participant dealer;

    List<Participant> players;

    CardDeck cardDeck;

    @BeforeEach
    public void 플레이어_초기화() {
        player1 = new Player("phobi");
        player2 = new Player("jacob");
        dealer = new Dealer();

        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(dealer);

        cardDeck = new CardDeck();
    }

    @Test
    public void 배팅() {
        // 배팅을 확인하려고 보일러플레이트를 만들면 캡슐화가 괜찮은가 ..
        // 딱히 게임할 땐 굳이 확인할 필요도 없는데
        ((Player) player1).betting(10000);
        ((Player) player2).betting(20000);
    }

    @Test
    public void 시작_카드_공개() {
        players.stream().forEach(participant -> participant.receiveCard(cardDeck.distribute()));
        players.stream().forEach(participant -> participant.receiveCard(cardDeck.distribute()));

        for(Participant participant : players) {
            System.out.println(participant.getName() + "의 카드");
            participant.getOpenableCards().stream().forEach(System.out::println);
        }
    }


}
