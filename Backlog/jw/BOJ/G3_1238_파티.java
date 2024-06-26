import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int N, M, X, answer;
    static int[] distance, distance1, distance2;
    static boolean[] visited;
    static ArrayList<Node>[] graph;
    static final int INF = 1_000_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];

        distance = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            distance[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, time));
        }

        dijkstra(X, 0);

        distance1 = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            distance1[i] = distance[i];
        }

        distance2 = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (i != X) {
                distance = new int[N + 1];
                for (int j = 1; j <= N; j++) {
                    distance[j] = INF;
                }
                distance2[i] = dijkstra(i, X);
            }
        }

        for (int i = 1; i <= N; i++) {
            int temp = distance1[i] + distance2[i];
            answer = Math.max(temp, answer);
        }

        System.out.println(answer);
    }

    static int dijkstra(int start, int end) {
        distance[start] = 0;
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Node(start, 0));

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
        return 1;
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
