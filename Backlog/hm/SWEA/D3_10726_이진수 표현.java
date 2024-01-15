import java.io.*;
import java.util.*;

public class D3_10726_이진수 표현 {

    static int TC, N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= TC; tc++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 최하위 N 비트를 모두 1로 켠 비트마스크
            int check = (1 << N) - 1;
            boolean light = ( M & check) == check;

            if (light) System.out.println("#"+tc+" ON");
            else System.out.println("#"+tc+" OFF");
        }
    }

}