package nextstep.blackjack;

import nextstep.blackjack.participant.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {
    @Test
    public void 플레이어_생성_성공() {
        String name = "phobi";

        Player player = new Player(name);
        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    public void 플레이어_생성_실패() {
        assertThrows(IllegalArgumentException.class, () -> new Player(""));
        assertThrows(IllegalArgumentException.class, () -> new Player(null));
    }
}
