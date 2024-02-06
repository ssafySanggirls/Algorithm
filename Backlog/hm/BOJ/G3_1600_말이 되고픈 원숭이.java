import java.io.*;
import java.util.*;

public class G3_1600_말이 되고픈 원숭이 {

    static int K, W, H;
    static int map[][];
    static boolean visit[][][];
    static int[] dx= {-1, 1, 0, 0}, dy= {0, 0, -1, 1};
    static int[] kx= {-2, -2, -1, 1, 2, 2, 1, -1}, ky= {-1, 1, 2, 2, 1, -1, -2, -2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visit = new boolean[H][W][K+1]; // 말 이동횟수도 넣어줌

        for(int i=0;i<H;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<W;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = move(0, 0);
        System.out.println(ans);
    }

    private static int move(int r, int c) {
        Queue<Node> q = new LinkedList<>();
        visit[r][c][0] = true;
        q.add(new Node(r, c, 0, 0));

        while(!q.isEmpty()) {
            Node n = q.poll();
            int x = n.x;
            int y = n.y;

            if(x == H-1 && y == W-1) {
                return n.mCnt;
            }

            for(int d=0;d<4;d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];

                if(nx<0 || ny<0 || nx>=H || ny>=W) continue;
                if(map[nx][ny]==1 || visit[nx][ny][n.hCnt]) continue;

                visit[nx][ny][n.hCnt] = true;
                q.add(new Node(nx, ny, n.hCnt, n.mCnt+1));
            }

            if(n.hCnt < K) {
                for(int k=0;k<8;k++) {
                    int nkx = x+kx[k];
                    int nky = y+ky[k];

                    if(nkx<0 || nky<0 || nkx >= H || nky >= W) continue;
                    if(map[nkx][nky] == 1 || visit[nkx][nky][n.hCnt+1]) continue;

                    visit[nkx][nky][n.hCnt+1] = true;
                    q.add(new Node(nkx, nky, n.hCnt+1, n.mCnt+1));
                }
            }
        }
        return -1;
    }

    static class Node{
        int x, y;
        int hCnt; // 말 점프 횟수
        int mCnt; // 원숭이 움직인 횟수
        Node(int x, int y, int hCnt, int mCnt){
            this.x = x;
            this.y = y;
            this.hCnt = hCnt;
            this.mCnt = mCnt;
        }
    }
}
