import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] num = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int startIdx = 0;
        int endIdx = 0;
        int answer = Integer.MAX_VALUE;
        int sum = 0;
        while (startIdx <= endIdx && endIdx <= N) {
            if (sum < S) {
                sum += num[endIdx++];
            } else {
                answer = Math.min(answer, endIdx - startIdx);
                sum -= num[startIdx++];
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }

        System.out.println(answer);
    }
}
