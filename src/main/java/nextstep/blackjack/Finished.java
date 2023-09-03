package nextstep.blackjack;

public abstract class Finished extends Started {
    public Finished(Cards cards) {
        super(cards);
    }

    public Finished() {

    }

    @Override
    public State draw(PlayingCard playingCard) {
        return super.draw(playingCard);
    }

    @Override
    public State stay() {
        return super.stay();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Cards cards() {
        return super.cards();
    }

    @Override
    public double profit(double betMoney) {
        return betMoney * earningRate();
    }

    public double earningRate() {
        return 0;
    }

}
