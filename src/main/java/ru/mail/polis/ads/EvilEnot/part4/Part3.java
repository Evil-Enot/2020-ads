package ru.mail.polis.ads.EvilEnot.part4;

import java.io.*;
import java.util.StringTokenizer;

public class Part3 {
    // https://www.e-olymp.com/ru/problems/1618

    private static void solve(final FastScanner in, final PrintWriter out) {
        int first = in.nextInt();
        int[] firstSequence = new int[first];

        for (int i = 0; i < first; i++){
            firstSequence[i] = in.nextInt();
        }

        int second = in.nextInt();
        int[] secondSequence = new int[second];

        for (int i = 0; i < second; i++){
            secondSequence[i] = in.nextInt();
        }

        int[][] result = new int[first + 1][second + 1];

        for (int i = 1; i <= first; i++){
            for (int j = 1; j <= second; j++){
                if (firstSequence[i - 1] == secondSequence[j - 1]){
                    result[i][j] = 1 + result[i - 1][j - 1];
                } else {
                    result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]);
                }
            }
        }

        out.println(result[first][second]);
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
