package ru.mail.polis.ads.EvilEnot.part3;

import java.io.*;
import java.util.StringTokenizer;

public class Part4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++){
            numbers[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++){
            int current = in.nextInt();
            if (binarySearch(numbers, n, current))
                out.println("YES");
            else out.println("NO");
        }
    }

    private static boolean binarySearch(int[] a, int index, int key){
        int x = 0;
        int y = index - 1;

        while (x <= y){
            int middle = (x + y) / 2;
            int middleValue = a[middle];

            if (middleValue < key)
                x = middle + 1;
            else{
                if (middleValue <= key)
                    return true;
                y = middle - 1;
            }
        }

        return false;
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
