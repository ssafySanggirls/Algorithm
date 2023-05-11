package practice;
//인구이동
import java.util.*;
import java.io.*;
public class Main_16234 {
	static int n,l,r;
	//bfs를 통해서 인접한 값 차이가 
	static int arr[][];
	static boolean visited[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static Queue<Dot> queue = new LinkedList<>();
	static ArrayList<Dot> list = new ArrayList<>();
	
	static class Dot{
		int x, y;
		Dot(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	static void bfs() {

		
		//하루치

		//연합만들기
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int sum=0;
				if(!visited[i][j]) {
					visited[i][j]=true;
					queue.add(new Dot(i,j));
					sum+=arr[i][j];
					list.add(new Dot(i,j));
					
				
					if(!queue.isEmpty()) {
						Dot dot = queue.poll();
						System.out.println(dot.x+ " "+dot.y);
						System.out.println("hi");
						for(int s=0; s<4; s++) {
							int nx = dot.x+dx[s];
							int ny = dot.y+dy[s];
							System.out.println(nx+" "+ny+" ");
							
							if(nx>=0 && ny>=0 && nx<n && ny<n && !visited[nx][ny] 
									&& Math.abs(arr[nx][ny]-arr[dot.x][dot.y])>=l && Math.abs(arr[nx][ny]-arr[dot.x][dot.y])<=r ) {
								queue.add(new Dot(nx,ny));
								System.out.println(nx+ " "+ ny+" -> "+Math.abs(arr[nx][ny]-arr[dot.x][dot.y]));
								
								list.add(new Dot(nx,ny));
								visited[nx][ny]=true;
								sum+=arr[nx][ny];
							}
						}
						System.out.println(visited[1][1]);
					}
					
					
					//한 집단 생성 끝
//					sum=sum/list.size();
//					System.out.println(list.size());
//					for(int s=0; s<list.size(); s++) {
//						arr[list.get(s).x][list.get(i).y]=sum;
//					}
//					
//					for(int a=0; a<n; a++) {
//						for(int b=0; b<n; b++) {
//							if(visited[a][b]) {
//								System.out.print(arr[a][b]+" ");
//							}else {
//								System.out.print(arr[a][b]+" ");
//							}
//						}
//						System.out.println();
//					}
					
					
				}
//				System.out.println("Hi");

			}	
		}
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		l=sc.nextInt();
		r=sc.nextInt();
		arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		
		visited = new boolean[n][n];
		bfs();
		
	}

}
