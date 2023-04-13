package Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _15565_hm {

	static int N, K, ans; //가장 작은 연속된 인형들의 집합의 크기
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		ans = Integer.MAX_VALUE; //그런 집합이 없다면
		
		int j = 0;// 라이언 배열 인덱스
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			if(x==1) { 
				arr[j] = i; //라이언만 넣음
				j++;
			}
		}

		// 1 : 라이언 인형, 2: 어피치 인형
		if(j < K) System.out.println(-1);
		else {
			ans = func(j);
			System.out.println(ans);
		}
	}

	public static int func(int N) {
		int len = arr[K-1] - arr[0] + 1;
		int ans = Integer.MAX_VALUE;
		ans = Math.min(ans, len);
		
		int j = 1;
		for (int i = K; i < N; i++) {
			len = arr[i] - arr[j] + 1;
			ans = Math.min(ans, len);
			j++;
		}		
		return ans;
	}
}
