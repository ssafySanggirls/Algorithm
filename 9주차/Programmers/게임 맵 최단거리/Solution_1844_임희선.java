import java.util.*;

class Solution {
    
    int[] dx = { 0, 1, 0, -1 };
    int[] dy = { -1, 0, 1, 0 };
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        answer = bfs(0, 0, maps, maps.length, maps[0].length);
        
        if (answer == 0) {
            answer = -1;
        }
        
        return answer;
    }

    public int bfs(int i, int j, int[][] maps, int N, int M) {
        int[][] path = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {i, j});
        path[i][j] = 1;
        
        while (!q.isEmpty()) {
            int y = q.peek()[0];
            int x = q.peek()[1];
            q.poll();
            
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                
                if (ny > -1 && ny < N && nx > -1 && nx < M) {
                    if (maps[ny][nx] == 1 && path[ny][nx] == 0) {
                        path[ny][nx] = path[y][x] + 1;
                        q.offer(new int[] {ny, nx});
                    }
                }
            }
        }
        
        return path[N - 1][M - 1];
    }
}