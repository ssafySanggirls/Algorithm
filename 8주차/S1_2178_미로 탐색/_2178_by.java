package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class _2178 {
    static int n, m, arr[][];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for(int i=0; i<n; i++) {
            char[] ch = br.readLine().toCharArray();
            for(int j=0; j<m; j++) {
                arr[i][j] = ch[j] - '0';
            }
        }

        bfs();
    }

    private static void bfs() {
        int[][] chk = new int[n][m];
        chk[0][0] = 1;
        Deque<Info> q = new ArrayDeque<>();
        q.add(new Info(0, 0));
        int x = 0; int y = 0;
        int nx = 0; int ny = 0;
        while(!q.isEmpty()) {
            Info info = q.poll();
            x = info.x;
            y = info.y;
            if(x == n-1 && y == m-1) {
                System.out.println(chk[x][y]);
                break;
            }
            for(int k=0; k<4; k++) {
                nx = x + dx[k];
                ny = y + dy[k];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && chk[nx][ny] == 0 && arr[nx][ny] == 1) {
                    chk[nx][ny] = chk[x][y] + 1;
                    q.add(new Info(nx, ny));
                }
            }
        }
    }

    static class Info{
        int x, y;

        public Info(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
}