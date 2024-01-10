import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static int N, M, answer;
    static int[][] map;
    static ArrayList<CCTV> cctvList;
    static final String[][] cctvDir = {
            {""},
            {"0", "1", "2", "3"},
            {"02", "13"},
            {"01", "12", "23", "30"},
            {"012", "123", "230", "301"},
            {"0123"}
    };
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = Integer.MAX_VALUE;
        map = new int[N][M];
        cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 5) {
                    cctvList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        dfs(0, map);

        System.out.println(answer);
    }

    static void dfs(int idx, int[][] origin) {
        if (idx == cctvList.size()) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (origin[i][j] == 0) {
                        cnt++;
                    }
                }
            }
            answer = Math.min(cnt, answer);
            return;
        }

        String[] rotateCctv = cctvDir[cctvList.get(idx).type];
        for (int i = 0; i < rotateCctv.length; i++) {
            int[][] copy = copyArr(origin);
            String now = rotateCctv[i];
            for (int j = 0; j < now.length(); j++) {
                draw(cctvList.get(idx).row, cctvList.get(idx).col, now.charAt(j) - '0', copy);
            }
            dfs(idx + 1, copy);
        }
    }

    static int[][] copyArr(int[][] origin) {
        int[][] copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = origin[i][j];
            }
        }

        return copy;
    }

    static void draw(int sr, int sc, int dir, int[][] copiedMap) {
        int nr = sr + dr[dir];
        int nc = sc + dc[dir];

        while (true) {
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || copiedMap[nr][nc] == 6) {
                break;
            }

            copiedMap[nr][nc] = 7;
            nr += dr[dir];
            nc += dc[dir];
        }
    }
}

class CCTV {
    int row;
    int col;
    int type;

    public CCTV(int row, int col, int type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }
}
