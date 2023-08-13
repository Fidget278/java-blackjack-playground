package nextstep.blackjack.view;

import nextstep.blackjack.gameEnum.GameState;
import nextstep.blackjack.gameSystem.GameManager;
import nextstep.blackjack.participant.Participant;
import nextstep.blackjack.participant.Participants;

public class DealerCardTurnView extends CardTurnView implements View {

    @Override
    public boolean isRunnable(GameState gameState) {
        return gameState == GameState.RUN;
    }

    @Override
    public void view(Participants participants) {
        hit(checkDealerScore(participants.getDealer()), participants.getDealer());
        GameManager.getInstance().changeGameState(GameState.FINISH);
    }

    private boolean checkDealerScore(Participant participant) {
        return participant.calcScore() >= 17;
    }

    @Override
    protected void hit(boolean hit, Participant participant) {
        if(!hit) {
            System.out.println("딜러는 카드를 받지 않았습니다.");
            return;
        }

        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        participant.receiveCard(GameManager.getInstance().distribute());
    }
}