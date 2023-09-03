package nextstep.blackjack;

import java.util.Scanner;

public class Input {
    private static Input instance = null;

    private Scanner scanner;

    private Input() {
        scanner = new Scanner(System.in);
    }

    public static Input getInstance() {
        if(instance == null)
            instance = new Input();

        return instance;
    }

    public String input() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
