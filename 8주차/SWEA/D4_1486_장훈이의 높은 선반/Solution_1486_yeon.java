import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1486 {
	
	static int B; // 선반의 높이
	static int N; // 점원 수
	static int min; // 높이가 B 이상인 탑 중에서 가장 작은 값
	static int[] height; // 점원의 키

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			height = new int[N];
			
			// 점원의 키 입력받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			min = Integer.MAX_VALUE;
			dfs(0, 0);
			sb.append(min - B).append("\n");
		}
		System.out.println(sb);
		
	}
	
	static void dfs(int index, int sum) {
		
		// 탑의 높이가 B 이상일 때 그만
		if (sum >= B) {
			min = Math.min(sum, min);
			return;
		}
		
		// 점원의 키를 전부 더했을 때 그만
		if (index == N) {
			return;
		}
		
		// 키를 더하자
		dfs(index + 1, sum + height[index]);
		// 다시 원래대로
		dfs(index + 1, sum);
	}

}
