package ru.qimix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    protected static Integer length3;
    protected static Integer length4;
    protected static Integer length5;

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        String nick = "aabbccc";
        String revertNick = new StringBuilder(nick).reverse().toString();
        if(revertNick.equals(nick)){
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        String[] buffer = nick.split("");
        for(String i : buffer){
            if(!buffer[0].equals(i)){
                System.out.println("false");
                break;
            }
        }

        List<String> list = Arrays.asList(nick.split("")).stream().sorted().collect(Collectors.toList());
        String result = list.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(""));
        if(result.equals(nick)) {
            System.out.println("true");
        }

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    class CheckPalindrom extends Thread {
        String nick;

        public CheckPalindrom(String nick) {
            this.nick = nick;
        }
        String revertNick = new StringBuilder(nick).reverse().toString();


    }

}

