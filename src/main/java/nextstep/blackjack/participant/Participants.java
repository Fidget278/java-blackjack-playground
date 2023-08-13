package nextstep.blackjack.participant;

import java.util.ArrayList;
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

    public Participant getDealer() {
        return dealer;
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
}
