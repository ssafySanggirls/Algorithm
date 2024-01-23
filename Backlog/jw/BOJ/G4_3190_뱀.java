import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, K, L;
    static int[][] map;
    static Dir[] dirInfo;
    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }

        L = Integer.parseInt(br.readLine());
        dirInfo = new Dir[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);

            dirInfo[i] = new Dir(t, d);
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> snake = new ArrayDeque<>();
        map[1][1] = 2;
        snake.offer(new Point(1, 1));

        int time = 0;
        int t = 0;
        int d = 0;
        int r = 1, c = 1;

        while (true) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 1 || nr > N || nc < 1 || nc > N) {
                return time + 1;
            }

            if (map[nr][nc] == 2) {
                return time + 1;
            }

            if (map[nr][nc] == 0) {
                Point tail = snake.poll();
                map[tail.r][tail.c] = 0;
            }

            map[nr][nc] = 2;

            snake.offer(new Point(nr, nc));
            r = nr;
            c = nc;

            time++;
            if (t < L && time == dirInfo[t].time) {
                if (dirInfo[t++].direction == 'D') {
                    d = (d == 3 ? 0 : d + 1 );
                } else {
                    d = (d == 0 ? 3 : d - 1);
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

class Dir {
    int time;
    char direction;

    public Dir(int time, char direction) {
        this.time = time;
        this.direction = direction;
    }
}
