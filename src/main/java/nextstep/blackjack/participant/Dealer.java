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
    public List<Card> getOpenableCards() {
        return cards.stream()
                .skip(1) // 첫 번째 요소를 제외하고 스트림 생성
                .collect(Collectors.collectingAndThen(
                        Collectors.toList()
                        , Collections::unmodifiableList));
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
