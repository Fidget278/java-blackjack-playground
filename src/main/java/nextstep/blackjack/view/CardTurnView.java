package nextstep.blackjack.view;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.gameEnum.GameState;
import nextstep.blackjack.gameSystem.GameManager;
import nextstep.blackjack.participant.Participant;
import nextstep.blackjack.participant.Participants;

import java.util.List;

public class CardTurnView implements View {

    @Override
    public boolean isRunnable(GameState gameState) {
        return gameState == GameState.RUN;
    }

    @Override
    public void view(Participants participants) {
        List<Participant> players = participants.getPlayers();
        int index = turnIndex(true, 0);
        while(index < players.size() &&  !players.get(index).isBust()) {
            Participant player = players.get(index);

            System.out.println();
            System.out.println(player.getName() + "는 카드를 더 받습니까?(y, n)");

            boolean isY = "y".equals(Input.getInstance().input());
            hit(isY, player);
            index = turnIndex(isY, index);
        }
    }

    private int turnIndex(boolean oneMore, int index) {
        if(oneMore)
            return index;

        return ++index;
    }

    protected void hit(boolean hit, Participant participant) {
        if(!hit)
            return;

        participant.receiveCard(GameManager.getInstance().distribute());
        new PrintCards().print(participant.getOpenableCards());
    }
}
