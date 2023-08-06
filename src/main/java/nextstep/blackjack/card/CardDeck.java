package nextstep.blackjack.card;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CardDeck {

    public static final int CARD_TYPE_MIN = 1;
    public static final int CARD_TYPE_MAX = 13;

    List<Card> cards;

    public CardDeck() {
        this.cards = Stream.of(CardType.values())
                .flatMap(this::addTypeCards)
                .collect(Collectors.toList());
    }

    public Stream<Card> addTypeCards(CardType type) {
        return IntStream.rangeClosed(CARD_TYPE_MIN, CARD_TYPE_MAX)
                .mapToObj(i -> new Card(CardNum.getCardNumByNum(i), type));
    }

    public Card distribute() {
        if(cards.size() <= 0)
            throw new IndexOutOfBoundsException("카드 더미에 남은 카드가 없습니다.");
        Random random = new Random();
        int randomNum = random.nextInt(cards.size());
        Card card = cards.get(randomNum);
        cards.remove(randomNum);
        return card;
    }

    public int getRemainNumberOfCards() {
        return cards.size();
    }

    // 이건 테스트를 위한 코드 아닌가..?
    public List<Card> getCardsByType(CardType cardType) {
        return cards.stream()
                .filter(card -> card.getCardType() == cardType)
                .collect(Collectors.toList());
    }
}
