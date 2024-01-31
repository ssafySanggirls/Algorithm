import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N, M, roomNo, maxRoomSize, sumRoomSize;
    static int[][] map;
    static int[][] room;
    static HashMap<Integer, Integer> roomSize;
    static boolean[][] visited;
    static final int[] dr = {0, -1, 0, 1};
    static final int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        roomSize = new HashMap<>();
        room = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (room[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    breakWall(i, j);
                }
            }
        }

        sb.append(roomNo).append('\n');
        sb.append(maxRoomSize).append('\n');
        sb.append(sumRoomSize).append('\n');

        System.out.println(sb);
    }

    static void bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        room[r][c] = ++roomNo;
        q.offer(new Point(r, c));
        int size = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= M || nc < 0 || nc >= N) {
                    continue;
                }

                if (room[nr][nc] != 0) {
                    continue;
                }

                int temp = 1 << d;
                if ((map[now.r][now.c] & (1 << d)) > 0) {
                    continue;
                }

                size++;

                room[nr][nc] = roomNo;
                q.offer(new Point(nr, nc));
            }
        }

        roomSize.put(roomNo, size);
        maxRoomSize = Math.max(maxRoomSize, size);
    }

    static void breakWall(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        visited[r][c] = true;
        q.offer(new Point(r, c));

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= M || nc < 0 || nc >= N) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                if (room[nr][nc] == room[now.r][now.c]) {
                    continue;
                }

                int sum = roomSize.get(room[nr][nc]) + roomSize.get(room[now.r][now.c]);
                sumRoomSize = Math.max(sumRoomSize, sum);
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
