import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N, answer;
    static int sharkSize, cnt;
    static int[][] map;
    static boolean[][] visited;
    static Shark now;
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    now = new Shark(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }

        sharkSize = 2;
        cnt = sharkSize;

        while (true) {
            PriorityQueue<Shark> pq = new PriorityQueue<>((o1, o2) ->
                    o1.time != o2.time ? o1.time - o2.time : (o1.row != o2.row ? o1.row - o2.row : o1.col - o2.col));
            visited = new boolean[N][N];

            pq.add(now);
            visited[now.row][now.col] = true;

            boolean flag = false;

            while (!pq.isEmpty()) {
                now = pq.poll();

                if (map[now.row][now.col] > 0 && map[now.row][now.col] < sharkSize) {
                    map[now.row][now.col] = 0;
                    cnt--;
                    answer = now.time;
                    flag = true;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = now.row + dr[d];
                    int nc = now.col + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] > sharkSize) {
                        continue;
                    }

                    pq.add(new Shark(nr, nc, now.time + 1));
                    visited[nr][nc] = true;
                }
            }

            if (!flag) {
                break;
            }

            if (cnt == 0) {
                sharkSize++;
                cnt = sharkSize;
            }
        }

        System.out.println(answer);
    }
}

class Shark {
    int row;
    int col;
    int time;

    public Shark(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
