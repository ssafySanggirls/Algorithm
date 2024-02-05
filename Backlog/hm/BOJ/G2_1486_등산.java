import java.io.*;
import java.util.*;

public class G2_1486_등산 {

    static int[][] map;
    static int N, M, T, D, INF = 1000000000; //Integer.MAX_VALUE... 절대안됨...
    static int[] dx= {0, 1, 0, -1}, dy= {-1, 0, 1, 0};
    static int[][][][] dist;

    static class Node implements Comparable<Node>{
        int x, y, time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        dist = new int[N+1][M+1][N+1][M+1];
        for(int i=1;i<=N;i++) {
            String str = br.readLine();
            for(int j=1;j<=M;j++) {
                char c = str.charAt(j-1);
                if(c >= 'a' && c<='z') {
                    map[i][j] = c - 'a' + 26;
                }else {
                    map[i][j] = c - 'A';
                }
            }
        }

        for(int i=1;i<=N;i++)
            for(int j=1;j<=M;j++)
                for(int m=1;m<=N;m++)
                    for(int n=1;n<=M;n++)
                        dist[i][j][m][n] = INF;

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                // 모든 정점에서 모든 정점으로
                move(i, j);
            }
        }

        int max=0;
        for(int i=1;i<=N;i++)
            for(int j=1;j<=M;j++) {
                if(dist[i][j][1][1] + dist[1][1][i][j] <= D) {
                    max = Math.max(max, map[i][j]);
                }
            }
        System.out.println(max);

    }

    private static void move(int r, int c) {
        Queue<Node> q = new PriorityQueue<>();
        dist[r][c][r][c] = 0;
        q.add(new Node(r, c, 0));

        while(!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int t = node.time;

            if(dist[x][y][r][c] < t) continue;

            for(int d=0;d<4;d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];
                int nt;

                if(nx<1 || ny<1 || nx>N || ny>M) continue;
                if(Math.abs(map[x][y] - map[nx][ny]) > T) continue; // 높이차이가 T보다 작은 곳들만 가능

                // 현재 위치에서 높이가 낮은 곳이나 같은 곳으로 이동한다면 시간은 1초
                if(map[x][y] >= map[nx][ny]) nt = t+1;
                    // 높은 곳으로 이동한다면 두 위치의 높이의 차이의 제곱만큼
                else nt = t + (int)Math.pow((map[x][y] - map[nx][ny]), 2);

                if(dist[nx][ny][r][c]>nt && nt<=D) {
                    dist[nx][ny][r][c] = nt;
                    q.add(new Node(nx, ny, nt));
                }
            }
        }
    }
}
