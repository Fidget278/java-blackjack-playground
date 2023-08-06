package nextstep.blackjack.view;

import nextstep.blackjack.participant.Participant;

import java.util.List;

public class CollectParticipantView implements View {

    @Override
    public void view(List<Participant> participants) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }
}
