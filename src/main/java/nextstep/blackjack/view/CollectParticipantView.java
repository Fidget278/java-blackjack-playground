package nextstep.blackjack.view;

import nextstep.blackjack.Input;

import java.util.HashMap;
import java.util.Map;

public class CollectParticipantView {

    public Map<String, String> view() {
        String names;

        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            names = Input.getInstance().input();
        } while (names == null || names.equals(""));

        String finalNames = names;
        return new HashMap<String, String>() {{
            put("names", finalNames);
        }};
    }
}
