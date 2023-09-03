package nextstep.blackjack.view;

public class DealerHitView {
    public void view(boolean isHit) {
        if(isHit) {
            System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
            return;
        }

        System.out.println("딜러는 카드를 더 받지 않았습니다.");

    }
}
