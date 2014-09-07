package net.joaopms.voicecountdown;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int totalTime = 0;
    private static int betweenTime = 0;

    public static void main(String[] args) {
        System.out.println("VoiceCountdown started!");
        promptTotalTime();
        promptBetweenTime();
        promptStart();
    }

    private static void promptTotalTime() {
        System.out.print("How much time do you want to count (in minutes)? ");
        Scanner scanner = new Scanner(System.in);
        int response = 0;

        try {
            response = scanner.nextInt();
        } catch (InputMismatchException e) {
            throwError("The input must be an integer!");
            promptTotalTime();
            return;
        }

        if (response < 1) {
            throwError("The input must be greater than 0!");
            promptTotalTime();
            return;
        }

        totalTime = response;
    }

    private static void promptBetweenTime() {
        System.out.print("How much time do you want between warnings (in minutes)? ");
        Scanner scanner = new Scanner(System.in);
        int response = 0;

        try {
            response = scanner.nextInt();
        } catch (InputMismatchException e) {
            throwError("The input must be an integer!");
            promptBetweenTime();
            return;
        }

        if (response < 1) {
            throwError("The input must be greater than 0!");
            promptBetweenTime();
            return;
        }

        if (response > totalTime) {
            throwError("The input must be less that the total time (" + totalTime + ")!");
            promptBetweenTime();
            return;
        }

        if (totalTime % response != 0) {
            throwError("The time between warning should be a multiple of the total time(" + totalTime + ")!");
            promptBetweenTime();
            return;
        }

        betweenTime = response;
    }

    private static void promptStart() {
        System.out.print("Press ENTER when you are ready to start. ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine())
            startLoop();
    }

    private static void startLoop() {
        int timeLeft = totalTime;

        System.out.println("Countdown started");
        while (true) {
            playAlert(timeLeft + (timeLeft == 1 ? " minute " : " minutes ") + "left");
            timeLeft -= betweenTime;

            if (timeLeft == 0) {
                playAlert("Countdown finished");
                break;
            }

            try {
                Thread.sleep(betweenTime * /*60000*/ 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void throwError(String message) {
        System.err.println(message);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // TODO Finish this and remove the exceptions from the method signature
    private static void playAlert(String message) {
        System.out.println(message);
        /*File file = new File("D:\\Downloads\\translate_tts.mp3");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        new Player(bufferedInputStream).play();*/
    }
}