import java.util.*;
// 게임 맵 최단거리
class Solution_jjoyra {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M;
    public int solution(int[][] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length;

        answer = bfs(maps);

        return answer;
    }

    static int bfs(int[][] maps) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int time = 1;

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()) {

            int size = queue.size();

            while(size-- > 0) {
                int[] tmp = queue.poll();

                if(tmp[0] == N - 1 && tmp[1] == M - 1) {
                    return time;
                }

                for(int d = 0; d < 4; d++) {
                    int nr = tmp[0] + dr[d];
                    int nc = tmp[1] + dc[d];

                    if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] &&                        maps[nr][nc] == 1) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }

            time++;

        }

        return -1;

    }


}