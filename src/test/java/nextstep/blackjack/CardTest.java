package nextstep.blackjack;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.card.CardDeck;
import nextstep.blackjack.card.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTest {

    CardDeck cardDeck;

    @BeforeEach
    public void cardInit() {
        cardDeck = new CardDeck();
    }

    @Test
    public void 카드_덱_생성() {
        assertThat(cardDeck.getRemainNumberOfCards()).isEqualTo(52);
    }

    @Test
    public void 카드_종류별_갯수_검사() {
        for(CardType cardType : CardType.values())
            assertThat(cardDeck.getCardsByType(cardType).size()).isEqualTo(CardDeck.CARD_TYPE_MAX);
    }

    @Test
    public void 카드_분배_검사() {
        Set<Card> verifySet = new HashSet<>();

        int count = CardDeck.CARD_TYPE_MAX * CardType.values().length;
        while(--count >= 0) {
            Card card = cardDeck.distribute();
            assertThat(verifySet.contains(card)).isFalse();
            verifySet.add(card);
        }

        assertThat(verifySet.size()).isEqualTo(52);
        assertThrows(IndexOutOfBoundsException.class, () -> cardDeck.distribute());
    }

    @Test
    public void card_distribution_not_sequential() {
        // ... 어케 검사함? 우연히 연속성을 띄어서 나오면 어캄;;
        // 처음에 만들어진 리스트 형태를 저장하고 나중에 카드 다 뽑고나서 뽑은 순서대로 리스트 만들고
        // 처음 형태의 리스트랑 비교해야되나?

        // 일단 육안 확인;;
        int count = CardDeck.CARD_TYPE_MAX * CardType.values().length;
        for(Card card : cardDeck.getCardsByType(CardType.DIAMOND))
            System.out.println(card);
//        while(--count >= 0) {
//            System.out.println(cardDeck.distribute());
//        }


    }

//    @ParameterizedTest
//    @EnumSource(CardType.class)
//    public void 카드_출력_검사(CardType cardType) {
//        for (Card card : cardDeck.getCardsByType(cardType)) {
//            // ?
//        }
//    }


}
