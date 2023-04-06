// SWEA D4 1486 장훈이의 높은 선반
package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1486_hm {

	static int N, B, ans;
	static int[] arr;
	static boolean[] selected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr = new int[N];
			selected = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;
			combi();
			
			System.out.println("#"+t+" "+ans);
		}

	}
	
	public static void combi() {
		
//		if(cnt == N) {
//			if(B <= sum) ans = Math.min(ans, Math.abs(sum-B));	
//			return;
//		}
		
//		for (int i = start; i < N; i++) {
//			selected[i] = true;
//			combi(cnt+1, i+1, sum+arr[i]);
//		
//			selected[i] = false;
//			combi(cnt+1, i+1, sum);
//		}
		
		for(int i = 0; i < (1<<N); i++) { //비트마스킹 안쓰면 시간초과남
			int sum = 0;
			for (int j = 0; j < N; j++) {
				if((i&1 << j) != 0) {
					sum += arr[j];
				}
				if(B <= sum) ans = Math.min(ans, Math.abs(sum-B));
			}
		}
	}

}
