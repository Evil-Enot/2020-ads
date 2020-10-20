package ru.mail.polis.ads.EvilEnot.part5;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class Part3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        long width = Long.parseLong(in.next());
        long height = Long.parseLong(in.next());
        long n = Long.parseLong(in.next());
        long left = max(width, height);
        long right = n * left;
        long count;
        long middle;

        while (left < right){
            middle = (left + right) / 2;
            count = (middle / width) * (middle / height);

            if (n <= count)
                right = middle;
            else  left = middle + 1;
        }

        out.println(left);
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
