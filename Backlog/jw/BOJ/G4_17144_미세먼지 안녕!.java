import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int R, C, T;
    static int[][] map, copy;
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        copy = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < T; t++) {
            copy = copyMap(map);

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        spread(i, j);
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                if (map[i][0] == -1) {
                    operate(i, 1);
                    operate(i + 1, -1);
                    break;
                }
            }

            map = copyMap(copy);
        }
        System.out.println(getTotal());
    }

    static void spread(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(r, c));

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }

                if (map[nr][nc] == -1) {
                    continue;
                }

                copy[nr][nc] += map[now.r][now.c] / 5;
                copy[now.r][now.c] -= map[now.r][now.c] / 5;
            }
        }
    }

    static void operate(int row, int dir) {
        int r = row;
        int c = 0;

        int lu = 0, lb = 0;
        if (dir == 1) {
            lu = 0;
            lb = row + 1;
        } else {
            lu = row;
            lb = R;
        }

        int d = 0;
        while (d < 4) {
            int nr = r + dr[d] * dir;
            int nc = c + dc[d];

            if (nr < lu || nr >= lb || nc < 0 || nc >= C) {
                d++;
                continue;
            }

            copy[r][c] = copy[nr][nc];
            r = nr;
            c = nc;
        }
        copy[row][0] = -1;
        copy[row][1] = 0;
    }

    static int getTotal() {
        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                total += map[i][j];
            }
        }
        return total + 2;
    }

    static int[][] copyMap(int[][] origin) {
        int[][] copy = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copy[i][j] = origin[i][j];
            }
        }
        return copy;
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
