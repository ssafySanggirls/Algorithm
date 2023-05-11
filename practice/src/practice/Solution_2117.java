package practice;
/*
8 3
0 0 0 0 0 1 0 0
0 1 0 1 0 0 0 1
0 0 0 0 0 0 0 0
0 0 0 1 0 1 0 0
0 0 1 1 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 1 0 1 0
1 0 0 0 0 0 0 0
 */
import java.util.*;
import java.io.*;

//swea 홈 방범 서비스

class Solution_2117 {
	//운영비용 계산: 1 1+4 1+4+8 1+4+8+12 16 20 ...
	//fn(k)= fn(k-1)+4(k-1);
	
	//운영비용 계산
	static int cnt(int k) {
		if(k==0) {return 1;}
		return cnt(k-1)+4*(k-1);
	}
	
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};

	//일단 손해보지 않아야 한다는 조건은
	//운영비용<=m*집의 개수
	
	//각 격자 칸을 기준으로 거리가 k-1 까지 bfs 진행
	//인덱스 범위가 초기화한다면 무시하고 그대로 진행
	//k가 1, 2, 3,,,,,,, n까지
	//k가 언제까지 증가할 것인가.
	//f(k)>집으개수*m인 k까지 진행
	static class Dot{
		int x,y;
		Dot(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	static void bfs(int x, int y) {
		queue.add(new Dot(x,y));
		if(arr[x][y]==1) {
			ans++;
		}
		int distance=0;
		//x,y좌표에서 거리가 k까지 탐색
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			for(int s=0; s<size; s++) {
				Dot dot = queue.poll();
				visited[dot.x][dot.y]=true;
				for(int i=0; i<4; i++) {
					int nx = dot.x+dx[i];
					int ny = dot.y+dy[i];
					//인덱스 범위 확인, 방문하지 않았다면, 그리고 1이라면 ans++;
					if(nx>=0 && ny>=0 && nx<n && ny<n && !visited[nx][ny]) {
						//값에 관계없이 탐색, 방문처리는 들어간다
						queue.add(new Dot(nx,ny));
						visited[nx][ny]=true;
						//근데 이제 집이 있다는 조건이 있다면 
						if(arr[nx][ny]==1) {
							ans++;
						}
//						arr[nx][ny]=2;		
					}
				}
			}
			
			//근데 조건에 맞지 않은 경우에 값 무시하기
			if(cnt(k)>ans*m) {
				ans=-1;
				continue;
			}else {
				//조건에 맞는 경우
				
			}
			
//			if(distance++>k-1) {
//				System.out.println(ans);
//				return;
//				
//			}
			
		}
	}
	static int n,m,count,k,ans;
	static int arr[][];
	static boolean visited[][];
	static Queue<Dot> queue = new LinkedList<>();
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		arr = new int[n][n];				
		visited = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				arr[i][j]=sc.nextInt();
				if(arr[i][j]==1) {
					count++;
				}
			}
		}
		//각 격자칸을 선택해서 k를 늘려가며 조건을 만족하고 
		//1) 운영비용보다 m*집수 가 더 클때
		//2) 조건에 만족하면 더 많은 집을 담기 위해서 서비스 k를 증가시켜 나가기. 
		//3) k는 count*m보다 같거나 작아야 한다. k범위
		
//		System.out.println(count);
//		System.out.println(cnt(2));
		
//		k=0;
//		
//		while(cnt(k)<=count*m) {
//			k++;
//		}
//		k-=1;
//		System.out.println(cnt(k)+" "+count*m);
		
		//k가 손해보지 않는 가장 큰 범위의 서비스이다. 
		
		int answer=0;
		
//		arr[3][3]=2;
//		bfs(3,3);
		

//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				
				for(int s=1; s<=n; s++) {
				
				k=s;
			
				ans=0;
				visited = new boolean[n][n];
				bfs(i,j);
//				System.out.println(ans);
				if(answer<ans) {
					answer=ans;
				}
				
				}
			}
		}
		
		
		System.out.println("정답"+ answer);	
	}
}