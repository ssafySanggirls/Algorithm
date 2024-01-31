import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[][] dp;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][2];

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        visited = new boolean[N + 1];
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int idx) {
        visited[idx] = true;
        dp[idx][0] = 0;
        dp[idx][1] = 1;

        for (int next : adj[idx]) {
            if (visited[next]) {
                continue;
            }
            dfs(next);
            dp[idx][0] += dp[next][1];
            dp[idx][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }
}
