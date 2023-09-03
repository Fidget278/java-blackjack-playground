package nextstep.blackjack;

import java.util.List;

public class Participant {
    protected State state = new Hit();
    private String name;
    private double betMoney;

    public Participant(String name) {
        this.name = name;
    }

    public double getBetMoney() {
        return betMoney;
    }
    public void finish(Participant winner) {
        state = state.winLose(this == winner);
    }

    public boolean doHit(boolean isHit) {
        state = hit(isHit);
        return isHit;
    }
    public State hit(boolean isHit) {
        if(isHit) {
            PlayingCard playingCard = new PlayingCard(CardDeck.getInstance().distribute());
            return state.draw(playingCard);
        }

        return state.stay();
    }

    public double getProfit() {
        return state.profit(betMoney);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void betting(double betMoney) {
        this.betMoney = betMoney;
    }

    public String getName() {
        return name;
    }


    public boolean isDealer() {
        return false;
    }


    public boolean isPlayer() {
        return true;
    }

    public Cards getCards() {
        return state.cards();
    }
}
