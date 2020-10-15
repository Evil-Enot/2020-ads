package ru.mail.polis.ads.EvilEnot.part3;

import java.io.*;
import java.util.StringTokenizer;

public class Part1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] numbers = new long[n + 1];
        boolean b = false;

        for (int i = 0; i < n; i++){
            numbers[i] = in.nextLong();
        }

        for (int i = 1; i < n; i++){
            if (numbers[(i - 1) / 2] > numbers[i]){
                b = true;
                break;
            }
        }
        if (b) out.write("NO");
        else out.write("YES");
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

        long nextLong(){
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

