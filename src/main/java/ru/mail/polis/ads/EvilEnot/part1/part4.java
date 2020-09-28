package ru.mail.polis.ads.EvilEnot.part1;

import java.io.*;
import java.util.StringTokenizer;

public class part4 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Queue queue = new Queue();

        String currentCommand = reader.readLine();
        String[] split;

        while (!currentCommand.equals("exit")){
            split = currentCommand.split(" ");

            switch (split[0]){
                case "push":
                    queue.push(Integer.parseInt(split[1]));
                    System.out.println("ok");
                    break;
                case "pop":
                    System.out.println(queue.pop());
                    break;
                case "front":
                    System.out.println(queue.front());
                    break;
                case  "size":
                    System.out.println(queue.size);
                    break;
                case "clear":
                    queue.clear();
                    System.out.println("ok");
                    break;
            }

            currentCommand = reader.readLine();
        }

        System.out.println("bye");

    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static class Queue {
        Node head;
        Node tail;
        int size;

        Queue(){
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        private void push(int n){
            Node node = new Node(n);
            if (size == 0){
                this.head = node;
                size++;
                return;
            }

            if (size == 1){
                this.tail = node;
                this.head.next = tail;
                size++;
                return;
            }

            this.tail.next = node;
            this.tail = node;
            size++;
        }


        private int pop(){
            int firstElement = head.data;
            head = head.next;
            size--;
            return firstElement;
        }

        private int front(){
            return head.data;
        }

        private void clear(){
            this.head = null;
            this.tail = null;
            size = 0;
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