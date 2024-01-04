import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, K;
    static int min = Integer.MAX_VALUE;
    static int[][] A, copyA, op;
    static boolean[] visited;
    static int[] opIdxList;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][M + 1];
        visited = new boolean[K];
        opIdxList = new int[K];


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        op = new int[K][3];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            op[i][0] = Integer.parseInt(st.nextToken());
            op[i][1] = Integer.parseInt(st.nextToken());
            op[i][2] = Integer.parseInt(st.nextToken());
        }

        select(0);

        System.out.println(min);
    }

    static void select(int cnt) {
        if (cnt == K) {
            rotate();
            min = Math.min(min, getArrVal());
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            opIdxList[cnt] = i;

            select(cnt + 1);
            visited[i] = false;
        }
    }

    static void rotate() {
        copyA = new int[N + 1][M + 1];
        arrCopy(copyA);

        for (int k = 0; k < K; k++) {
            int opIdx = opIdxList[k];
            int r = op[opIdx][0];
            int c = op[opIdx][1];

            for (int s = 1; s <= op[opIdx][2]; s++) {
                int row = r - s;
                int col = c - s;

                int idx = 0;
                int temp = copyA[row][col];

                while (idx < 4) {
                    int nr = row + dr[idx];
                    int nc = col + dc[idx];

                    if (nr < r - s || nr > r + s || nc < c - s || nc > c + s) {
                        idx++;
                    } else {
                        copyA[row][col] = copyA[nr][nc];

                        row = nr;
                        col = nc;
                    }
                }
                copyA[row][col + 1] = temp;
            }
        }
    }

    static int getArrVal() {
        int arrMin = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += copyA[i][j];
            }
            arrMin = Math.min(arrMin, sum);
        }

        return arrMin;
    }

    static void arrCopy(int[][] copy) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                copy[i][j] = A[i][j];
            }
        }
    }
}
