package nextstep.blackjack.view;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.participant.Participant;

import java.util.List;

public class CardOpenView implements View {

    @Override
    public void view(List<Participant> participants) {
        for(Participant participant : participants) {
            System.out.print(participant.getName() + " : ");
            printCards(participant.getOpenableCards());
        }
        participants.stream()
                .forEach(participant -> participant.getOpenableCards());
    }

    private void printCards(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card card : cards) {
            stringBuilder.append(card).append(", ");
        }

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        System.out.println(stringBuilder);
    }
}
