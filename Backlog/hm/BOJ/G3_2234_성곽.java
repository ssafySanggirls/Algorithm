import java.util.*;
import java.io.*;

public class G3_2234_성곽 {

    static int N, M;
    static String[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    static int size;
    static int maxSize;
    static Map<String, Integer> room = new HashMap<>(); // 방 번호, 넓이

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[M+1][N+1];
        visited = new boolean[M+1][N+1];
        for(int i = 1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = String.format("%04d", Integer.parseInt(Integer.toBinaryString(x)));
                // 10진수 x를 2진수로 변환, 4자리수 되게 포맷팅
            }
        }

        int cnt=0, maxRoom=0;
        for(int i = 1; i<=M; i++) {
            for(int j = 1; j<=N; j++) {
                if(!visited[i][j]) {
                    cnt++;
                    size=1;
                    bfs(i, j, cnt);

                    room.put(String.valueOf(cnt), size); //cnt번째 방, cnt번째 방 넓이
                    maxRoom = Math.max(maxRoom, size);
                }
            }
        }

        run();
        System.out.println(cnt);
        System.out.println(maxRoom);
        System.out.println(maxSize);

    }

    private static void run() {
        for(int i=1; i<=M; i++) {
            for(int j=1; j<=N; j++) {
                String room1 = map[i][j];

                for(int d=0;d<4;d++) {
                    int ni = i+dx[d];
                    int nj = j+dy[d];

                    if(ni<1 || nj<1 || ni>M || nj>N) continue;

                    String room2 = map[ni][nj];
                    if(room1.equals(room2)) continue;
                    else {
                        maxSize = Math.max(maxSize, room.get(room1) + room.get(room2));
                    }
                }
            }
        }

    }

    private static void bfs(int i, int j, int cnt) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;

        while(!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.poll()[1];

            String dir = map[x][y]; //이진수
            map[x][y] = String.valueOf(cnt); //방 번호

            for(int d = 0; d<4; d++) {
                int nx = x+dx[d];
                int ny = y+dy[d];

                if(nx<1 || ny<1 || nx>M || ny>N) continue;

                // 북
                if(dir.charAt(2) == '0' && d == 0 && !visited[nx][ny]) {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    size++;
                }
                // 동
                if(dir.charAt(1) == '0' && d == 1 && !visited[nx][ny]) {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    size++;
                }
                // 남
                if(dir.charAt(0) == '0' && d == 2 && !visited[nx][ny]) {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    size++;
                }
                // 서
                if(dir.charAt(3) == '0' && d == 3 && !visited[nx][ny]) {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    size++;
                }
            }
        }
    }
}
