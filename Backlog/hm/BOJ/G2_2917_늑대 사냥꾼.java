import java.io.*;
import java.util.*;

public class G2_2917_늑대 사냥꾼 {

    static int N, M, sx, sy, ex, ey;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static ArrayDeque<int[]> tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        tree = new ArrayDeque<int[]>();

        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.MAX_VALUE;
                char c = str.charAt(j);
                if(c=='V') {
                    sx = i;
                    sy = j;
                }else if(c=='J') {
                    ex = i;
                    ey = j;
                }else if(c=='+') {
                    // 나무들에서 bfs를 돌면서 거리 전처리
                    // 이동한 거리 -> 좌표랑 원본값
                    tree.offer(new int[] {i, j, i, j});
                    map[i][j]=0;
                }
            }
        }

        trees();

    }

    private static void trees() {
        while(!tree.isEmpty()) {
            int[] temp = tree.poll();
            int x = temp[0];
            int y = temp[1];
            int fx = temp[2];
            int fy = temp[3];

            for(int d=0;d<4;d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];
                int distance = Math.abs(nx-fx)+Math.abs(ny-fy);

                if(nx<0 || ny<0 || nx >= N || ny >= M || distance >= map[nx][ny]) continue;

                map[nx][ny] = distance; // 늑대와의 거리를 표시
                tree.offer(new int[] {nx, ny, fx, fy});
            }
        }

        // 이분 탐색으로 최대 거리의 범위를 설정해줌
        int left=0;
        int right = N+M;
        while(left <= right) {
            int mid=(left+right)/2;
            if(bfs(mid)) left = mid+1;
            else right = mid-1;
        }

        System.out.println(left-1);
    }

    private static boolean bfs(int mid) {
        // 가장 먼 거리를 유지하면서 이동할 수 있는 경로
        if(map[sx][sy] < mid) return false;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        q.offer(new int[] {sx, sy});
        visit[sx][sy] = true;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];
            if(ex == x && ey == y) return true;

            for(int d=0;d<4;d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];

                if(nx<0 || ny<0 || nx>=N ||ny>=M) continue;
                if(map[nx][ny]<mid || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }

        return false;
    }

}
