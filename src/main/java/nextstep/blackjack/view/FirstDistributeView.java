package nextstep.blackjack.view;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.gameEnum.GameState;
import nextstep.blackjack.gameSystem.GameManager;
import nextstep.blackjack.participant.Participant;
import nextstep.blackjack.participant.Participants;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FirstDistributeView implements View {

    @Override
    public boolean isRunnable(GameState gameState) {
        return gameState == GameState.RUN;
    }

    @Override
    public void view(Participants participants) {
        PrintCards printCards = new PrintCards();

        StringBuilder ui_string = new StringBuilder();

        for(Participant participant : participants.getAllParticipants()) {
            participant.receiveCard(GameManager.getInstance().distribute());
            participant.receiveCard(GameManager.getInstance().distribute());
            ui_string.append(participant.getName()).append(",");
        }

        ui_string.deleteCharAt(ui_string.lastIndexOf(","));
        ui_string.append("에게 2장의 카드를 나누었습니다.");
        System.out.println(ui_string);

        for(Participant participant : participants.getAllParticipants()) {
            System.out.print(participant.getName() + " : ");
            printCards.print(getDistributeCards(participant));
            System.out.println();
        }


        checkBlackJack(participants);
    }

    private void checkBlackJack(Participants participants) {
        if(participants.getDealer().isBlackJack())
            GameManager.getInstance().changeGameState(GameState.FINISH);
    }

    private List<Card> getDistributeCards(Participant participant) {

        List<Card> cards = participant.getOpenableCards();

        if(participant.isDealer())
            cards = cards.stream()
                .skip(1) // 첫 번째 카드를 제외하고 스트림 생성
                .collect(Collectors.collectingAndThen(
                        Collectors.toList()
                        , Collections::unmodifiableList));

        return cards;
    }

}
