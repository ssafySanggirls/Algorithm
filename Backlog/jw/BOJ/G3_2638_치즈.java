import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int cheeseCnt, time;
    static int[][] map;
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheeseCnt++;
                }
            }
        }

        while (cheeseCnt > 0) {
            checkOutside();
            melt();
            time++;
        }

        System.out.println(time);
    }

    static void checkOutside() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        q.offer(new Point(0, 0));

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                if (visited[nr][nc] || map[nr][nc] == 1) {
                    continue;
                }

                if (map[nr][nc] == 0) {
                    map[nr][nc] = 2;
                }

                visited[nr][nc] = true;
                q.offer(new Point(nr, nc));
            }
        }
    }

    static void melt() {
        Queue<Point> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    q.offer(new Point(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Point now = q.poll();

            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                if (map[nr][nc] == 2) {
                    cnt++;
                }

                if (cnt >= 2) {
                    map[now.r][now.c] = 0;
                    cheeseCnt--;
                    break;
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
