import java.io.*;
import java.util.*;

public class G1_1194_달이 차오른다 가자 {

    static int N, M, sx, sy, ex, ey;
    static char[][] map;
    static boolean[][][] visit;
    static int[] dx= {-1, 1, 0, 0}, dy= {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M][64]; // abcdef -> 2의 6제곱 = 64

        String str = "";
        for(int i=0;i<N;i++) {
            str = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '0') {
                    sx = i;
                    sy = j;
                }
            }
        }

        int ans = bfs(sx, sy);
        System.out.println(ans);
    }

    public static int bfs(int r, int c) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(r, c, 0, 0));
        visit[r][c][0] = true;
        map[r][c] = '.'; // 다시 되돌아가도 갈 수 있게 하려고

        while(!q.isEmpty()) {
            Node n = q.poll();
            int x = n.x;
            int y = n.y;
            if(map[x][y] == '1') return n.time;

            for(int d=0;d<4;d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];

                if(nx<0 || ny<0 || nx >= N || ny >=M) continue;
                if(map[nx][ny] == '#' || visit[nx][ny][n.key]) continue;

                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') { // 열쇠
                    int idx = n.key | (1 << (map[nx][ny] - 'a'));
                    q.add(new Node(nx, ny, n.time+1, idx));
                    visit[nx][ny][idx] = true;
                }else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    boolean flag = (n.key & (1 << (map[nx][ny] - 'A'))) != 0; // 키 존재하는 지 체크
                    if(flag) {
                        q.add(new Node(nx, ny, n.time+1, n.key));
                        visit[nx][ny][n.key] = true;
                    }
                }else {
                    q.add(new Node(nx, ny, n.time+1, n.key));
                    visit[nx][ny][n.key] = true;
                }
            }
        }
        return -1;
    }

    static class Node{
        int x, y, time, key;
        Node(int x, int y, int time, int key){
            this.x = x;
            this.y = y;
            this.time = time;
            this.key = key;
        }
    }

}
