package nextstep.blackjack.view;

import nextstep.blackjack.card.Card;

import java.util.List;

public class PrintCards {
    public void print(List< Card > cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card card : cards)
            stringBuilder.append(card).append(", ");

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        System.out.print(stringBuilder + " ");
    }
}
