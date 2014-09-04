package net.joaopms.voicecountdown;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(promptUser("This is a test."));
    }

    public static String promptUser(String question) {
        System.out.print(question + " ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // TODO Finish this and remove the exceptions from the method signature
    public static void playAlert() throws JavaLayerException, FileNotFoundException {
        File file = new File("D:\\Downloads\\translate_tts.mp3");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        new Player(bufferedInputStream).play();
    }
}