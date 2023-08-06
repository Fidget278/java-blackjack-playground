package nextstep.blackjack.card;

import java.util.Arrays;

public enum CardNum {
    ACE("A", 1, 1),
    TWO("2", 2, 2),
    THREE("3", 3, 3),
    FOUR("4", 4, 4),
    FIVE("5", 5, 5),
    SIX("6", 6, 6),
    SEVEN("7", 7, 7),
    EIGHT("8", 8, 8),
    NINE("8", 9, 9),
    TEN("10", 10, 10),
    JACK("J", 11, 10),
    QUEEN("Q", 12, 10),
    KING("K", 13, 10);

    private String name;
    private int number;
    private int score;

    CardNum(String name, int number, int score) {
        this.name = name;
        this.number = number;
        this.score = score;
    }

    public static CardNum getCardNumByNum(int num) {
        return Arrays.stream(CardNum.values())
                .filter(cardNum -> cardNum.number == num)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("wrong card num"));
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean isAce() {
        return number == 1;
    }
}
