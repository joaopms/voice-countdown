package net.joaopms.voicecountdown;

import java.util.ArrayList;
import java.util.Scanner;

public class Prompt {
    private String question;
    private ArrayList<String> validResponses = new ArrayList<String>();
    private String errorMessage;

    public void setQuestion(String question) {
        this.question = question;
    }

    public void addValidResponse(String validResponse) {
        this.validResponses.add(validResponse.toLowerCase());
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getQuestion() {
        if (this.question == null)
            throwError("The question message is null!");

        return this.question;
    }

    public ArrayList<String> getValidResponses() {
        if (this.validResponses.isEmpty())
            throwError("The valid responses array is empty!");

        return this.validResponses;
    }

    public String getErrorMessage() {
        if (this.errorMessage == null)
            throwError("The error message is null!");

        return this.errorMessage;
    }

    public String prompt() {
        System.out.print(getQuestion() + " ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().toLowerCase();

        if (getValidResponses().contains(response)) {
            return response;
        } else {
            System.err.println(getErrorMessage());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            prompt();
        }

        return response;
    }

    private void throwError(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(1);
    }
}
