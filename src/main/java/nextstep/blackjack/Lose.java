package nextstep.blackjack;

public class Lose extends Finished {
    public Lose(Cards cards) {
        super(cards);
    }
    @Override
    public double earningRate() {
        return -1;
    }
}

