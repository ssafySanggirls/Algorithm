package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 15565
 * 분류 : 슬라이딩 윈도우
 *
 *
 */
public class Main_15565_조희라 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            if(Integer.parseInt(st.nextToken()) == 1)
                list.add(i);
        }

        if(list.size() < K) System.out.println(-1);
        else {
            int answer = list.get(K - 1) - list.get(0) + 1;

            for(int i = K; i < list.size(); i++) {
                int j = i - K + 1;
                int length = list.get(i) - list.get(j) + 1;
                answer = Math.min(answer, length);

            }

            System.out.println(answer);
        }


    }
}
