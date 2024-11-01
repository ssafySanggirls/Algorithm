import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[][] cost, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][3];
        int min = Integer.MAX_VALUE;

        for (int rgb = 0; rgb < 3; rgb++) {
            // 마지막 집 초기화 후 시작
            for (int c = 0; c < 3; c++) {
                if (c == rgb) {
                    dp[N - 1][c] = 10001;
                } else {
                    dp[N - 1][c] = cost[N - 1][c];
                }
            }

            // dp
            for (int r = N - 2; r >= 0; r--) {
                dp[r][0] = Math.min(dp[r + 1][1], dp[r + 1][2]) + cost[r][0];
                dp[r][1] = Math.min(dp[r + 1][0], dp[r + 1][2]) + cost[r][1];
                dp[r][2] = Math.min(dp[r + 1][0], dp[r + 1][1]) + cost[r][2];
            }

            min = Math.min(dp[0][rgb], min);
        }

        System.out.println(min);
    }
}
