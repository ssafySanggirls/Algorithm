package _0403_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_조희라 {
    static int D, W, K, answer;
    static int visited[] = new int[2];
    static int[][] map;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            answer = 0;
            flag = false;

            st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];

            for(int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i <= K; i++) {
                combi(0, 0, i);
                if(flag) break;
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    static boolean test() {

        for(int j = 0; j < W; j++) {
            int charCnt = 1;
            for(int i = 1; i < D; i++) {
                int cur = -1;

                // 현재 행의 특성
                if((visited[0] & 1 << i) != 0) cur = 0;
                else if((visited[1] & 1 << i) != 0) cur = 1;
                else cur = map[i][j];

                // 현재 행의 특성과 이전 행의 특성이 같은지 검사
                if ((visited[cur] & 1 << (i - 1)) != 0 || (( visited[(cur + 1) % 2] & 1 << (i - 1)) == 0 && map[i - 1][j] == cur)) {
                        charCnt++;
                } else charCnt = 1;

                if(charCnt >= K) break;
            }


            if(charCnt < K) return false;
        }

        return true;
    }

    static void combi(int cnt, int start, int d) {
        if(cnt == d) {
            if(test()) {
                answer = cnt;
                flag = true;
            }
            return;
        }

        for(int i = start; i < D; i++) {

            // 해당 행에 A약품을 넣을 때
            visited[0] |= 1 << i;
            combi(cnt + 1, i + 1, d);
            visited[0] ^= 1 << i;
            if(flag) return;

            // 해당 행에 B약품을 넣을 때
            visited[1] |= 1 << i;
            combi(cnt + 1, i + 1, d);
            visited[1] ^= 1 << i;
            if(flag) return;

        }

    }
}
