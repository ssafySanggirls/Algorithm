import java.io.*;
import java.util.*;

class Main {

    static int K, W, H;
    static int[][] map;
    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };
    static final int[] hdr = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static final int[] hdc = { 1, 2, 2, 1, -1, -2, -2, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean visited[][][] = new boolean[H][W][K + 1];
        q.offer(new Point(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.row == H - 1 && now.col == W - 1) {
                return now.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.row + dr[d];
                int nc = now.col + dc[d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                    continue;
                }

                if (map[nr][nc] == 1 || visited[nr][nc][now.horse]) {
                    continue;
                }

                visited[nr][nc][now.horse] = true;
                q.offer(new Point(nr, nc, now.cnt + 1, now.horse));
            }

            if (now.horse < K) {
                for (int d = 0; d < 8; d++) {
                    int hnr = now.row + hdr[d];
                    int hnc = now.col + hdc[d];

                    if (hnr < 0 || hnr >= H || hnc < 0 || hnc >= W) {
                        continue;
                    }

                    if (map[hnr][hnc] == 1 || visited[hnr][hnc][now.horse + 1]) {
                        continue;
                    }

                    visited[hnr][hnc][now.horse + 1] = true;
                    q.offer(new Point(hnr, hnc, now.cnt + 1, now.horse + 1));
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
    int horse;

    public Point(int row, int col, int cnt, int horse) {
        this.row = row;
        this.col = col;
        this.cnt = cnt;
        this.horse = horse;
    }
}
