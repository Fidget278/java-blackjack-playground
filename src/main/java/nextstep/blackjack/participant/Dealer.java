package nextstep.blackjack.participant;

import nextstep.blackjack.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer extends Participant {
    public Dealer() {
        super("Dealer");
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    public void setDelaerProfit(int profit) {
        this.profit = profit;
    }
}
