package nextstep.blackjack;

public class Hit extends Running {
    public Hit() {
        super();
    }
    public Hit(Cards cards) {
        super(cards);
    }
    @Override
    public State draw(PlayingCard playingCard) {

        if(playingCard != null && playingCard.getCard() != null)
            cards.receiveCard(playingCard);

        if(cards.isBlackJack())
            return new Blackjack(cards);

        if(cards.isBust())
            return new Bust(cards);

        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
