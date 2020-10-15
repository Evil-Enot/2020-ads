package ru.mail.polis.ads.EvilEnot.part3;

import java.io.*;
import java.util.StringTokenizer;

public class Part3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap max = new Heap(500001, false);
        Heap min = new Heap(500001, true);

        int middle = -1;
        int number;

        max.insert(Integer.MIN_VALUE);
        min.insert(Integer.MAX_VALUE);

        while (true) {
            try {
                number = Integer.parseInt(in.next());
            } catch (Exception e) {
                break;
            }

            if (middle == -1) {
                if (number < max.peek()) {
                    middle = max.extract();
                    max.insert(number);
                } else
                    if (number > min.peek()) {
                    middle = min.extract();
                    min.insert(number);
                    } else {
                        middle = number;
                    }
            } else {
                if (number < middle) {
                    min.insert(middle);
                    max.insert(number);
                    middle = -1;
                } else {
                    max.insert(middle);
                    min.insert(number);
                    middle = -1;
                }
            }
            if (middle == -1)
                out.println(max.peek() + (min.peek() - max.peek()) / 2);
            else out.println(middle);
        }
    }

    private static class Heap {
        private int[] data;
        private boolean isMin;
        private int size;

        Heap(int max, boolean min) {
            data = new int[max];
            size = 0;
            this.isMin = min;
        }

        void insert(int x) {
            size++;
            data[size - 1] = x;
            swim(size - 1);
        }

        int peek() {
            return data[0];
        }

        int extract() {
            int max = data[0];
            data[0] = data[size - 1];
            size--;
            sink(0);
            return max;
        }

        private void swim(int i) {
            while (isMin && data[i] < data[(i - 1) / 2] || !isMin && data[i] > data[(i - 1) / 2]) {
                int t = data[i];
                data[i] = data[(i - 1) / 2];
                data[(i - 1) / 2] = t;

                i = (i - 1) / 2;
            }
        }

        private void sink(int n) {
            int left;
            int right;
            int j;
            while (2 * n + 1 < size) {
                left = 2 * n + 1;
                right = 2 * n + 2;
                j = left;
                if (right <= size && isMin && data[right] < data[left] || (right > size || !isMin)
                        && data[right] > data[left]) {
                    j = right;
                }
                if (isMin && data[n] <= data[j] || !isMin && data[n] >= data[j]) {
                    break;
                }

                int t = data[n];
                data[n] = data[j];
                data[j] = t;

                n = j;
            }
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
