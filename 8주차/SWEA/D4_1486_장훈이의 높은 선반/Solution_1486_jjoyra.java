package _0403_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1486_조희라 {
    static int N, B, bitCheck, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            answer = Integer.MAX_VALUE;

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            subSet(0, 0);

            System.out.println("#" + t + " " + answer);
        }
    }

    static void subSet(int cnt, int sum) {

        if (sum >= B) {
            answer = Math.min(answer, sum - B);
            return;
        }

        if (cnt == N) return;
//        if(sumSubSet(start)) return;

//        if(cnt == N) {
//            int sum = 0;
//            for(int i = 0; i < N; i++) {
//                if((bitCheck & 1 << i) != 0) sum += arr[i];
//            }
//            if(sum >= B) answer = Math.min(sum - B, answer);
//
//            return;
//        }


        subSet(cnt + 1, sum + arr[cnt]);
        subSet(cnt + 1, sum);


    }
}

//    static boolean sumSubSet(int start) {
//        int sum = 0;
//        for(int i = 0; i < start; i++) {
//            if((bitCheck & 1 << i) != 0) sum += arr[i];
//        }
//        if(sum - B >= answer) return true;
//        return false;
//    }
//}
