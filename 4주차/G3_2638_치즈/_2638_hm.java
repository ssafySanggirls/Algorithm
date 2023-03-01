package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2638_hm {
	
	static int N, M, ans=0, size = 0; //가로, 세로, 정답변수
	static int[] dx = {-1, 1, 0, 0}; //상하좌우
	static int[] dy = {0, 0, -1, 1};
	static int[][] cheese; //치즈상태 배열
	static boolean[][] visited; //방문체크 배열
	static Queue<int[]> q; //녹일 치즈들 넣기

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];
		q = new LinkedList<int[]>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				if(cheese[i][j] == 1) q.offer(new int[] {i, j}); //녹을 수 있는 치즈들 넣어주기
			}
		}
		//외부공기2. 내부공기0. 치즈1
		meltcheese(); //치즈 녹이기 
		System.out.println(ans);
	}
	
	public static void outerAir(int x, int y) {
		//0,0부터 외부공기는 2로 바꿔주는 로직 => dfs
		visited[x][y] = true;
		cheese[x][y] = 2;
		
		for(int d=0;d<4;d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx<0 || N <= nx || ny<0 || M<= ny) //범위밖이라면 제외
				continue;
			if(visited[nx][ny] || cheese[nx][ny] == 1) //방문했거나 1이라면(치즈라면) 제외
				continue;
			//방문하지 않았고, 0일때  => 외부공기일때
			outerAir(nx, ny);
		}
	}
	
	public static void meltcheese() {
		//치즈를 녹이는 함수
		//2를 두번 이상 만나면 녹이기
		//그다음 outerAir하기
		while(!q.isEmpty()) {
			//큐가 빌때까지 반복하기
			ans++;
			visited = new boolean[N][M];
			outerAir(0, 0);
			
			int size = q.size();
			while(--size >= 0) {
				int x = q.peek()[0];
				int y = q.poll()[1];
				int cnt = 0;
				
				for(int d=0;d<4;d++) {//4방탐색 
					int nx = x+dx[d];
					int ny = y+dy[d];
					//범위밖이라면 제외
					if(nx<0 || N <= nx || ny<0 || M<= ny) continue;
					//외부공기와 만났다면	
					if(cheese[nx][ny] == 2) cnt++;
				}
                //녹는 치즈라면, 0으로 바꿔주기
				if(cnt >= 2) cheese[x][y] = 0; //1->0
				else q.offer(new int[] {x, y});
			}
		}
	}
}