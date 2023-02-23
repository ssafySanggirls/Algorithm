package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class _7576_hm {

	static int N, M;
	static int [][] tomato;
	
	static Queue<int[]> queue = new ArrayDeque<int[]>();
	static int ans = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); //가로 수
		N = Integer.parseInt(st.nextToken()); //세로 수
		tomato = new int[N][M];
		
		for(int i=0;i<N;i++) { //토마토 입력 받기
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// --4방 탐색으로 토마토 1인 곳 찾아서 익혀주기(1로 변환)
		//0->1로 -1은 건너뛰기 //무한반복 =>bfs, 최소시간!
		//그 다음에 전체 토마토 배열에서 0이 있는지 확인하고. 모두 익었으면 최소익은 날짜
		//처음부터 모두 익으면 0출력
		
		//내 답안이 틀렸던 이유 1 : 이 문제는 다른 문제들과 달리 시작 위치가 여러개에서 동시에니까! 그 시작위치를 모두 큐에 넣어주어야 한다
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tomato[i][j]==1) {
					queue.offer(new int[] {i,j});
				}
			}
		}
		
		bfs();
		
		
		// 출력
		// 1. 안익으면 -1
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tomato[i][j] == 0) { // 안 익은 토마토가 있다면
					ans = -1;
				}
			}
		}
		
		if(ans != -1) {
			System.out.println(ans - 1);
		}else {
			System.out.println(ans);
		}
	}
	
	public static void bfs() {
		int[] dx = {-1, 1, 0, 0}; //상하좌우
		int[] dy = {0, 0, -1, 1}; 
		
		while(!queue.isEmpty()) {//큐가 빌때까지 반복
			
			int size = queue.size(); //큐의 사이즈를 나타내는 변수
			while(--size >= 0) {
				
				int x = queue.peek()[0];// 현재 들어간 큐의 인덱스 빼기
				int y = queue.poll()[1];
				
				for(int d=0;d<4;d++) {
					int nextX = x+dx[d];
					int nextY = y+dy[d];
					
					if(nextX>=0 && nextX<N && nextY>=0 && nextY<M && tomato[nextX][nextY]==0 ) {
						//0이라면
						tomato[nextX][nextY] = 1;
						queue.offer(new int[] {nextX, nextY});
					}
				}
			}
			ans++;
		}
	}

}
