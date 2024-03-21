import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static int N, K;
    static int[] doll;
    static ArrayList<Integer> ryan;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        doll = new int[N];
        ryan = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            doll[i] = Integer.parseInt(st.nextToken());
            if (doll[i] == 1) {
                ryan.add(i);
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int start = 0; start <= ryan.size() - K; start++) {
            int end = start;
            int cnt = 0;
            while (end < ryan.size() && cnt < K) {
                cnt++;
                end++;
            }

            if (cnt == K) {
                int left = ryan.get(start);
                int right = ryan.get(end - 1);
                answer = Math.min(answer, right - left + 1);
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        System.out.println(answer);
    }
}
