package nextstep.blackjack;

public class Started implements State {
    Cards cards;


    public Started() {
        cards = new Cards();
    }

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(PlayingCard playingCard) {
        return null;
    }

    @Override
    public State stay() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public double profit(double betMoney) {
        return 0;
    }

    @Override
    public State winLose(boolean win) {
        return null;
    }
}
