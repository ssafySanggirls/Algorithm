import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[] win, draw, lose, team1, team2;
    static boolean available;

    public static void main(String[] args) throws Exception {
        team1 = new int[15];
        team2 = new int[15];

        int cnt = 0;
        for (int i = 0; i <= 4; i++) {
            for (int j = i + 1; j <= 5; j++) {
                team1[cnt] = i;
                team2[cnt] = j;
                cnt++;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        win = new int[6];
        draw = new int[6];
        lose = new int[6];

        for (int tc = 0; tc < 4; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int w = 0;
            int d = 0;
            int l = 0;
            available = false;
            
            for (int j = 0; j < 6; j++) {
                w += win[j] = Integer.parseInt(st.nextToken());
                d += draw[j] = Integer.parseInt(st.nextToken());
                l += lose[j] = Integer.parseInt(st.nextToken());
            }
            if (w + d + l != 30) {
                available = false;
            } else {
                dfs(0);
            }

            if (available) {
                sb.append('1').append(" ");
            } else {
                sb.append('0').append(" ");
            }
        }

        System.out.println(sb);
    }

    static void dfs(int idx) {
        if (available) {
            return;
        }

        if (idx == 15) {
            available = true;
            return;
        }

        int t1 = team1[idx];
        int t2 = team2[idx];

        // t1 승리
        if (win[t1] > 0 && lose[t2] > 0) {
            win[t1]--;
            lose[t2]--;
            dfs(idx + 1);
            win[t1]++;
            lose[t2]++;
        }

        // 무승부
        if (draw[t1] > 0 && draw[t2] > 0) {
            draw[t1]--;
            draw[t2]--;
            dfs(idx + 1);
            draw[t1]++;
            draw[t2]++;
        }

        // t2 승리
        if (lose[t1] > 0 && win[t2] > 0) {
            lose[t1]--;
            win[t2]--;
            dfs(idx + 1);
            lose[t1]++;
            win[t2]++;
        }
    }
}
