package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11866_jjoyra {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        System.out.print("<");

        while(!queue.isEmpty()) {
            for(int i = 0; i < K - 1; i++) {
                queue.offer(queue.poll());
            }

            System.out.print(queue.poll());

            if(queue.isEmpty()) System.out.print(">");
            else System.out.print(", ");

        }
    }
}
