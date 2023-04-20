package Gold;

import java.io.*;
import java.util.*;

public class _2206_hm {
	static int N, M, ans;
	static int[][] map; // 초기 상태 입력하는 배열
	static int[][][] visited; // 벽뿌순것과 안뿌순것의 좌표에 해당하는 거리저장
	
	public static class Wall{
		int x;
		int y;
		int broken; //0:false, 1:true
		
		public Wall(int x, int y, int broken) {
			super();
			this.x = x;
			this.y = y;
			this.broken = broken;
		}

		@Override
		public String toString() {
			return "Wall [x=" + x + ", y=" + y + ", broken=" + broken + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		visited = new int[N+1][M+1][2];
		
		for (int i = 1; i <= N; i++) {
			String[] s = br.readLine().split("");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(s[j-1]);
			}
		}
		
		int ans = bfs();
		System.out.println(ans);
	}

	private static int bfs() {
		Queue<Wall> q = new LinkedList<>();
		q.add(new Wall(1, 1, 0));
		visited[1][1][0] = 1;
		//0:벽안뿌숨.1:벽뿌숨
		
		int[] dx = {-1, 0, 1, 0}, dy= {0, 1, 0, -1};
		
		while(!q.isEmpty()) {
			Wall w = q.poll();
			int x = w.x;
			int y = w.y;
			int b = w.broken;
			
			if(x==N && y==M) {
				return visited[x][y][b];
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int nb = b;
				
				if(nx<1 || ny<1 || nx>N || ny>M) continue; //범위벗어났다면
				if(visited[nx][ny][nb] > 0) continue; // 방문한적있으면
				if(map[nx][ny] == 1) {
					if(nb == 1) continue;
					nb = 1;
				}
				visited[nx][ny][nb] = visited[x][y][b]+1;
				q.offer(new Wall(nx, ny, nb));
				
			}
//			print();
		}
		
		return -1;
	}
}