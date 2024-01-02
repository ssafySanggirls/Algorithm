import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, R;
    static int[][] A;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][M];

        int cnt = Math.min(N, M) / 2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int n = N;
        int m = M;

        for (int i = 0; i < cnt; i++) {
            rotate(i, 2 * n + 2 * m - 4);
            n -= 2;
            m -= 2;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(A[i][j]).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }

    static void rotate (int start, int len) {
        int move = R % len;

        for (int i = 0; i < move; i++) {
            int row = start;
            int col = start;
            int temp = 0;
            int pre = A[start][start];
            int idx = 0;

            while (idx < 4) {
                int nr = row + dr[idx];
                int nc = col + dc[idx];

                if (nr < start || nc < start || nr >= N - start || nc >= M - start) {
                    idx++;
                } else {
                    temp = A[nr][nc];
                    A[nr][nc] = pre;
                    pre = temp;
                    row = nr;
                    col = nc;
                }
            }
        }
    }
}
