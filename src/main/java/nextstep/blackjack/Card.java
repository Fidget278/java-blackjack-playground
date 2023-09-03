package nextstep.blackjack;

public class Card {
    private final CardNum cardNum;
    private final CardType cardType;

    public Card(CardNum num, CardType cardType) {
        this.cardNum = num;
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardNum getCardNum() {
        return cardNum;
    }

    @Override
    public String toString() {
        return cardNum.getName() + " " + cardType;
    }
}
