package nextstep.blackjack.view;

import nextstep.blackjack.Input;

import java.util.HashMap;
import java.util.Map;

public class HitView {

    public Map<String, String> view(Map<String, String> map) {

        String answer;
        do {
            System.out.println(map.get("name") + "한장의 카드를 더 받으시겠습니까?(예는 y, 아니오는 n");
            answer = Input.getInstance().input();

        } while (answer == null || (!answer.equals("y") && !answer.equals("n")));

        String finalResult = answer;
        return new HashMap<String, String>() {{
            put("result", finalResult);
        }};
    }
}
