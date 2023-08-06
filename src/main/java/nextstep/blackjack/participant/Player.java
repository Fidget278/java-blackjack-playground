package nextstep.blackjack.participant;

public class Player extends Participant {
    private String name;
    private int betMoney = 0;
    private int profit = 0;
    private int score = 0;

    public Player(String name) {
        super(name);
        this.name = name;
    }

    public void betting(int betMoney) {
        this.betMoney = betMoney;
    }
}
