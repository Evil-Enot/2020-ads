package ru.mail.polis.ads.EvilEnot.part4;

import java.io.*;
import java.util.StringTokenizer;

public class Part4 {
    // https://www.e-olymp.com/ru/problems/262

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stairs = new int[n + 2];
        int[] cost = new int[n + 2];

        for (int i = 1; i < n + 1; i++){
            stairs[i] = in.nextInt();
        }

        int k = in.nextInt();

        for (int i = 1; i < n + 2; i++){
            int max = Integer.MIN_VALUE;
            for (int j = i < k ? 0 : i - k; j < i; j++){
                if (cost[j] > max)
                    max = cost[j];
            }
            cost[i] = stairs[i] + max;
        }

        out.println(cost[n + 1]);
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
