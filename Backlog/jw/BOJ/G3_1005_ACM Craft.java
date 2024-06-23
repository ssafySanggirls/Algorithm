import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int T, N, K, W;
    static int[] time, indegree, result;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            time = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            indegree = new int[N + 1];
            result = new int[N + 1];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                indegree[b]++;
            }

            W = Integer.parseInt(br.readLine());

            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                    result[i] = time[i];
                }
            }

            while (!q.isEmpty()) {
                int now = q.poll();

                for (int next : graph[now]) {
                    indegree[next]--;
                    result[next] = Math.max(result[next], result[now] + time[next]);
                    if (indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            sb.append(result[W]).append('\n');
        }
        System.out.println(sb);
    }
}
