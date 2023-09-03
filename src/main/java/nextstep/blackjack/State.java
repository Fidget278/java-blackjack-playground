package nextstep.blackjack;

public interface State {
    State draw(PlayingCard playingCard);
    State stay();
    boolean isFinished();
    Cards cards();
    double profit(double betMoney);
    State winLose(boolean win);
}
