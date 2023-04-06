package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2178_hm {

	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 1; j <= M; j++) {
				map[i][j] = c[j-1] - '0';
			}
		}
		
		System.out.println(bfs(1, 1, 0));
	}
	
	public static int bfs(int i, int j, int cnt) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[N+1][M+1];
		q.offer(new int[] {i, j, cnt});
		visited[i][j] = true;
		
		int x, y, time = 0;
		while(!q.isEmpty()) {
			x = q.peek()[0];
			y = q.peek()[1];
			time = q.poll()[2];
			//System.out.println(x+" "+y+" "+time);
			
			if(x == N && y == M) {
				return ++time;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int ntime = time;
				
				if(nx<1 || ny<1 || nx>N || ny>M)
					continue;
				if(visited[nx][ny] || map[nx][ny] == 0)
					continue;
				
				q.offer(new int[] {nx, ny, ++ntime});
				visited[nx][ny] = true;
			}
		}
		return 0;
	}

}
