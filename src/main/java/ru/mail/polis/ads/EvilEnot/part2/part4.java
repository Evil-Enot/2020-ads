package ru.mail.polis.ads.EvilEnot.part2;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class part4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        String[] lines = in.next().trim().split(" ");
        BigInteger[] numbers = new BigInteger[lines.length];

        for (int i = 0; i < lines.length; i++){
            String line = lines[i];
            numbers[i] = new BigInteger(line);
        }

        Arrays.sort(numbers);

        out.println(numbers[numbers.length - n]);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine(), "\\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
