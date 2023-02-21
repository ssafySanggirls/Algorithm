package silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14501 {

    static int n;
    static int[][] tp;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 상담일, n+1일 째 퇴사
        tp = new int[n+1][n+1];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            tp[i][0] = Integer.parseInt(st.nextToken());
            tp[i][1] = Integer.parseInt(st.nextToken());
        }

        ans = new int[16];
        dp(n-1);
        System.out.println(ans[0]);
    }

    private static void dp(int i) {

        if(i < 0) {
            return;
        }

        if((tp[i][0] + i) <= n) {
            if(tp[i][0] >= 2) {
                ans[i] = Math.max(ans[i+1], ans[(tp[i][0] + i)] + tp[i][1]);
            }else {
                ans[i] = ans[i+1] + tp[i][1];
            }
            dp(i-1);
        }else {
            ans[i] = ans[i+1];
            dp(i-1);
        }
    }
}
