package ru.qimix;

import java.util.Arrays;
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

        checkNickName(texts);

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

    public static void checkNickName(String[] texts) {
        new Thread(() -> {
            for (String i : texts) {
                String revertNick = new StringBuilder(i).reverse().toString();
                if (revertNick.equals(i)) {
                    switch (i.length()) {
                        case 3:
                            length3.incrementAndGet();
                        case 4:
                            length4.incrementAndGet();
                        case 5:
                            length5.incrementAndGet();
                        default:
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (String i : texts) {
                String[] array = i.split("");
                for (String s : array) {
                    if (!array[0].equals(s)) {
                        return;
                    }
                }
                switch (i.length()) {
                    case 3:
                        length3.incrementAndGet();
                    case 4:
                        length4.incrementAndGet();
                    case 5:
                        length5.incrementAndGet();
                    default:
                }
            }
        }).start();

        new Thread(() -> {
            for (String i : texts) {
                List<String> list = Arrays.asList(i.split("")).stream().sorted().collect(Collectors.toList());
                String result = list.stream()
                        .map(n -> String.valueOf(n))
                        .collect(Collectors.joining(""));
                if (result.equals(i)) {
                    switch (i.length()) {
                        case 3:
                            length3.incrementAndGet();
                        case 4:
                            length4.incrementAndGet();
                        case 5:
                            length5.incrementAndGet();
                        default:
                    }
                }
            }
        }).start();
    }
}

