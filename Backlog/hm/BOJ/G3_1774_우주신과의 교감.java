import java.io.*;
import java.util.*;

public class G3_1774_우주신과의 교감 {

    static int N, M;
    static int[][] map;
    static int[] parent;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //우주신들 좌표수
        M = Integer.parseInt(st.nextToken()); //연결된 간선
        map = new int[N+1][2]; // 우주신의 좌표 저장

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());

            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        // 부모노드 초기화
        parent = new int[N+1];
        for(int i=0;i<parent.length;i++) {
            parent[i]=i;
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            union(s, e); // 이미 연결된 통로들을 합침
        }

        pq = new PriorityQueue<>();
        // 연결 가능한 모든 통로들을 큐에 추가
        for(int i=1;i<N;i++) {
            for(int j=1+1;j<N+1;j++) {
                int x1 = map[i][0];
                int y1 = map[i][1];
                int x2 = map[j][0];
                int y2 = map[j][1];

                double w = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
                pq.offer(new Edge(i, j, w));
            }
        }

        kru();
    }

    private static void kru() {
        double ans=0;

        // 크루스칼 알고리즘
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            // 최상위 노드가 다르면 경로를 합침 -> 연결 안된것들은 최상위노드가 본인
            if(union(edge.s, edge.e)) {
                ans += edge.weight; // 길이 = 가중치 더함
            }
        }

        System.out.println(String.format("%.2f", ans));
    }

    private static boolean union(int x, int y) {
        // 두 노드를 합침
        x = find(x);
        y = find(y);

        if(x != y) {
            parent[x] = y;
            return true;
        }

        return false;
    }

    private static int find(int x) {
        // x가 속하는 최상위 노드 찾음
        if(parent[x]==x) return x;
        else return parent[x] = find(parent[x]);
    }

    public static class Edge implements Comparable<Edge>{
        int s, e;
        double weight;

        public Edge(int s, int e, double weight) {
            this.s = s;
            this.e = e;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.weight, e.weight);
        }
    }

}
