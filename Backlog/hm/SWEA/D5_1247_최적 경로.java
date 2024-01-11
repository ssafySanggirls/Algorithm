import java.io.*;
import java.util.*;

public class D5_1247_최적 경로 {

	static int TC, N, ans, xc, yc, xh, yh;
	static int[][] maps;
	static boolean[] visited;
	static int[] selected;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= TC; t++ ) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			maps = new int[2][N]; //0-> x좌표, 1-> y좌표
			ans = Integer.MAX_VALUE;
			visited = new boolean[N];
			selected = new int[N];
			
			st = new StringTokenizer(br.readLine());
			xc = Integer.parseInt(st.nextToken());
			yc = Integer.parseInt(st.nextToken());
			xh = Integer.parseInt(st.nextToken());
			yh = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N ; i++) {
				maps[0][i] = Integer.parseInt(st.nextToken());
				maps[1][i] = Integer.parseInt(st.nextToken());
			}
			
			// 1~N까지 순열 구하기 -> 그 순열의 반환 값으로 최단 경로의 이동거리 반환
			recur(0);
			System.out.println("#"+t+" "+ans);
		}
	}
	
	public static void recur(int cnt) {
		
		if(cnt == N) {
			
			int sum = logic();
			sum += (Math.abs(xc - maps[0][selected[0]]) +
	                Math.abs(yc - maps[1][selected[0]]));
			sum += (Math.abs(xh - maps[0][selected[N-1]]) +
	                Math.abs(yh - maps[1][selected[N-1]]));
			
			ans = Math.min(sum, ans);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			selected[cnt] = i;			
			visited[i] = true;
			recur(cnt+1);
			visited[i] = false;
		}
	}

	public static int logic() {
		
		double len = 0;		
		for(int i = 0; i < N-1; i++) {
			 len += (Math.abs(maps[0][selected[i]] - maps[0][selected[i+1]]) +
		                Math.abs(maps[1][selected[i]] - maps[1][selected[i+1]]));
		}
		return (int)len;
	}

}
