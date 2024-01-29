import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, answer;
    static int[][] table;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        table = new int[2][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            table[0][i] = Integer.parseInt(st.nextToken());
            table[1][i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];
        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int p) {
        if (idx >= N) {
            answer = Math.max(answer, p);
            return;
        }

        if (idx + table[0][idx] <= N) {
            dfs(idx + table[0][idx], p + table[1][idx]);
        } else {
            dfs(idx + table[0][idx], p);
        }
        dfs(idx + 1, p);
    }
}
