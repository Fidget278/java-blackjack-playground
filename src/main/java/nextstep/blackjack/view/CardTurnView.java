package nextstep.blackjack.view;

import nextstep.blackjack.gameSystem.GameManager;
import nextstep.blackjack.participant.Participant;

import java.util.List;

public class CardTurnView implements View {
    @Override
    public void view(List<Participant> participants) {
        participants.stream()
                .filter(participant -> !participant.isDealer())
                .forEach(participant -> {
                    System.out.println(participant.getName() + "는 카드를 더 받습니까?(y, n)");
                    hit("y".equals(Input.getInstance().input()), participant);
                });
    }

    protected void hit(boolean hit, Participant participant) {
        if(!hit)
            return;

        participant.receiveCard(GameManager.getInstance().distribute());
    }
}
