package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _3584_hm {
	
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1];

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                parent[b] = a;
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean[] isParent = new boolean[N + 1];
            while (a != 0) {
                isParent[a] = true;
                a = parent[a];
            }

            while (true) {
                if (isParent[b]) {
                    sb.append(b).append("\n");
                    break;
                }

                b = parent[b];
            }

        }

        System.out.println(sb.toString().trim());

    }

}