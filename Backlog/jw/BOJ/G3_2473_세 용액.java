import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] solution = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(solution);

        long min = Long.MAX_VALUE;
        int idx1 = 0, idx2 = 0, idx3 = 0;
        for (int start = 0; start < N; start++) {
            int middle = start + 1;
            int end = N - 1;

            while (middle < end) {
                long sum = solution[start] + solution[middle] + solution[end];
                if (Math.abs(sum) < min) {
                    idx1 = start;
                    idx2 = middle;
                    idx3 = end;
                    min = Math.abs(sum);
                }

                if (sum > 0) {
                    end--;
                } else if (sum < 0) {
                    middle++;
                } else {
                    break;
                }
            }
        }

        System.out.println(solution[idx1] + " " + solution[idx2] + " " + solution[idx3]);
    }
}
