package Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class _17391_hm {

	static int[][] boost;
	static boolean[][] visited;
	static int N, M, ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //N개의 줄
		M = Integer.parseInt(st.nextToken()); //M개의 양의 정수
		boost = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		for(int i = 1; i<= N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M ; j++) {
				boost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		bfs(1, 1);
		System.out.println(ans);

	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		int[] dx = {1,0};
		int[] dy = {0,1};
		q.offer(new int[] {r, c, boost[r][c]});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			ans++;
			while(--size>=0) {
				int x = q.peek()[0];
				int y = q.peek()[1];
				int item = q.poll()[2];
			
				for(int j = 1; j <= item; j++ ) {
					for(int d = 0; d<2; d++) {
						int nx = x + j*dx[d];
						int ny = y + j*dy[d];
						
						if(nx<1||ny<1||nx> N||ny> M||visited[nx][ny])
							continue;
						
						if(nx == N && ny == M) {
							return;
						}
						
						q.offer(new int[] {nx, ny, boost[nx][ny]});
						visited[nx][ny] = true;
					}
				}
			}
			
						
		}		
	}
}
