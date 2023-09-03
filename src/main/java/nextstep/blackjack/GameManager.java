package nextstep.blackjack;

import nextstep.blackjack.view.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    private Participants participants;

    public void start() {
        Map<String, String> result  = (new CollectParticipantView()).view();
        participants = new Participants(result.get("names"));
    }

    public void betting() {
        new BettingView().view(participants);
        participants.setDealerBetMoney();
    }

    public void firstDistribute() {

        List<Participant> allParticipants = participants.getAllParticipants();

        for(Participant participant : allParticipants) {
            participant.hit(true);
            participant.hit(true);
        }

        new FirstDistributeView().view(participants);
    }

    public boolean checkBlackJack() {
        Participant dealer = participants.getDealer();

        return false;
        // 딜러가 블랙잭이면 게임 종료
    }

    public void running() {
        List<Participant> players = participants.getPlayers();

        HitFinishView hitFinishView = new HitFinishView();
        for(Participant player : players) {
            hitPerPlayer(player);
            hitFinishView.view(player);
        }

        Participant dealer = participants.getDealer();

        // 파라미터가 true로 들어가서 나중에 헷갈릴듯
        new DealerHitView().view(dealer.doHit(true));
    }

    private void hitPerPlayer(Participant player) {
        HitView hitView = new HitView();

        while(!player.isFinished()) {
            Map<String, String> result = hitView.view(new HashMap<String, String>() {{
                put("name", player.getName());
            }});

            player.doHit("y".equals(result.get("result")));
        }
    }

    public void finish() {
        List<Participant> players = participants.getPlayers();
        List<Participant> allParticipants = participants.getAllParticipants();

        Participant winner = participants.getWinner();
        Participant dealer = participants.getDealer();


        for(Participant participant : players) {
            participant.finish(winner);
            dealer.betting(dealer.getBetMoney() - participant.getProfit());
        }

        for(Participant participant : allParticipants) {
            System.out.println(new MakeCardString().print(participant, true));
        }

        for(Participant participant : allParticipants) {
            System.out.println(participant.getName() + " : " + participant.getProfit());
        }

    }
}
