package net.joaopms.voicecountdown;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StaticMode {
    private int totalTime = 0;
    private int betweenTime = 0;

    public StaticMode() {
        this.promptTotalTime();
        this.promptBetweenTime();
    }

    private void promptTotalTime() {
        System.out.print("How much time do you want to count (in minutes)? ");
        Scanner scanner = new Scanner(System.in);
        int response = 0;

        try {
            response = scanner.nextInt();
        } catch (InputMismatchException e) {
            this.throwError("The input must be an integer!");
            this.promptTotalTime();
            return;
        }

        if (response < 1) {
            this.throwError("The input must be greater than 0!");
            this.promptTotalTime();
            return;
        }

        this.totalTime = response;
    }

    private void promptBetweenTime() {
        System.out.print("How much time do you want between warnings (in minutes)? ");
        Scanner scanner = new Scanner(System.in);
        int response = 0;

        try {
            response = scanner.nextInt();
        } catch (InputMismatchException e) {
            this.throwError("The input must be an integer!");
            this.promptBetweenTime();
            return;
        }

        if (response < 1) {
            this.throwError("The input must be greater than 0!");
            this.promptBetweenTime();
            return;
        }

        if (response > totalTime) {
            this.throwError("The input must be less that the total time (" + totalTime + ")!");
            this.promptBetweenTime();
            return;
        }

        if (totalTime % response != 0) {
            this.throwError("The time between warning should be a multiple of the total time(" + totalTime + ")!");
            this.promptBetweenTime();
            return;
        }

        this.betweenTime = response;
    }

    private void throwError(String message) {
        System.err.println(message);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
