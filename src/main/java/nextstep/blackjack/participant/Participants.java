package nextstep.blackjack.participant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private Participant dealer;
    private List<Participant> players;
    private List<Participant> participants;

    public Participants() {
        players = new ArrayList<>();
        participants = new ArrayList<>();
    }

    public Dealer getDealer() {
        return (Dealer) dealer;
    }

    public List<Participant> getPlayers() {
        return players;
    }

    public List<Participant> getAllParticipants() {
        return participants;
    }

    public void addDealer(Participant dealer) {
        participants.add(dealer);
        this.dealer = dealer;
    }

    public void addPlayer(Participant player) {
        participants.add(player);
        players.add(player);
    }

    public void finishGame() {
        getAllParticipants().stream().forEach(Participant::calcScore);

        if(!checkBlackJack())
            winLose();
    }

    private boolean checkBlackJack() {

        if(dealer.isBlackJack()) {
            calcAllProfit(0, dealer);
            return true;
        }

        List<Participant> blackJackWinners =
                getPlayers().stream()
                        .filter(Participant::isBlackJack)
                        .collect(Collectors.toList());

        if(!blackJackWinners.isEmpty()) {
            calcAllProfit(1.5, blackJackWinners.get(0));
            return true;
        }

        return false;
    }
    private void winLose() {
        int profitMultiplier = 1;

        Participant winner = getAllParticipants().stream()
                .filter(p -> !p.isBust())
                .min(Comparator.comparingInt(Participant::calcGap)).get();

        if(winner.isTie(dealer.getScore())) {
            winner = dealer;
            profitMultiplier = 0;
        }

        calcAllProfit(profitMultiplier, winner);
    }

    private void calcAllProfit(double profitMultiplier, Participant winner) {
        int betMoneySum = 0;

        for(Participant participant : players) {
            int profit = participant.calcProfit(participant.isTie(winner.getScore()), profitMultiplier, participant.getBetMoney());
            betMoneySum -= profit;
        }

        getDealer().setDealerProfit(betMoneySum);
    }
}
