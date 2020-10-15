package ru.mail.polis.ads.EvilEnot.part3;

import java.io.*;
import java.util.StringTokenizer;

public class Part2 {
    private static int[] heap;
    private static int size = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        heap = new int[n];

        for (int i = 0 ; i < n; i++){
            if (in.nextInt() == 0) {
                insert(in.nextInt());
            } else {
                if (heap.length > 0) {
                    out.println(extract());
                }
            }
        }
    }

    private static void insert(int v){
        heap[size++] = v;
        int k = size - 1;

        while (k > 0 && heap[k] > heap[(k - 1) / 2]){
            int temp = heap[k];
            int par = (k - 1) / 2;
            heap[k] = heap[par];
            heap[par] = temp;
            k = par;
        }
    }

    private static int extract(){
        int max = heap[0];
        int k = 0;
        heap[0] = heap[--size];

        while (2 * k + 1 < size){
            int child = 2 * k + 1;
            int temp = heap[k];

            if (child < size && heap[child] < heap[child + 1])
                child++;
            if (heap[k] >= heap[child])
                break;

            heap[k] = heap[child];
            heap[child] = temp;
            k = child;
        }
        return max;
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
