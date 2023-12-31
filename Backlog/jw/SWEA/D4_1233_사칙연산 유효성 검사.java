import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());

            int answer = 1;
            StringTokenizer st;
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());

                st.nextToken();
                char node = st.nextToken().charAt(0);

                if (st.hasMoreTokens()) {
                    if ('0' <= node && node <= '9') {
                        answer = 0;
                    }
                } else {
                    if (node < '0' || '9' < node) {
                        answer = 0;
                    }
                }
            }
            sb.append("#").append(test_case).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
