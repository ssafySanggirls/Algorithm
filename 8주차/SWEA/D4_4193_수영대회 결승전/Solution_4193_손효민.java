package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_4193_손효민 {

	static int N, start_x, start_y, end_x, end_y, ans;
	static int [][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			//-- 입력
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			start_x = Integer.parseInt(st.nextToken());
			start_y = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			end_x = Integer.parseInt(st.nextToken());
			end_y = Integer.parseInt(st.nextToken());
			
			//--구현
			ans = 0;
			System.out.println("#"+t+" "+bfs(start_x, start_y, ans));
			
		}

	}
	
	public static int bfs(int i, int j, int ans) {	
		Queue<int[]> q = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[N][N];
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		q.offer(new int[] {i, j, ans});
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
		
			int x = q.peek()[0];
			int y = q.peek()[1];
			int cnt = q.poll()[2];
			System.out.println(x+" "+y+" "+cnt);
			if(x == end_x && y == end_y) return cnt;
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int ncnt = cnt; 
				
				if(nx<0 || ny<0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == 1)
					continue;
				
				if(map[nx][ny] == 0) {
					q.offer(new int[] {nx, ny, ncnt+1});
					visited[nx][ny] = true;	
				}else if(map[nx][ny] == 2){
					if(ncnt%3 == 2) {
						//소용돌이가 멈추는 때
						// -> 시간이 절대적으로 흐름! 무조건 2초 지나면 소용돌이 멈춤. 
						// -> 이동하면서 도착한 후에 2초가 아님!
						q.offer(new int[] {nx, ny, ncnt+1});
						visited[nx][ny] = true;	
					}
					else {
						//소용돌이가 멈추지 않는 때
						q.offer(new int[] {x, y, ncnt+1});
						visited[x][y] = true;	
					}
				}
			}
		}
		return -1;
	}
}
/*
 * 
 * 
 * 
0 0 0 0 0 0
0 1 1 0 0 0
0 0 0 1 2 e
1 1 0 1 0 1
0 0 0 1 0 1
s 0 0 2 0 1
 * 
 * */
