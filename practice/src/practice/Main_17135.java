package practice;
/*
 * 
//17135 캐슬디펜스
6 5 1
1 0 1 0 1
0 1 0 1 0
1 1 0 0 0
0 0 0 1 1
1 1 0 1 1
0 0 1 0 0
정답: 9
 */
import java.util.*;
import java.io.*;
public class Main_17135{
	static int n,m,d,answer;
	static int arr[][];
	static boolean visited[][];
	static int dx[] = {-1,0,0}; //상 좌 우
	static int dy[] = {0,-1,1};
	
	static Queue<Dot> queue = new LinkedList<>();
	static class Dot{
		int x, y;
		Dot(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	static void bfs() {
		int count=0;
		
		int depth = 0; int delete = n-1;
		while(!queue.isEmpty()) {
			
			
			//깊이 탐색하는데 n+d까지 거리만큼 탐색한 후 종료 
			count++;
			depth++;
			Dot dot = queue.poll();
			int nx = dot.x;
			int ny = dot.y;
			
			for(int i=0; i<3; i++) {
				if(nx+dx[i]>=0 && ny+dy[i]>=0 && ny+dy[i]<m && !visited[nx+dx[i]][ny+dy[i]] && arr[nx+dx[i]][ny+dy[i]]==1) {
					visited[nx+dx[i]][ny+dy[i]]=true;
					answer++;
					queue.add(new Dot(nx+dx[i], ny+dy[i]));	
				}
			}
			
			if(depth==d) {
				depth=0;
				//맨밑에서부터 행 하나씩 무력화 시킨다.
				Arrays.fill( arr[delete], 0);
				delete--;
			}
			
			if(count==n+d) {
				return;
			}
			
		}
	}
	
	static void recur(int cur,int num) {
		if(cur==3) {
			//세개 선택한 것 부터 이제 연산 시작
			for(int i=0 ; i<m; i++) {
				if(arr[n][i]==2) {
					 queue.add(new Dot(n,i));
				}
			}
			
			//길이 1부터 d+n까지 너비 우선 탐색한다. 
			//하지만 각 탐색 마다 마지막 줄 한줄씩 모두 0으로 초기화해준다.
			bfs();
			
			return;
		}
		for(int i=num; i<m; i++) {
			arr[n][i]=2; //궁수 위치, 조합
			recur(cur+1,i+1);
			arr[n][i]=0;
		}
		
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		d=sc.nextInt();
		
		arr = new int[n+1][m];
		visited = new boolean[n+1][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		

		/* 
		 * arr[i+1][0~m-1]중 궁수를 둘 3개를 고른다.
		 * 너비 우선 탐색 시작. 너비 우선 탐색 시작할 때 마다 거리는 1씩 늘린다.
		 * 궁수에 1씩 가까워지는 점 처리
		 */
		
		recur(0,0);
		System.out.println(answer);
	}
}