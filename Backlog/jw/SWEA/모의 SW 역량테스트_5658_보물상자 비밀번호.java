import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {

    static int N, K;
    static HashSet<String> set;
    static Integer[] decimal;
    static final int A = 10;
    static final int B = 11;
    static final int C = 12;
    static final int D = 13;
    static final int E = 14;
    static final int F = 15;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            String s = br.readLine();
            set = new HashSet<String>();

            for (int i = 0; i < N / 4; i++) {
                for (int j = 0; j < N; j += N / 4) {
                    set.add(s.substring(j, j + N / 4));
                }
                s = s.charAt(N - 1) + s.substring(0, N - 1);
            }

            toDecimal();
            Arrays.sort(decimal, (i1, i2) -> i2 - i1);

            sb.append('#').append(tc).append(' ').append(decimal[K - 1]).append('\n');
        }
        System.out.println(sb);
    }

    static void toDecimal() {
        Iterator iterator = set.iterator();
        decimal = new Integer[set.size()];

        int idx = 0;
        while (iterator.hasNext()) {
            int temp = 0;
            String hexa = iterator.next().toString();
            temp = Integer.parseInt(hexa, 16);
            decimal[idx++] = temp;
        }
    }
}
