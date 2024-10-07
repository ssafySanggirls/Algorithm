import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static char[][] map;
    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            map[i] = input.toCharArray();
        }

        System.out.println(bfs());

    }

    static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];

        q.offer(new Point(0, 0, 1, false));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.row == N - 1 && now.col == M - 1) {
                return now.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.row + dr[d];
                int nc = now.col + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                if (map[nr][nc] == '0') {
                    // 벽이 아닐 때
                    if (now.broken && !visited[nr][nc][1]) {
                        visited[nr][nc][1] = true;
                        q.offer(new Point(nr, nc, now.cnt + 1, now.broken));
                    } else if (!now.broken && !visited[nr][nc][0]) {
                        visited[nr][nc][0] = true;
                        q.offer(new Point(nr, nc, now.cnt + 1, now.broken));
                    }
                    
                } else {
                    // 벽일 때
                    if (!now.broken && !visited[nr][nc][1]) {
                        visited[nr][nc][1] = true;
                        q.offer(new Point(nr, nc, now.cnt + 1, true));
                    }
                }
            }
        }

        return -1;
    }
}

class Point {
    int row;
    int col;
    int cnt;
    boolean broken;

    public Point(int row, int col, int cnt, boolean broken) {
        this.row = row;
        this.col = col;
        this.cnt = cnt;
        this.broken = broken;
    }
}
