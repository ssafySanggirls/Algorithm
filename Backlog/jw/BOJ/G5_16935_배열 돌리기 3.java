import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, R;
    static int[][] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] op = new int[R];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < op.length; i++) {
            switch (op[i]) {
                case 1: one(); break;
                case 2: two(); break;
                case 3: three(); break;
                case 4: four(); break;
                case 5: five(); break;
                case 6: six(); break;
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

    // 상하반전
    static void one() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N / 2; i++) {
            temp[i] = A[N - 1 - i];
            temp[N - 1 - i] = A[i];
        }

        A = temp;
    }

    // 좌우반전
    static void two() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                temp[i][j] = A[i][M - 1 - j];
                temp[i][M - 1 - j] = A[i][j];
            }
        }

        A = temp;
    }

    // 오른쪽 90도 회전
    static void three() {
        int[][] temp = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[j][N - 1 - i] = A[i][j];
            }
        }

        A = new int[M][N];
        int m = M;
        M = N;
        N = m;
        A = temp;
    }

    // 왼쪽 90도 회전
    static void four() {
        int[][] temp = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[M - 1 - j][i] = A[i][j];
            }
        }

        A = new int[M][N];
        int m = M;
        M = N;
        N = m;
        A = temp;
    }

    // 1/4 조각 시계방향
    static void five() {
        int[][] temp = new int[N][M];

        for (int i = 0, r = N / 2; i < r; i++) {
            for (int j = 0, c = M / 2; j < c; j++) {
                temp[i][j + c] = A[i][j];
                temp[i + r][j + c] = A[i][j + c];
                temp[i + r][j] = A[i + r][j + c];
                temp[i][j] = A[i + r][j];
            }
        }

        A = temp;
    }

    // 1/4 조각 반시계방향
    static void six() {
        int[][] temp = new int[N][M];

        for (int i = 0, r = N / 2; i < r; i++) {
            for (int j = 0, c = M / 2; j < c; j++) {
                temp[i + r][j] = A[i][j];
                temp[i][j] = A[i][j + c];
                temp[i + r][j + c] = A[i + r][j];
                temp[i][j + c] = A[i + r][j + c];
            }
        }

        A = temp;
    }
}
