package nextstep.blackjack.view;

import nextstep.blackjack.Participants;

import java.util.Map;

public class FirstDistributeView {

    public Map<String, String> view(Participants participants) {

        System.out.println("에게 2장의 카드를 나누었습니다.");

        participants.getAllParticipants().stream()
                .forEach(p -> System.out.println(new MakeCardString().print(p, false)));

        return null;
    }
}
