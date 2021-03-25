package ru.Geekbrains.client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatArchiver {
    public static void main(String[] args) {
        System.out.println(displayLastMessages());
    }

    public synchronized static void archiveMessage(String nick, String message){
        if(message.charAt(0) != '/') {
            try (FileWriter writer = new FileWriter("src/main/resources/ChatHistory.txt", true)) {
                writer.write(nick + ": " + message + "\n");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public synchronized static String displayLastMessages(){
        int countDisplayLastMsg = 100;
        ArrayList <String> archive = new ArrayList<>();
        StringBuilder resultLine = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/ChatHistory.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                archive.add(line + "\n");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        int index = 0;
        if (archive.size() > countDisplayLastMsg){
            index = archive.size() - countDisplayLastMsg;
        }

        for (int i = index; i < archive.size(); i++) {
            resultLine.append(archive.get(i)).append(System.lineSeparator());
        }

        return resultLine.toString();
    }
}
