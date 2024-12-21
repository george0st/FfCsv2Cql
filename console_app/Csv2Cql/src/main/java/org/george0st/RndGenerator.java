package org.george0st;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public class RndGenerator {

    private static String allCandidates = "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "1234567890"+
            " ()_-,.";
    private static String stringCandidates = "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "1234567890";
    private static String numberCandidates = "1234567890";

    private SecureRandom rnd=null;

    public RndGenerator() throws InterruptedException {

        // calc based on CPU speed
        long startTime = System.nanoTime();
        Thread.sleep(3);
        long calcClock = System. nanoTime() - startTime;

        // define INIT seed
        byte[] init = String.format("%s,%d,%s", LocalDateTime.now().toString(), calcClock, UUID.randomUUID().toString())
                .getBytes();

        try {
            this.rnd = SecureRandom.getInstance("SHA1PRNG");
        }
        catch (NoSuchAlgorithmException ex) {
            this.rnd = new SecureRandom();
        }
        this.rnd.setSeed(init);
    }

    public String getStringSequence(int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i ++)
            sb.append(stringCandidates.charAt(this.rnd.nextInt(stringCandidates.length())));
        return sb.toString();
    }

    public String getNumberSequence(int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i ++)
            sb.append(numberCandidates.charAt(this.rnd.nextInt(numberCandidates.length())));
        return sb.toString();
    }

    public String getAllSequence(int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i ++)
            sb.append(allCandidates.charAt(this.rnd.nextInt(allCandidates.length())));
        return sb.toString();
    }
}