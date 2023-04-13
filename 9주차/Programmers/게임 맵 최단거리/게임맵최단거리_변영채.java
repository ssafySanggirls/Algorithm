package l2;

import java.util.*;

// 프로그래머스 > DFS/BFS LEVEL2. 게임 맵 최단거리
public class bfs_game {
    int[] dx = {0, 0, 1, -1}; // 동서남북: 행 이동
    int[] dy = {1, -1, 0, 0}; // 동서남북: 열 이동

    public int solution(int[][] maps) {
        int answer = Integer.MAX_VALUE;
        int n = maps.length;
        int m = maps[0].length;
        Queue<Pos> q = new ArrayDeque<>();
        int[][] visited = new int[n][m];
        q.add(new Pos(0, 0));
        visited[0][0] = 1;
        int x, y; // 현재 위치
        int nx, ny; // 다음 위치
        while(!q.isEmpty()){
            Pos pos = q.poll();
            x = pos.x;
            y = pos.y;
            if(x == n-1 && y == m-1){
                answer = Math.min(answer, visited[x][y]);
            }
            for(int k=0; k<4; k++){
                nx = x + dx[k];
                ny = y + dy[k];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && visited[nx][ny] == 0 && maps[nx][ny] == 1){
                    visited[nx][ny] = visited[x][y] + 1;
                    q.add(new Pos(nx, ny));
                }
            }
        }
        if(answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }

    class Pos{ // 위치 정보 저장할 클래스
        int x, y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
