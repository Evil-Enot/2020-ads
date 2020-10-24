package ru.mail.polis.ads.EvilEnot.part4;

import java.io.*;

public class Part1 {
// https://www.e-olymp.com/ru/problems/1087

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        char[] sequence;
        sequence = in.reader.readLine().toCharArray();

        if (sequence.length == 0) {
            return;
        }

        int n = sequence.length;

        int[][] count = new int[n][n];
        char[][][] correctSequence = new char[n][n][];

        for (int i = 0; i < n; i++) {
            count[i][i] = 1;
            correctSequence[i][i] = getCorrectSequence(sequence[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int minSum = count[i][i] + count[i + 1][j];
                int current = i;

                for (int k = i + 1; k < j; k++) {
                    int tempSum = count[i][k] + count[k + 1][j];
                    if (tempSum < minSum) {
                        minSum = tempSum;
                        current = k;
                    }
                }

                if ((sequence[i] == '(' && sequence[j] == ')' || sequence[i] == '[' && sequence[j] == ']') && count[i + 1][j - 1] < minSum) {
                    count[i][j] = count[i + 1][j - 1];
                    if (correctSequence[i + 1][j - 1] == null) {
                        correctSequence[i][j] = new char[]{sequence[i], sequence[j]};
                    } else {
                        char[] left = mergeArrays(new char[]{sequence[i]}, correctSequence[i + 1][j - 1]);
                        correctSequence[i][j] = mergeArrays(left, new char[]{sequence[j]});
                    }
                } else {
                    count[i][j] = minSum;
                    correctSequence[i][j] = mergeArrays(correctSequence[i][current], correctSequence[current + 1][j]);
                }
            }
        }
        out.println(correctSequence[0][n - 1]);
    }

    private static char[] mergeArrays(char[] x, char[] y) {
        char[] res = new char[x.length + y.length];
        System.arraycopy(x, 0, res, 0, x.length);
        System.arraycopy(y, 0, res, x.length, y.length);
        return res;
    }

    private static char[] getCorrectSequence(char x) {
        return x != '(' && x != ')' ? new char[]{'[', ']'} : new char[]{'(', ')'};
    }

    private static class FastScanner {
        private final BufferedReader reader;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

    }

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}