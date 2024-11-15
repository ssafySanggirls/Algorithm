import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int ans, virusCnt;
    static int[][] map;
    static ArrayList<Point> virus;
    static int[] selected;
    static boolean[] virusVisited;
    static boolean notEmpty;
    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 2) {
                    virusCnt++;
                    virus.add(new Point(i, j));
                    temp = 0;
                }
                map[i][j] = temp;
            }
        }

        virusVisited = new boolean[virusCnt];
        ans = Integer.MAX_VALUE;
        selected = new int[M];
        dfs(0, 0);

        if (notEmpty) {
            System.out.println(ans - 1);
        } else {
            System.out.println(-1);
        }

    }

    static void dfs(int start, int depth) {
        if (depth == M) {
            bfs();
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            if (virusVisited[i]) {
                continue;
            }
            selected[depth] = i;
            virusVisited[i] = true;
            dfs(start + 1, depth + 1);
            virusVisited[i] = false;
        }
    }

    static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        int[][] copy = new int[N][N];
        copyMap(copy);

        for (int i = 0; i < M; i++) {
            Point v = virus.get(selected[i]);
            copy[v.row][v.col] = 2;
            visited[v.row][v.col] = true;
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

                    if (copy[nr][nc] == 1 || visited[nr][nc]) {
                        continue;
                    }

                    copy[nr][nc] = 2;
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }

            time++;
        }

        if (checkMap(copy)) {        
            ans = Math.min(ans, time);
            notEmpty = true;
        }

    }

    static void copyMap(int[][] copy) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    static boolean checkMap(int[][] thisMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (thisMap[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}

class Point {
    int row;
    int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
