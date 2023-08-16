package nextstep.blackjack;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.card.CardDeck;
import nextstep.blackjack.card.CardNum;
import nextstep.blackjack.card.CardType;
import nextstep.blackjack.participant.Dealer;
import nextstep.blackjack.participant.Participant;
import nextstep.blackjack.participant.Participants;
import nextstep.blackjack.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.Caret;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Test
    public void 에이스_점수_계산_AA() {
        player1.receiveCard(new Card(CardNum.ACE, CardType.CLOVER));
        player1.receiveCard(new Card(CardNum.ACE, CardType.DIAMOND));
        assertThat(player1.calcScore()).isEqualTo(12);
    }

    @Test
    public void 에이스_점수_계산_AJ() {
        player1.receiveCard(new Card(CardNum.ACE, CardType.CLOVER));
        player1.receiveCard(new Card(CardNum.QUEEN, CardType.DIAMOND));
        assertThat(player1.calcScore()).isEqualTo(20);
    }

    @Test
    public void 블랙잭_체크() {
        dealer.receiveCard(new Card(CardNum.ACE, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNum.TEN, CardType.CLOVER));

        assertThat(dealer.isBlackJack()).isEqualTo(true);

        player1.receiveCard(new Card(CardNum.ACE, CardType.CLOVER));
        player1.receiveCard(new Card(CardNum.TWO, CardType.DIAMOND));

        assertThat(player1.isBlackJack()).isEqualTo(false);

        player2.receiveCard(new Card(CardNum.SIX, CardType.CLOVER));
        player2.receiveCard(new Card(CardNum.TEN, CardType.DIAMOND));

        assertThat(player2.isBlackJack()).isEqualTo(false);
    }

    @Test
    public void 버스트_체크() {
        dealer.receiveCard(new Card(CardNum.TEN, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNum.TEN, CardType.DIAMOND));
        dealer.receiveCard(new Card(CardNum.SIX, CardType.CLOVER));

        assertThat(dealer.isBust()).isEqualTo(true);

        player1.receiveCard(new Card(CardNum.ACE, CardType.CLOVER));
        player1.receiveCard(new Card(CardNum.TWO, CardType.DIAMOND));

        assertThat(player1.isBust()).isEqualTo(false);
    }

    @Test
    public void 타이_체크() {
        dealer.receiveCard(new Card(CardNum.TEN, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNum.SIX, CardType.DIAMOND));

        player1.receiveCard(new Card(CardNum.EIGHT, CardType.CLOVER));
        player1.receiveCard(new Card(CardNum.EIGHT, CardType.DIAMOND));

        assertThat(player1.isTie(dealer.getScore())).isEqualTo(true);

        player2.receiveCard(new Card(CardNum.EIGHT, CardType.CLOVER));
        player2.receiveCard(new Card(CardNum.SEVEN, CardType.DIAMOND));

        assertThat(player2.isTie(dealer.getScore())).isEqualTo(false);
    }

    @Test
    public void 딜러_블랙잭_수익() {
        dealer.receiveCard(new Card(CardNum.ACE, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNum.TEN, CardType.DIAMOND));

        player1.receiveCard(new Card(CardNum.EIGHT, CardType.CLOVER));
        player1.receiveCard(new Card(CardNum.EIGHT, CardType.DIAMOND));
        player1.bet(10000);

        player2.receiveCard(new Card(CardNum.EIGHT, CardType.CLOVER));
        player2.receiveCard(new Card(CardNum.SEVEN, CardType.DIAMOND));
        player2.bet(20000);

        Participants participants = new Participants();
        participants.addDealer(dealer);
        participants.addPlayer(player1);
        participants.addPlayer(player2);

        participants.finishGame();
        assertThat(dealer.getProfit()).isEqualTo(30000);
        assertThat(player1.getProfit()).isEqualTo(-10000);
        assertThat(player2.getProfit()).isEqualTo(-20000);
    }

    @Test
    public void 블랙잭_딜러_플레이어_동점_수익() {
        dealer.receiveCard(new Card(CardNum.ACE, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNum.TEN, CardType.DIAMOND));

        player1.receiveCard(new Card(CardNum.ACE, CardType.CLOVER));
        player1.receiveCard(new Card(CardNum.TEN, CardType.DIAMOND));
        player1.bet(10000);

        player2.receiveCard(new Card(CardNum.EIGHT, CardType.CLOVER));
        player2.receiveCard(new Card(CardNum.SEVEN, CardType.DIAMOND));
        player2.bet(20000);

        Participants participants = new Participants();
        participants.addDealer(dealer);
        participants.addPlayer(player1);
        participants.addPlayer(player2);

        participants.finishGame();

        assertThat(dealer.getProfit()).isEqualTo(20000);
        assertThat(player1.getProfit()).isEqualTo(10000);
        assertThat(player2.getProfit()).isEqualTo(-20000);
    }

    // 딜러 블랙잭, 플레이어 동점 수익 체크
    // 플레이어 블랙잭, 수익 체크
    // 딜러 버스트, 플레이어 동점 수익 체크
    // 딜러 버스트, 플레이어 동점 없음 수익 체크
    // 딜러랑 플레이어 동점 승리 수익 체크
    // 플레이어1과 플레이어2 동점 승리 수익 체크

}
