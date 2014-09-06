package net.joaopms.voicecountdown;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // TODO Add an option to switch between static and dynamic mode
        staticMode();
    }

    private static void staticMode() {
        Prompt timeToCount = new Prompt();
        timeToCount.setQuestion("Hello? Is this working?");
        timeToCount.setErrorMessage("This should be an error message.");
        timeToCount.addValidResponse("0");
        timeToCount.addValidResponse("1");
        System.out.println(timeToCount.prompt());
    }

    // TODO Finish this and remove the exceptions from the method signature
    private static void playAlert() throws JavaLayerException, FileNotFoundException {
        File file = new File("D:\\Downloads\\translate_tts.mp3");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        new Player(bufferedInputStream).play();
    }
}