package nextstep.blackjack;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CardDeck {
    public static final int CARD_TYPE_MIN = 1;
    public static final int CARD_TYPE_MAX = 13;

    private static CardDeck instance = null;

    private List<Card> cards;

    private CardDeck() {
        this.cards = Stream.of(CardType.values())
                .flatMap(this::addTypeCards)
                .collect(Collectors.toList());
    }

    public static CardDeck getInstance() {
        if(instance == null)
            instance = new CardDeck();

        return instance;
    }

    public Stream<Card> addTypeCards(CardType type) {
        return IntStream.rangeClosed(CARD_TYPE_MIN, CARD_TYPE_MAX)
                .mapToObj(i -> new Card(CardNum.getCardNumByNum(i), type));
    }

    public Card distribute() {
        if(cards.size() <= 0)
            throw new IndexOutOfBoundsException("카드 더미에 남은 카드가 없습니다.");

        Random random = new Random();
        return cards.remove(random.nextInt(cards.size()));
    }
}
