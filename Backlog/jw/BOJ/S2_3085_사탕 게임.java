import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Main {

    static int N, max;
    static char[][] board;
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        check(board);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                change(i, j);
            }
        }

        System.out.println(max);
    }

    static void change(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                if (board[now.r][now.c] != board[nr][nc]) {
                    char[][] copy = copyBoard();
                    char temp = copy[now.r][now.c];
                    copy[now.r][now.c] = copy[nr][nc];
                    copy[nr][nc] = temp;

                    check(copy);
                }
            }
        }
    }

    static char[][] copyBoard() {
        char[][] copy = new char[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = board[i][j];
            }
        }

        return copy;
    }

    static void check(char[][] copy) {
        for (int r = 0; r < N; r++) {
            int cnt = 1;
            for (int c = 0; c < N - 1; c++) {
                if (copy[r][c] == copy[r][c + 1]) {
                    cnt++;
                    max = Math.max(max, cnt);
                } else {
                    cnt = 1;
                }
            }
        }

        for (int c = 0; c < N; c++) {
            int cnt = 1;
            for (int r = 0; r < N - 1; r++) {
                if (copy[r][c] == copy[r + 1][c]) {
                    cnt++;
                    max = Math.max(max, cnt);
                } else {
                    cnt = 1;
                }
            }
        }
    }
}

class Point {
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
