package ru.mail.polis.ads.EvilEnot.part1;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class part3 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack stack = new Stack();
        String[] split;
        String currentCommand = reader.readLine();

        while (!currentCommand.equals("exit")){
            split = currentCommand.split(" ");
            switch (split[0]){
                case "push":
                    stack.push(Integer.parseInt(split[1]));
                    System.out.println("ok");
                    break;
                case "pop":
                    if (stack.size() != 0)
                        System.out.println(stack.pop());
                    else System.out.println("error");
                    break;
                case "back":
                    if (stack.size() != 0)
                        System.out.println(stack.back());
                    else System.out.println("error");
                    break;
                case "size" :
                    System.out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    System.out.println("ok");
                    break;
            }
            currentCommand = reader.readLine();
        }

        System.out.println("bye");

    }

    private static class Stack {
        ArrayList<Integer> stackBucket = new ArrayList<>();

        private Stack(){
        }

        private void push(int n){
            stackBucket.add(n);
        }

        private int pop(){
            int number =  stackBucket.get(stackBucket.size() - 1);
            stackBucket.remove(stackBucket.size() - 1);
            return number;
        }

        private int back(){
            return stackBucket.get(stackBucket.size() - 1);
        }

        private int size(){
            return stackBucket.size();
        }

        private void clear(){
            stackBucket = new ArrayList<>();
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}