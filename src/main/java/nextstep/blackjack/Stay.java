package nextstep.blackjack;

public class Stay extends Finished {
    public Stay() {
        super();
    }
    public Stay(Cards cards) {
        super(cards);
    }
    @Override
    public double earningRate() {
        return 1;
    }

    @Override
    public State winLose(boolean win) {
        if(win)
            return new Win(cards);

        return new Lose(cards);
    }
}
