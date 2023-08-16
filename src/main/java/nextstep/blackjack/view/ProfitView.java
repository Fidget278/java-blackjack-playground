package nextstep.blackjack.view;

import nextstep.blackjack.gameEnum.GameState;
import nextstep.blackjack.participant.Dealer;
import nextstep.blackjack.participant.Participant;
import nextstep.blackjack.participant.Participants;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProfitView implements View {

    @Override
    public boolean isRunnable(GameState gameState) {
        return gameState == GameState.FINISH;
    }

    @Override
    public void view(Participants participants) {
        participants.finishGame();
        printResult(participants);
        printProfit(participants);
    }

    public void printResult(Participants participants) {

        PrintCards printCards = new PrintCards();
        for(Participant participant : participants.getAllParticipants()) {
            System.out.print(participant.getName() + " - ");
            printCards.print(participant.getOpenableCards());
            System.out.println("결과 : " + participant.getScore());
        }
    }

    public void printProfit(Participants participants) {
        System.out.println();
        System.out.println("### 최종 수익");
        for(Participant participant : participants.getAllParticipants()) {
            System.out.println(participant.getName() + " : " + participant.getProfit());
        }
    }


}
