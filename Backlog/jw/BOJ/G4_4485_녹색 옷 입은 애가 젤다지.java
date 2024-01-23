import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, answer;
    static int[][] map;
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int cnt = 0;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();

            sb.append("Problem ").append(++cnt).append(": ").append(answer).append('\n');
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Point> q = new PriorityQueue<>((o1, o2) -> o1.coin - o2.coin);
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        q.offer(new Point(0, 0, map[0][0]));

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.r == N - 1 && now.c == N - 1) {
                answer = now.coin;
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                q.add(new Point(nr, nc, now.coin + map[nr][nc]));
            }
        }
    }
}

class Point {
    int r;
    int c;
    int coin;

    public Point(int r, int c, int coin) {
        this.r = r;
        this.c = c;
        this.coin = coin;
    }
}
