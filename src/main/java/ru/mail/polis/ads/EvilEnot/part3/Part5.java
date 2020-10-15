package ru.mail.polis.ads.EvilEnot.part3;

import java.io.*;
import java.util.StringTokenizer;

public class Part5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] numbers = new int[n + 1];
        int left = 0;
        int right = 0;

        for (int i = 1; i <= n; i++){
            numbers[i] = in.nextInt();
        }

        right = numbers[n] - numbers[1];

        if (k == 2){
            out.println(right);
            return;
        }

        while (left != right){
            int middle = (left + right) / 2;
            int count = 1;
            int index = 1;

            for (int i = 2; i <= n; i++){
                if (numbers[i] - numbers[index] >= middle){
                    index = i;
                    count++;
                }
            }

            if (count >= k)
                left = middle + 1;
            else right = middle;
        }

        out.println(left - 1);

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
