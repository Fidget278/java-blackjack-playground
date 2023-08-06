package nextstep.blackjack.view;

import nextstep.blackjack.gameSystem.GameManager;
import nextstep.blackjack.participant.Participant;

import java.util.List;

public class FirstDistributeView implements View {

    @Override
    public void view(List<Participant> participants) {
        StringBuilder ui_string = new StringBuilder();

        for(Participant participant : participants) {
            participant.receiveCard(GameManager.getInstance().distribute());
            participant.receiveCard(GameManager.getInstance().distribute());
            ui_string.append(participant.getName()).append(",");
        }

        ui_string.deleteCharAt(ui_string.lastIndexOf(","));
        ui_string.append("에게 2장의 카드를 나누었습니다.");
        System.out.println(ui_string);
    }
}
