import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi
//N+1일째에는 회사에 없기 때문에, 6, 7일에 있는 상담을 할 수 없다.
//상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램
//S3_14501_퇴사
public class Main_S3_14501_퇴사 {
	
	static int N; // 퇴사전 남은 일자, N일
	static Counseling[] list;
	static int max;
	
	static class Counseling {
		int T; // 상담 일수(상담을 완료하는데 걸리는 기간)
		int P; // 상담 금액(상담을 했을 때 받을 수 있는 금액)
		
		public Counseling(int T, int P) {
			this.T = T;
			this.P = P;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		list = new Counseling[N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(in.readLine());
			list[i] = new Counseling(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		max = Integer.MIN_VALUE;
		
		dfs(1, 0);

		System.out.println(max);
		
	}
	
	private static void dfs(int start, int sum) {
		if (start == N + 1) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = start; i <= N; i++) {
			dfs(i + list[i].T, sum + list[i].P);
			dfs(i + 1, sum);
		}
	}
	
}
