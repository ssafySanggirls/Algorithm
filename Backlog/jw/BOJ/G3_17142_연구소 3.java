import java.io.*;
import java.util.*;

class Main {

    static int N, M, ans, cnt;
    static int[][] originalMap;
    static ArrayList<Point> virus;
    static int[] selectedIdx;
    static boolean notEmpty;
    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        originalMap = new int[N][N];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                originalMap[i][j] = Integer.parseInt(st.nextToken());
                if (originalMap[i][j] == 2) {
                    virus.add(new Point(i, j));
                } else if (originalMap[i][j] == 0) {
                    cnt++;
                }
            }
        }

        if (cnt == 0) {
            System.out.println(0);
        } else {
            ans = Integer.MAX_VALUE;
            selectedIdx = new int[M];
            dfs(0, 0, 0);

            if (notEmpty) {
                System.out.println(ans);
            } else {
                System.out.println(-1);
            }
        }

    }

    static void dfs(int start, int depth, int visited) {
        if (depth == M) {
            bfs();
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            if ((visited & (1 << i)) == 0) {
                selectedIdx[depth] = i;
                dfs(i + 1, depth + 1, visited | (1 << i));
            }
        }
    }

    static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        int[][] copiedMap = new int[N][N];
        copy(copiedMap);
        int copyCnt = cnt;

        for (int i = 0; i < M; i++) {
            Point v = virus.get(selectedIdx[i]);
            visited[v.row][v.col] = true;
            copiedMap[v.row][v.col] = 3;
            q.offer(v);
        }

        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point now = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = now.row + dr[d];
                    int nc = now.col + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }

                    if (copiedMap[nr][nc] == 1 || visited[nr][nc]) {
                        continue;
                    }

                    if (copiedMap[nr][nc] == 0) {
                        copyCnt--;
                    }

                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }

                if (ans <= time) {
                    return;
                }
            }
            time++;

            if (copyCnt == 0) {
                ans = Math.min(ans, time);
                notEmpty = true;
                return;
            }
        }

    }

    static void copy(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = originalMap[i][j];
            }
        }
    }
}

class Point {
    int row;
    int col;
    int time;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
        this.time = 0;
    }
}
