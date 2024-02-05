import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] T = new int[N + 2];
        int[] P = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 2];

        int max = 0;
        for (int i = 1; i <= N + 1; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }

            int nextDay = i + T[i];
            if (nextDay <= N + 1) {
                dp[nextDay] = Math.max(dp[nextDay], max + P[i]);
            }
        }

        System.out.println(max);
    }
}
