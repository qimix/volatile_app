package ru.qimix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    protected static AtomicInteger length3 = new AtomicInteger(0);
    protected static AtomicInteger length4 = new AtomicInteger(0);
    protected static AtomicInteger length5 = new AtomicInteger(0);

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        for(String i : texts){
            checkNickName(i);
        }
        System.out.println("Красивых слов с длиной 3: " + length3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + length4 + " шт");
        System.out.println("Красивых слов с длиной 5: " + length5 + " шт");

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void checkNickName(String nickName) {
        Runnable runnable = () -> {
            String revertNick = new StringBuilder(nickName).reverse().toString();
            if(revertNick.equals(nickName)){
                switch (nickName.length()) {
                    case 3: length3.incrementAndGet();
                    case 4: length4.incrementAndGet();
                    case 5: length5.incrementAndGet();
                }
            }
        };
        new Thread(runnable).start();

        Runnable runnable1 = () -> {
            String[] buffer = nickName.split("");
            for(String i : buffer){
                if(!buffer[0].equals(i)){
                    return;
                }
            }
            switch (nickName.length()) {
                case 3: length3.incrementAndGet();
                case 4: length4.incrementAndGet();
                case 5: length5.incrementAndGet();
            }
        };
        new Thread(runnable1).start();

        Runnable runnable2 = () -> {
            List<String> list = Arrays.asList(nickName.split("")).stream().sorted().collect(Collectors.toList());
            String result = list.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(""));
            if(result.equals(nickName)) {
                switch (nickName.length()) {
                    case 3: length3.incrementAndGet();
                    case 4: length4.incrementAndGet();
                    case 5: length5.incrementAndGet();
                }
            }
        };
        new Thread(runnable2).start();
    }
}

