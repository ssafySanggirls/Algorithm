import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112 {
	
	static int D; // 두께
	static int W; // 가로
	static int K; // 합격 기준
	static int answer; // 투입 횟수의 최솟값
	static int[][] film; // 단면의 정보
	

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			film = new int[D][W];
			
			// 단면의 정보 입력 받기
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 99999;
			dfs(0, 0);
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	
	/**
	 * 약품 사용하기
	 * @param row 특정 막 
	 * @param cnt A or B로 바꾼 횟수
	 */
	static void dfs(int row, int cnt) {
		
		// 검사를 통과했을 경우
		if (inspection()) {
			// 투입 횟수의 최솟값 구하기
			answer = Math.min(cnt, answer);
			return;
		}
		
		// 모든 행이 완료되었을 경우
		if (row == D) {
			return;
		}
		
		// 약품 투여 횟수가 최솟값 보다 큰 경우
		if (cnt >= answer) {
			return;
		}
		
		// 약품 투여 횟수가 K와 같을 경우
		if (cnt == K) {
			answer = Math.min(cnt, answer);
			return;
		}
		
		// 선택된 막의 정보를 복사해두자
		int[] copyRow = new int[W];
		for (int i = 0; i < W; i++) {
			copyRow[i] = film[row][i];
		}
		
		// 선택된 막을 그대로 두고 다음 행으로
		dfs(row + 1, cnt);
		
		// 선택된 막을 A로 변경하고 다음 행으로
		for (int i = 0; i < W; i++) {
			film[row][i] = 0;
		}
		dfs(row + 1, cnt + 1);
		
		// 선택된 막을 B로 변경하고 다음 행으로
		for (int i = 0; i < W; i++) {
			film[row][i] = 1;
		}
		dfs(row + 1, cnt + 1);
		
		// 원래대로 되돌리기
		for (int i = 0; i < W; i++) {
			film[row][i] = copyRow[i];
		}
	}

	/**
	 * 보호 필름 성능 검사
	 * @return 성능 검사 성공/실패
	 */
	static boolean inspection() {
		for (int i = 0; i < W; i++) {
			// 동일한 셀의 개수
			int same = 1;
			for (int j = 1; j < D; j++) {
				if (film[j - 1][i] == film[j][i]) {
					same++;
				} else {
					same = 1;
				}
				if (same == K) {
					break;
				}
			}
			// 합격 기준에 못미치는 경우
			if (same < K) {
				return false;
			}
		}
		return true;
	}

}
