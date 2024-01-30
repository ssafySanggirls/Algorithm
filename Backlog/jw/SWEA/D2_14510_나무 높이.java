import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[] tree = new int[N];
            int[] diff = new int[N];

            int max = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                tree[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, tree[i]);
            }

            int sum = 0;
            int cnt1 = 0, cnt2 = 0;
            for (int i = 0; i < N; i++) {
                diff[i] = max - tree[i];
                sum += diff[i];
                cnt1 += diff[i] % 2;
                cnt2 += diff[i] / 2;
            }

            int answer = 0;
            if (cnt1 > cnt2) {
                answer = cnt2 * 2 + (cnt1 - cnt2) * 2 - 1;
            } else if (cnt1 < cnt2) {
                answer = cnt1 * 2 + (cnt2 - cnt1) * 2 / 3 * 2 + (cnt2 - cnt1) * 2 % 3;
            } else {
                answer = cnt1 * 2;
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.println(sb);
    }
}
