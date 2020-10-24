package ru.mail.polis.ads.EvilEnot.part4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Part5 {
    // https://www.e-olymp.com/ru/problems/4261

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] array = new int[size];

        for(int i = 0; i < size; ++i) {
            array[i] = in.nextInt();
        }

        out.println(countInv(array));
    }

    private static int countInv(int[] array) {
        if(array.length <= 1) {
            return 0;
        }

        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        return countInv(left) + countInv(right) + countInMerge(array, left, right);
    }

    private static int countInMerge(int[] array, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int count = 0;

        while (i < left.length || j < right.length) {
            if (i == left.length) {
                array[i + j] = right[j];
                j++;
            } else if (j == right.length) {
                array[i + j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                array[i + j] = left[i];
                i++;
            } else {
                array[i + j] = right[j];
                count += left.length - i;
                j++;
            }
        }

        return count;
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
