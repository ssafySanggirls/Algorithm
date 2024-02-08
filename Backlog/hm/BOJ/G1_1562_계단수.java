import java.io.*;

public class G1_1562_계단수 {

    static int N, MOD = 1000000000;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][10][1 << 10]; // 2의 10제곱

        for(int i=1;i<=9;i++) {
            dp[1][i][1<<i] = 1; // 초기화
        }

        for(int i=2;i<=N;i++) {
            for(int j=0;j<=9;j++) {
                for(int visit=0;visit<(1<<10);visit++) { // 0~9까지 숫자가 모두 있는지 확인
                    /* dp[i][j][visit]
                     * i 번째 자리는 j라는 수이며
                     * visit : i 번째 자리까지 어떤 숫자를 사용했는지
                     * dp[i][j][visit|(1<<j)] = dp[i-1][j-1][visit]+dp[i-1][j+1][visit]
                     * */
                    int nv = visit | (1<<j);

                    if(j==0) {
                        dp[i][j][nv] += dp[i-1][j+1][visit] % MOD;
                    }else if(j==9) {
                        dp[i][j][nv] += dp[i-1][j-1][visit] % MOD;
                    }else {
                        dp[i][j][nv] += dp[i-1][j+1][visit] % MOD + dp[i-1][j-1][visit];
                    }

                    dp[i][j][nv] %= MOD;
                }
            }
        }

        long sum=0;
        for(int i=0;i<=9;i++) {
            sum += dp[N][i][(1<<10) -1] % MOD;
            sum %= MOD;
        }
        System.out.println(sum);
    }

}
