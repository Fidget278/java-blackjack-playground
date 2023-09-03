package nextstep.blackjack;

public class Running extends Started {

    public Running(Cards cards) {
        super(cards);
    }

    public Running() {

    }

    public boolean isFinished() {
        return false;
    }

    public double profit(double betMoney) {
        return 0;
    }
}
