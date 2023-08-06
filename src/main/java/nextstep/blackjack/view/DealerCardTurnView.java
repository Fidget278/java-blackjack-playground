package nextstep.blackjack.view;

import nextstep.blackjack.participant.Participant;

import java.util.List;

public class DealerCardTurnView extends CardTurnView implements View {

    @Override
    public void view(List<Participant> participants) {
        participants.stream().filter(participant -> participant.isDealer())
                .forEach(participant -> {
                    hit(checkDealerScore(participant), participant);
                });
    }

    private boolean checkDealerScore(Participant participant) {
        return participant.calcScore() >= 17;
    }

    @Override
    protected void hit(boolean hit, Participant participant) {
        if(!hit)
            return;

        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        super.hit(hit, participant);
    }
}