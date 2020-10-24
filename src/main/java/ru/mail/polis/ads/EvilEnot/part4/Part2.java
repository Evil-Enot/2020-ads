package ru.mail.polis.ads.EvilEnot.part4;

import java.io.*;
import java.util.StringTokenizer;

public class Part2 {
    // https://www.e-olymp.com/ru/problems/15

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        StringBuilder result = new StringBuilder();

        int[][] field = new int[100][100];

        for (int i = m - 1; i >= 0; i--){
            for (int j = 0; j < n; j++){
                field[i][j] = in.nextInt();
            }
        }

        for (int i = 1; i < m; i++){
            field[i][0] = field[i][0] + field[i - 1][0];
        }

        for (int j = 1; j < n; j++){
            field[0][j] = field[0][j] + field[0][j - 1];
        }

        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                field[i][j] = field[i][j] + Math.max(field[i - 1][j], field[i][j - 1]);
            }
        }

        int k = m - 1;
        int l = n - 1;

        while (k > 0 || l > 0){
            if (k > 0 && l > 0){
                if (field[k - 1][l] > field[k][l - 1]){
                    result.append("F");
                    k--;
                } else {
                    result.append("R");
                    l--;
                }
            } else if (k == 0){
                result.append("R");
                l--;
            } else if ((l == 0)) {
                result.append("F");
                k--;
            }
        }

        out.println(result.reverse().toString());
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
