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
    protected int score = 0;

    public final static int BLACKJACK = 21;

    public Participant(String name) {
        if(StringUtils.isEmpty(name))
            throw new IllegalArgumentException("Participant Name Required.");

        this.name = name;
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

        this.score = score;

        return calcAce(score);
    }

    public int calcGap() {
        return BLACKJACK - score;
    }

    private int calcAce(int score) {

        if(!isContainAce())
            return score;

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

    public boolean isBlackJack() {
        return calcScore() == BLACKJACK;
    }

    public boolean isTie(int score) {
        return calcScore() == score;
    }

    public boolean isBust() {
        if(calcScore() > BLACKJACK) {
            System.out.println(getName() + "버스트임");
            return true;
        }

        System.out.println(getName() + "버스트 아님");
        return false;
    }

    public int getBetMoney() {
        return betMoney;
    }

    public int calcProfit(boolean isTie, double profitMultiplier, int betMoney) {
        if(isTie) {
            profit = (int) (betMoney * profitMultiplier);
            return profit;
        }

        profit = betMoney * -1;
        return profit;
    }


    public void bet(int money) {
        betMoney = money;
    }

    public boolean isDealer() {
        return false;
    }

    public int getScore() {
        return calcScore();
    }

    public String getName() {
        return name;
    }

    public int getProfit() { return profit; }
}
