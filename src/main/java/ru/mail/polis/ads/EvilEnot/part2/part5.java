package ru.mail.polis.ads.EvilEnot.part2;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class part5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Robot[] robots = new Robot[n];

        for (int i = 0; i < robots.length; i++){
            robots[i] = new Robot(in.nextInt(), in.nextInt());
        }

        mergeSort(robots, 0, robots.length );

        for (Robot robot : robots) {
            out.println(robot.toString());
        }

    }

    private static class Robot{
        int firstNumber;
        int secondNumber;

        Robot(int firstNumber, int secondNumber){
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
        }

        public String toString(){
            return firstNumber +" " + secondNumber;
        }

        int getFirstNumber(){
            return firstNumber;
        }
    }

    private static void mergeSort(Robot[] robots, int left, int right){
        if (right - left < 2)
            return;

        int center = (right + left) / 2;

        mergeSort(robots, left, center);
        mergeSort(robots, center, right);

        merge(robots, left, center, right);
    }

    private static void merge(Robot[] robots, int left, int center, int right){
        int iLeft = 0;
        int iRight = 0;

        Robot[] leftPart = Arrays.copyOfRange(robots, left, center);
        Robot[] rightPart = Arrays.copyOfRange(robots, center, right);

        for (int i = left; i < right; i++){
            if (iLeft < leftPart.length && (iRight == rightPart.length
                    || leftPart[iLeft].getFirstNumber() <= rightPart[iRight].getFirstNumber())){
                robots[i] = leftPart[iLeft++];
            } else robots[i] = rightPart[iRight++];
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
