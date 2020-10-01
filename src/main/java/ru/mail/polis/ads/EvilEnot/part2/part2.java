package ru.mail.polis.ads.EvilEnot.part2;

import java.io.*;
import java.util.StringTokenizer;

public class part2 {
    private static int[] numbers;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        numbers = new int[n];

        for (int i = 0; i < n; i++){
            numbers[i] = in.nextInt();
        }

        for (int i = 0; i < numbers.length - 1; ++i) {
            int minIndex = i;

            for (int j = i + 1; j < numbers.length; ++j) {
                if (compare(j, minIndex)) {
                    minIndex = j;
                }
            }

            int tmp = numbers[i];
            numbers[i] = numbers[minIndex];
            numbers[minIndex] = tmp;
            numbers[minIndex] = tmp;
        }

        for (int number : numbers) {
            out.write(number + " ");
        }

    }

    private static boolean compare(int a, int b){
        if (numbers[a] % 10 == numbers[b] % 10) {
            return numbers[a] < numbers[b];
        } else {
            return numbers[a] % 10 < numbers[b] % 10;
        }
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
                    tokenizer = new StringTokenizer(reader.readLine());
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
