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

        participants.getAllParticipants().stream().forEach(Participant::calcScore);

        if(!checkBlackJack(participants))
            winLose(participants);

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

    private boolean checkBlackJack(Participants participants) {
        Participant dealer = participants.getDealer();

        if(dealer.isBlackJack()) {
            calcAllProfit(participants, 0, dealer, dealer);
            return true;
        }

        List<Participant> blackJackWinners =
                participants.getPlayers().stream()
                        .filter(Participant::isBlackJack)
                        .collect(Collectors.toList());

        if(!blackJackWinners.isEmpty()) {
            calcAllProfit(participants, 1.5, blackJackWinners.get(0), dealer);
            return true;
        }

        return false;
    }
    private void winLose(Participants participants) {

        Participant dealer = participants.getDealer();
        int profitMultiplier = 1;

        Participant winner = participants.getAllParticipants().stream()
                .filter(p -> !p.isBust())
                .min(Comparator.comparingInt(Participant::calcGap)).get();

        if(winner.isTie(dealer.getScore())) {
            winner = dealer;
            profitMultiplier = 0;
        }

        calcAllProfit(participants, profitMultiplier, winner, dealer);
    }

    private void calcAllProfit(Participants participants, double profitMultiplier, Participant winner, Participant dealer) {
        int betMoneySum = 0;

        for(Participant participant : participants.getPlayers()) {
            int profit = participant.calcProfit(participant.isTie(winner.getScore()), profitMultiplier, participant.getBetMoney());
            betMoneySum -= profit;
        }

        ((Dealer) dealer).setDelaerProfit(betMoneySum);
    }
}
