package nextstep.blackjack;

public class BlackJackApplication {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();

        gameManager.start();
        gameManager.betting();
        gameManager.firstDistribute();
        if(!gameManager.checkBlackJack())
            gameManager.running();

        gameManager.finish();
    }
}
