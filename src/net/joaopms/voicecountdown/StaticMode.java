package net.joaopms.voicecountdown;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StaticMode {
    private int totalTime = 0;
    private int betweenTime = 0;

    public StaticMode() {
        this.promptTotalTime();
    }

    private void promptTotalTime() {
        System.out.print("How much time do you want to count (in minutes)? ");
        Scanner scanner = new Scanner(System.in);
        int response = 0;

        try {
            response = scanner.nextInt();
        } catch (InputMismatchException e) {
            this.throwError("The input must be an integer!");
            promptTotalTime();
        }

        if (response < 0) {
            this.throwError("The input must be greater than 0!");
            promptTotalTime();
        }

        this.totalTime = response;
    }

    private void throwError(String message) {
        System.err.println(message);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
