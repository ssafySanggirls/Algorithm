package _0412_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11779 {
    static int N, M, cnt;
    static class Vertex {
        int no;
        int weight;

        public Vertex(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }

    static List<Vertex>[] list;
    static int[] distance, before;
    static Stack<Integer> route;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        route = new Stack<>();

        for(int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[s].add(new Vertex(e, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        getRoute(start, end);

        StringBuilder sb = new StringBuilder();
        sb.append(distance[end] + "\n");
        sb.append(cnt + "\n");

        for(int i = 0; i < cnt; i++)
            sb.append(route.pop() + " ");

        System.out.println(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.offer(new Vertex(start, 0));

        distance = new int[N + 1];
        before = new int[N + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(before, -1);

        distance[start] = 0;
        before[start] = 0;

        while(!pq.isEmpty()) {
            Vertex now = pq.poll();
            if(now.weight > distance[now.no]) continue;

            for(Vertex v : list[now.no]) {
                if(distance[v.no] > distance[now.no] + v.weight) {
                    distance[v.no] = distance[now.no] + v.weight;
                    before[v.no] = now.no;
                    pq.add(new Vertex(v.no, distance[v.no]));
                }
            }
        }
    }

    static void getRoute(int start, int end) {
        int cur = end;
        route.push(cur);
        cnt = 1;
        while(true) {
            if(cur == start) break;
            route.push(before[cur]);
            cur = before[cur];
            cnt++;
        }
    }
}
