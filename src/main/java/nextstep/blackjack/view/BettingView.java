package nextstep.blackjack.view;

import nextstep.blackjack.participant.Participant;

import java.util.List;

public class BettingView implements View {

    @Override
    public void view(List<Participant> participants) {
        participants.stream()
                .filter(participant -> !participant.isDealer())
                .forEach(participant -> {
                    System.out.println(participant.getName() + "의 배팅 금액은?");

                    // 숫자 아닌 거 들어올 경우 예외처리 추가해야함
                    participant.bet(Integer.valueOf(Input.getInstance().input()));
                });
    }
}
