package nextstep.blackjack.view;

import nextstep.blackjack.Input;
import nextstep.blackjack.Participants;

import java.util.HashMap;
import java.util.Map;

public class BettingView {
    public Map<String, String> view(Participants participants) {
        participants.getPlayers()
                .forEach(participant -> {
                    System.out.println(participant.getName() + "의 배팅 금액은?");
                    participant.betting(Integer.valueOf(Input.getInstance().input()));
                });

        // 원래는 이름-배팅금액 리턴해야함
        return null;
    }
}
