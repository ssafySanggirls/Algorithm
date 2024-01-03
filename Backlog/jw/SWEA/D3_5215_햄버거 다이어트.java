import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int N, L, maxScore;
    static int[] score, cal;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            score = new int[N];
            cal = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }

            maxScore = 0;
            combination(0, 0, 0);
            sb.append('#').append(tc).append(' ').append(maxScore).append('\n');

        }
        System.out.println(sb);
    }

    static void combination(int idx, int sumScore, int sumCal) {
        if (sumCal <= L) {
            maxScore = Math.max(sumScore, maxScore);
        } else {
            return;
        }

        if (idx == N) {
            return;
        }

        combination(idx + 1, sumScore + score[idx], sumCal + cal[idx]);
        combination(idx + 1, sumScore, sumCal);
    }
}
