package ru.mail.polis.ads.EvilEnot.part1;

import java.io.*;
        import java.util.ArrayList;
        import java.util.StringTokenizer;

public class part5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();

        for (int i = 0; i < count; i++){
            ArrayList<Node> arrayList = new ArrayList<>();
            StringBuilder result = new StringBuilder();

            String line = in.next();
            char[] lineChars = line.toCharArray();

            for (char lineChar : lineChars) {
                Node node = new Node(lineChar);

                if (Character.isUpperCase(lineChar)) {
                    node.left = arrayList.remove(0);
                    node.right = arrayList.remove(0);
                    arrayList.add(0, node);
                } else arrayList.add(0, node);

            }

            while (arrayList.size() != 0){
                Node node = arrayList.remove(0);
                result.insert(0, node.data);

                if (node.right != null){
                    arrayList.add(arrayList.size(), node.right);
                    arrayList.add(arrayList.size(), node.left);
                }
            }

            out.println(result.toString());
        }
    }

    private static class Node{
        Node left;
        Node right;
        char data;

        private Node(char c){
            this.data = c;
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
