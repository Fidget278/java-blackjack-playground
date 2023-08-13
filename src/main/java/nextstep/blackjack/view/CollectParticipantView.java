package nextstep.blackjack.view;

import nextstep.blackjack.gameEnum.GameState;
import nextstep.blackjack.participant.Dealer;
import nextstep.blackjack.participant.Participant;
import nextstep.blackjack.participant.Participants;

import java.util.Arrays;

public class CollectParticipantView implements View {

    @Override
    public boolean isRunnable(GameState gameState) {
        return gameState == GameState.RUN;
    }

    @Override
    public void view(Participants participants) {
        participants.addDealer(new Dealer());
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String[] names = Input.getInstance().input().replaceAll("\\s+", "").split(",");
        Arrays.stream(names).forEach(name -> participants.addPlayer(new Participant(name)));
    }
}
