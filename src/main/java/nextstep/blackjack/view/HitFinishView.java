package nextstep.blackjack.view;


import nextstep.blackjack.Cards;
import nextstep.blackjack.Participant;
import nextstep.blackjack.Participants;

import java.util.Map;

public class HitFinishView {
    public Map<String, String> view(Participant participant) {

        System.out.println(new MakeCardString().print(participant, false));
        return null;
    }
}
