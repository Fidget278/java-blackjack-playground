package nextstep.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private List<Participant> participants;

    public Participants(String participantNames) {

        participants = new ArrayList<>();

        // 플레이어 생성
        String[] names = participantNames.replaceAll("\\s+", "").split(",");
        Arrays.stream(names).forEach(name -> participants.add(new Participant(name)));

        // 딜러 생성 (파라미터 에바인듯)
        participants.add(new Dealer());
    }

    public List<Participant> getPlayers() {
        return participants.stream()
                .filter(Participant::isPlayer)
                .collect(Collectors.toList());
    }

    public Participant getDealer() {
        return participants.stream()
                .filter(Participant::isDealer)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No Dealer."));
    }

    public List<Participant> getAllParticipants() {
        return participants;
    }

    public Participant getWinner() {
        if(getDealer().getCards().isBlackJack())
            return getDealer();

        return participants.stream()
                .filter(p -> !p.getCards().isBust())
                .min(Comparator.comparingInt(p -> p.getCards().calcGap()))
                .orElseThrow(() -> new RuntimeException("승자 뽑기 잘못됨"));
    }

    public void setDealerBetMoney() {
        getDealer().betting(getPlayers().stream()
                .mapToDouble(p -> p.getBetMoney())
                .sum());
    }
}
