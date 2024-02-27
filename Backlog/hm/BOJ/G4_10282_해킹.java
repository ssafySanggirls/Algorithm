import java.io.*;
import java.util.*;

public class G4_10282_해킹 {

    static int T, N, D, C;
    static ArrayList<Node> computer[];
    static int[] dist = new int[10001];
    static final int INF = Integer.MAX_VALUE;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for(int tc=0;tc<T;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            computer = new ArrayList[N+1];
            for(int i=0;i<=N;i++) computer[i] = new ArrayList<>();

            for(int i=0;i<D;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                computer[b].add(new Node(a, s));
            }

            func();

            int cnt=0; // 총 감염되는 컴퓨터 수
            int sec=0; // 마지막 컴퓨터가 걸리는 시간
            for(int i : dist) {
                if(i != INF) {
                    cnt++;
                    sec = Math.max(sec, i);
                }
            }
            sb.append(cnt).append(" ").append(sec).append("\n");
        }
        System.out.println(sb);
    }


    private static void func() {
        Arrays.fill(dist, INF);
        dist[C] = 0;

        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(C, 0));

        while(!q.isEmpty()) {
            Node node = q.poll();
            int num = node.num;
            int sec = node.sec;

            for(Node n : computer[num]) {
                if(dist[n.num] > sec + n.sec) {
                    dist[n.num] = sec + n.sec;
                    q.add(new Node(n.num, sec + n.sec));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int num, sec;
        Node(int num, int sec){
            this.num = num;
            this.sec = sec;
        }

        @Override
        public int compareTo(Node o) {
            return this.sec-o.sec;
        }
    }
}
