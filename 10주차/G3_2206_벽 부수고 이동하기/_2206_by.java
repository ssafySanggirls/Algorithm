package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// G3. 벽 부수고 이동하기
public class _2206 {
    static int n, m, map[][], ans;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ans = Integer.MAX_VALUE; // 정답: 최단 경로 -> 최소로 계속 갱신
        // 입력
        n = Integer.parseInt(st.nextToken()); // 행 길이
        m = Integer.parseInt(st.nextToken()); // 열 길이
        map = new int[n][m]; // 맵
        for(int i=0; i<n; i++) {
            char[] ch = br.readLine().toCharArray(); // 띄어쓰기 없이 0과 1로 입력받음
            for(int j=0; j<m; j++) {
                map[i][j] = ch[j] - '0';
            }
        }

        // 로직
        if(n == 1 && m == 1) { // 행렬의 크기가 1 X 1 이면 시작하자마자 도착이므로 bfs 돌 필요 없음
            ans = 1;
        }else { // 그게 아니라면 bfs 시작
            bfs();
        }
        if(ans == Integer.MAX_VALUE) ans = -1; // bfs를 모두 돌았는데 갱신이 안됐다면 도착할 수 없음 -> -1

        // 출력
        System.out.println(ans);
    }

    private static void bfs() { // 최단 경로이므로 너비 우선 탐색
        Queue<Info> q = new ArrayDeque<>();
        int[][][] visited = new int[n][m][2]; // 방문 배열(3차원): 맵의 크기(2차원) + 벽을 부수고 이동한 경로인지(0과 1)
        q.add(new Info(0, 0, 1, 0)); // 첫 시작
        visited[0][0][0] = 1; // 방문 체크
        int x, y, cnt, crush; // 현재 정보
        int nx, ny; // 다음 정보
        while(!q.isEmpty()) {
            Info info = q.poll();
            x = info.x; // 행
            y = info.y; // 열
            cnt = info.cnt; // 이동한 거리
            crush = info.crush; // 벽을 부쉈는지 아닌지
            for(int k=0; k<4; k++) { // 상하좌우 탐색
                nx = x + dx[k];
                ny = y + dy[k];

                if(nx == n-1 && ny == m-1) { // 도착지에 도착하면
                    ans = Math.min(ans, cnt+1); // 갱신
                    continue;
                }

                // 다음 위치가 범위 안에 있고 방문한 적 없다면
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && visited[nx][ny][crush] == 0) {
                    if(map[nx][ny] == 0) { // 벽이 없다면 그냥 이동
                        visited[nx][ny][crush] = 1;
                        q.add(new Info(nx, ny, cnt+1, crush));
                    }else { // 벽이 있다면 횟수 비교
                        if(crush == 0) { // 벽을 부순 적이 없다면 부수고 이동
                            visited[nx][ny][1] = 1;
                            q.add(new Info(nx, ny, cnt+1, 1));
                        }
                    }
                }
            }
        }
    }

    static class Info{
        int x, y; // 위치
        int cnt; // 움직인 칸
        int crush; // 벽을 부순 횟수

        public Info(int x, int y, int cnt, int crush) {
            super();
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.crush = crush;
        }
    }
}