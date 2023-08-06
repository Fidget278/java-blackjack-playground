package nextstep.blackjack.participant;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.card.CardNum;
import nextstep.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participant {
    protected String name;
    protected int betMoney = 0;
    protected int profit = 0;
    protected List<Card> cards = new ArrayList<>();

    public final static int BLACKJACK = 21;

    public Participant(String name) {
        if(StringUtils.isEmpty(name))
            throw new IllegalArgumentException("Participant Name Required.");

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public List<Card> getOpenableCards() {
        return Collections.unmodifiableList(cards);
    }

    public int calcScore() {
        int score = 0;

        for(Card card : cards) {
            CardNum cardNum = card.getCardNum();
            score += cardNum.getScore();
        }

        if(isContainAce())
            return calcAce(score);

        return score;
    }

    private int calcAce(int score) {

        if(score >= BLACKJACK)
            return score;

        int newScore = score + 10;

        if(newScore > BLACKJACK)
            return score;

        if(BLACKJACK - newScore > BLACKJACK - score)
            return score;

        return newScore;
    }

    private boolean isContainAce() {
        return cards.stream()
                .anyMatch(card -> card.getCardNum().isAce());
    }

    public void bet(int money) {
        betMoney = money;
    }

    public boolean isDealer() {
        return false;
    }

    public int getBetMoney() {
        return betMoney;
    }
}
