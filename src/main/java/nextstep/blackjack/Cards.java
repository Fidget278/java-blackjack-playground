package nextstep.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Cards {

    public final static int BLACKJACK = 21;

    List<Card> cards = new ArrayList<>();

    public void receiveCard(PlayingCard playingCard) {
        cards.add(playingCard.getCard());
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBlackJack() {
        if(cards.size() != 2)
            return false;

        return calcScore() == BLACKJACK;
    }

    public boolean isBust() {
        if(calcScore() > BLACKJACK)
            return true;

        return false;
    }

    public boolean isUnderSeventeen() {
        return calcScore() < 17;
    }

    public int calcScore() {
        int sum = 0;

        for(Card card : cards) {
            CardNum cardNum = card.getCardNum();
            sum += cardNum.getScore();
        }

        return calcAce(sum);
    }

    public int calcGap() {
        return BLACKJACK - calcScore();
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
}
