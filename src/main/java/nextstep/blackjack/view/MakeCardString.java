package nextstep.blackjack.view;

import nextstep.blackjack.Card;
import nextstep.blackjack.Cards;
import nextstep.blackjack.Participant;


public class MakeCardString {

    // todo:: isResult 빼야할듯
    public String print(Participant participant, boolean isResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(participant.getName()).append("카드 : ");
        stringBuilder.append(makeCardString(participant.getCards()));
        if(!isResult)
            return stringBuilder.toString();

        stringBuilder.append(" -  결과:").append(participant.getCards().calcScore());
        return stringBuilder.toString();
    }

    private String makeCardString(Cards cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card card : cards.getCards())
            stringBuilder.append(card).append(", ");

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));

        return stringBuilder.toString();
    }
}
