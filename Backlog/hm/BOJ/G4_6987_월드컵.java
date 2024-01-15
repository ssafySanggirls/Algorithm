import java.io.*;
import java.util.*;

public class G4_6987_월드컵 {

    static int[] team1 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] team2 = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
    static int[][] score;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int tc = 0; tc < 4; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            score = new int[6][3];

            int sum = 0;
            for(int i = 0; i<6; i++) {
                score[i][0] = Integer.parseInt(st.nextToken()); // 승
                score[i][1] = Integer.parseInt(st.nextToken()); // 무
                score[i][2] = Integer.parseInt(st.nextToken()); // 패
                sum += (score[i][0] + score[i][1] + score[i][2]);
            }

            if(sum > 30) {
                System.out.print("0 ");
                continue;
            }

            if(check(0)) System.out.print("1 ");
            else System.out.print("0 ");
        }
    }

    private static boolean check(int cnt) {

        if(cnt == 15) {
            return true;
        }

        // 승
        if(score[team1[cnt]][0] > 0 && score[team2[cnt]][2] > 0) {
            score[team1[cnt]][0]--;
            score[team2[cnt]][2]--;
            if(check(cnt + 1)) return true;
            score[team1[cnt]][0]++;
            score[team2[cnt]][2]++;
        }

        // 무
        if(score[team1[cnt]][1] > 0 && score[team2[cnt]][1] > 0) {
            score[team1[cnt]][1]--;
            score[team2[cnt]][1]--;
            if(check(cnt + 1)) return true;
            score[team1[cnt]][1]++;
            score[team2[cnt]][1]++;
        }

        // 패
        if(score[team1[cnt]][2] > 0 && score[team2[cnt]][0] > 0) {
            score[team1[cnt]][2]--;
            score[team2[cnt]][0]--;
            if(check(cnt + 1)) return true;
            score[team1[cnt]][2]++;
            score[team2[cnt]][0]++;
        }

        return false;
    }

}
