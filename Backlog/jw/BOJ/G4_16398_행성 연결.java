import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[] parents;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        edges = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int w = Integer.parseInt(st.nextToken());
                if (w != 0) {
                    edges.add(new Edge(i, j, w));
                }
            }
        }

        Collections.sort(edges);
        makeSet();

        long result = 0;
        int cnt = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                result += e.weight;

                if (++cnt == N - 1) {
                    break;
                }
            }
        }

        System.out.println(result);

    }

    static void makeSet() {
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}
