import java.io.*;
import java.util.*;

public class G3_14391_종이 조각 {

	static int N, M, ans=Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];

		for(int i=0;i<N;i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(String.valueOf(arr[j]));
			}
		}

		dfs(0,0);
		System.out.println(ans);
	}

	private static void dfs(int x, int y) {
		if(x == N) {
			sum();
			return;
		}
		if(y == M) {
			dfs(x+1, 0); // 다음 행으로 이동
			return;
		}

		visit[x][y] = true; // 지금 칸이 가로로 읽는 칸이라면
		dfs(x, y+1); // 다음 열로 이동
		visit[x][y] = false; // 지금 칸이 세로로 읽는 칸이라면
		dfs(x, y+1); // 다음 열로 이동

	}

	private static void sum() {
		int res = 0;
		int temp;

		// 가로로 읽는 숫자들의 합
		for(int i=0;i<N;i++) {
			temp = 0;
			for(int j=0;j<M;j++) {
				if(visit[i][j]) {
					temp *= 10;
					temp += map[i][j];
				}else {
					res += temp; // 가로 숫자 더하기
					temp = 0;
				}
			}
			res += temp; // 가로 숫자 더하기
		}

		// 세로로 읽는 숫자들의 합
		for(int j=0;j<M;j++) {
			temp = 0;
			for(int i=0;i<N;i++) {
				if(!visit[i][j]) {
					temp *= 10;
					temp += map[i][j];
				}else {
					res += temp; // 세로 숫자 더하기
					temp = 0;
				}
			}
			res += temp; // 세로 숫자 더하기
		}

		ans = Math.max(ans, res);
	}
}
