package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _15846_hm {
	/**
	 * 14501 퇴사 문제보다 N의 범위가 1500000으로 아주 커져서
	 * dp로 풀지 않으면 시간초과가 난다
	 * */

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] tp = new int[N+1][2]; //Ti와 Pi를 순서대로 저장하는 배열
		
		StringTokenizer st;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			tp[i][0] = Integer.parseInt(st.nextToken()); //시간 Ti
			tp[i][1] = Integer.parseInt(st.nextToken()); //수익금 Pi
		}

		int max = Integer.MIN_VALUE; //백준이가 얻을 수 있는 최대 이익 초기화
		int[] dp = new int[N+2]; //0은 비워두고 1부터 퇴사일 N+1까지
		for(int i=N;i>=1;i--) {
			if(i+tp[i][0] <= N+1) {
				//시간(i)+Ti를 더한 것이 퇴사하는 N+1과 같거나 작을때
				dp[i] = Math.max(dp[i+1] , tp[i][1] + dp[i+tp[i][0]]);
				//dp[i+1]과 
				//i번째의 수익금 + dp[i + 걸리는 시간]에서의 수익금을 비교함
			}
			else {
				//범위에 어긋나면
				dp[i] = dp[i+1];
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}