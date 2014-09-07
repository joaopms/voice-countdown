package net.joaopms.voicecountdown;

import javazoom.jl.player.Player;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static File audioFiles = new File("audioFiles");
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
            if (timeLeft == 0) {
                playAlert("Countdown finished", true);
                break;
            }

            playAlert(timeLeft + (timeLeft == 1 ? " minute " : " minutes ") + "left", false);
            timeLeft -= betweenTime;

            try {
                Thread.sleep(betweenTime * 60000);
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
    private static void playAlert(final String message, final boolean deleteDirectory) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(message);

                try {
                    audioFiles.mkdir();
                    String encodedMessage = URLEncoder.encode(message, "UTF-8");
                    URL url = new URL("http://translate.google.com/translate_tts?tl=en&q=" + encodedMessage);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
                    InputStream inputStream = httpURLConnection.getInputStream();
                    String filePath = audioFiles.getPath() + File.separator + encodedMessage + ".mp3";
                    FileOutputStream fileOutputStream = new FileOutputStream(filePath);

                    int byteToWrite = 0;
                    while ((byteToWrite = inputStream.read()) != -1) {
                        fileOutputStream.write(byteToWrite);
                    }

                    inputStream.close();
                    fileOutputStream.close();

                    File file = new File(filePath);
                    FileInputStream fileInputStream = new FileInputStream(file);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                    new Player(bufferedInputStream).play();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (deleteDirectory) {
                    String filePath = audioFiles.getPath() + File.separator;
                    String[] folderList = audioFiles.list();
                    int folderSize = folderList.length - 1;

                    for (int i = folderSize; i >= 0; i--) {
                        File temporaryFile = new File(filePath + folderList[i]);
                        temporaryFile.delete();
                    }

                    audioFiles.delete();
                }
            }
        }).start();
    }
}