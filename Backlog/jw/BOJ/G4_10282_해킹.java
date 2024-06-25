import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int cnt, result;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Node>[] graph;
    static final int INF = 200_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            distance = new int[n + 1];
            visited = new boolean[n + 1];
            graph = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
                distance[i] = INF;
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a, s));
            }

            cnt = 0;
            result = 0;
            dijkstra(c);

            for (int i = 1; i <= n; i++) {
                if (distance[i] < INF) {
                    cnt++;
                    result = Math.max(result, distance[i]);
                }
            }
            sb.append(cnt).append(' ').append(result).append('\n');
        }

        System.out.println(sb);
    }

    static void dijkstra(int start) {
        distance[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            visited[now.node] = true;

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
