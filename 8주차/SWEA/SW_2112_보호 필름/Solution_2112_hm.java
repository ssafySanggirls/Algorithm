package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_hm {

	static int D, W, K, pass;
	static int[][] map;
	static boolean[] subset;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // 두께 (행)
			W = Integer.parseInt(st.nextToken()); // 가로크기 (열)
			K = Integer.parseInt(st.nextToken()); // 합격기준
			
			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			for (int i = 0; i < D; i++) {
//				for (int j = 0; j < W; j++) {
//					System.out.println(map[i][j]);
//				}
//			}
			
			pass = D+1;
			subset = new boolean[D];
			Arrays.fill(subset, true);
			combi(0);
			System.out.println("#"+t+" "+pass);
		}

	}
	
	public static void combi(int cnt) {
		//가로로 0이나 1로 전부 바꿔줄 인덱스의 부분집합을 구하는 함수

		
		if(cnt == D) {
			//System.out.println(Arrays.toString(subset));
						
			int x = 0; //부분집합 true인것들 개수
			int[][] copy = new int[D][W];
			for(int i=0;i<D;i++) {
				copy[i] = map[i].clone();
				if(subset[i]) x++;
			}
			
			if(x>=K) return;
			
			change(copy, x, 0, 0); //시약	
			
			return;
		}
		
		subset[cnt] = false;
		combi(cnt+1);
		subset[cnt] = true;
		combi(cnt+1);
		
	}
	
	public static void change(int[][] copy, int x, int cnt, int start) {
		//조합으로 시약 바꾸기
		//dfs, 재귀로 조합 구하기
		
		
		if(cnt == x) {
//		if(start == D) {
			if(check(copy)) {
				pass = Math.min(pass, x);
			}
			
//			for (int i = 0; i < D; i++) {
//				for (int j = 0; j < W; j++) {
//					System.out.print(copy[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println(x+"===========");
			
			return;
		}
		
		// ver1.		
//		if(subset[start]) {
//			Arrays.fill(copy[start], 0); //A로 약물투여
//			change(copy, x, cnt+1, start+1);
//			
//			
//			Arrays.fill(copy[start], 1); //B로 약물투여
//			change(copy, x, cnt+1, start+1);
//		}else {
//			change(copy, x, cnt, start+1);
//		}

//		// ver2.
		for(int i = start; i < D ; i++) { //제한시간 초과
			if(subset[i]) {
				Arrays.fill(copy[i], 0);
				change(copy, x, cnt+1, i+1);
				
				Arrays.fill(copy[i], 1);
				change(copy, x, cnt+1, i+1);
			}else {
				change(copy, x, cnt, i+1);
			}
		}
	}
	
	public static boolean check(int[][] copy) {
		//세로로 연속된 게 있는 지 확인하는 함수
		
		for(int i = 0; i < W; i++) {
			int cnt = 1; //시작점 포함
			boolean flag = false;
			
			for (int j = 0; j < D-1; j++) {
				if(copy[j][i] == copy[j+1][i]) {
					cnt++; //같으면 카운트 세줌
				}else {
					cnt = 1;
				}
				
				if(cnt >= K) {
					flag = true;
					break;
				}
			}
			if(!flag) return false;
		}
		
		return true;
	}

}
