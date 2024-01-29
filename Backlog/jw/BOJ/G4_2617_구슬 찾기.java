import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static final int INF = 999;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
                arr[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        int[] rowCnt = new int[N + 1];
        int[] colCnt = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j && arr[i][j] != INF) {
                    rowCnt[i]++;
                    colCnt[j]++;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (rowCnt[i] >= N / 2 + 1) {
                answer++;
            }
            if (colCnt[i] >= N / 2 + 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
