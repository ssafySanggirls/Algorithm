import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int N, E;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Node>[] graph;
    static final int INF = 300_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int res1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        int res2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        if (res1 <= 0 || res1 >= INF || res2 <= 0 || res2 >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(res1, res2));
        }
    }

    static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Node(start, 0));

        distance = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            distance[i] = INF;
        }
        distance[start] = 0;
        visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            visited[now.node] = true;
            if (now.node == end) {
                return distance[end];
            }

            for (Node next : graph[now.node]) {
                if (visited[next.node]) {
                    continue;
                }
                if (distance[next.node] > distance[now.node] + next.weight) {
                    distance[next.node] = distance[now.node] + next.weight;
                    pq.offer(new Node(next.node, distance[next.node]));
                }
            }
        }
        return distance[end];
    }
}

class Node {
    int node;
    int weight;

    public Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
