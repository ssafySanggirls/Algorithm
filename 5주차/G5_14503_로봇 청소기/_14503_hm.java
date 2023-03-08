package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class _14503_hm {

	static int N, M, ans; //방 크기, 로봇청소기가 바라보는 방향, ans : 청소하는 칸 개수
	static int[][] room; //방
	static boolean[][] visited; //방문체크
	
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1}; //북, 서, 남, 동 순
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//방 크기
		M = Integer.parseInt(st.nextToken());//
		room = new int[N][M];
		visited = new boolean[N][M];
		
		int r, c;
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());//로봇청소기 좌표값
		c = Integer.parseInt(st.nextToken());//
		int D = Integer.parseInt(st.nextToken());//처음에 로봇 청소기가 바라보는 방향
		if(D == 1) D = 3; //동 -> 서 (반시계방향으로 바꿔주기)
		else if(D==3) D = 1; // 서 -> 동
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		clean(r, c, D);
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(visited[i][j]) ans++;
			}
		}
		System.out.println(ans);
	}
	public static void clean(int r, int c, int D) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {r,c, D});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			int dir = q.poll()[2];
			
			int cleanD = dir;
			int cleaning = 0;
			//현재 칸의 주변 4칸 중
			for(int d = 0; d < 4; d++) {
				//1.반시계 방향으로 회전
				if(cleanD != 3) cleanD++;
				else cleanD = 0; //D==3일때
				
				//2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸인 경우 한칸 전진
				int D_nx = x + dx[cleanD];
				int D_ny = y + dy[cleanD];
				if(D_nx < 0 || D_ny < 0 || D_nx >= N || D_ny >= M || visited[D_nx][D_ny]|| room[D_nx][D_ny]==1)
					continue;
				
				q.offer(new int[] {D_nx, D_ny, cleanD});
				visited[D_nx][D_ny] = true;
				
				cleaning ++;
				break;//한칸 전진했음
			}
			if(cleaning ==0) {
				//청소되지 않은 빈칸이 없는 경우
				//후진
				int D_nx = x-dx[dir];
				int D_ny = y-dy[dir];
				
				if(D_nx < 0 || D_ny < 0 || D_nx >= N || D_ny >= M ||room[D_nx][D_ny] == 1)
					continue;
				
				q.offer(new int[] {D_nx, D_ny, dir}); //한칸후진하고 1번으로 돌아감
				visited[D_nx][D_ny] = true;
				
				if(room[D_nx][D_ny] == 1) return; //벽이라 후진할 수 없다면								
			}			
		}
	}
}