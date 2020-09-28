package ru.mail.polis.ads.EvilEnot.part1;

import java.io.*;
import java.util.StringTokenizer;

public class part2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = 0;
        String line = in.next();

        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) ==  40){
                count++;
            } else if (line.charAt(i) == 41){
                count--;
                if (count < 0){
                    break;
                }
            }
        }

        if (count == 0){
            out.write("YES");
        } else {
            out.write("NO");
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

    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}