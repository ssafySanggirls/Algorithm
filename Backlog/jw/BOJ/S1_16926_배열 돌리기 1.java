import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, R;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] A = new int[N][M];
        int cnt = Math.min(N, M) / 2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < R; r++) {
            for (int i = 0; i < cnt; i++) {
                int row = i;
                int col = i;
                int value = A[row][col];
                int idx = 0;
              
                while (idx < 4) {
                    int nr = row + dr[idx];
                    int nc = col + dc[idx];

                    if (nr < i || nc < i || nr > N - 1 - i || nc > M - 1 - i ) {
                        idx++;
                    } else {
                        A[row][col] = A[nr][nc];
                        row = nr;
                        col = nc;
                    }
                }
                A[row + 1][col] = value;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(A[i][j]).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

}
