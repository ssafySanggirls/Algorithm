import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//G4_2617_구슬찾기
public class Main_G4_2617_구슬찾기 {
	
	static int N; // 구슬의 개수
	static int[][] light_list;
	static int[][] heavy_list;
	static int[] light_sum;
	static int[] heavy_sum;
	static Queue<Integer> q;
	static int[] cnt;

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 구슬의 개수
		int M = Integer.parseInt(st.nextToken()); // 저울에 올려본 쌍의 개수
		light_list = new int[N + 1][N + 1];
		heavy_list = new int[N + 1][N + 1];

		light_sum = new int[N + 1];
		heavy_sum = new int[N + 1];
		cnt = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			light_list[to][from] = 1;
			heavy_list[from][to] = 1;
		}
		
		int answer = 0;
		q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (light_list[i][j] == 1) {
					q.offer(i);
				}
			}
			
			if (!q.isEmpty()) {
				bfs(i, light_list);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (cnt[i] >= ((N + 1) / 2)) {
				answer++;
			}
		}
		
		q = new ArrayDeque<>();
		cnt = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (heavy_list[i][j] == 1) {
					q.offer(i);
				}
			}
			
			if (!q.isEmpty()) {
				bfs(i, heavy_list);
			}
		}
		for (int i = 1; i <= N; i++) {
			if (cnt[i] >= ((N + 1) / 2)) {
				answer++;
			}
		}
		
		System.out.println(answer);
		
		
	}

	private static void bfs(int start, int[][] list) {
		boolean[] visitied = new boolean[N + 1];
		
		int curr = 0;
		
		while (!q.isEmpty()) {
			curr = q.poll();
			
			for (int i = 1; i <= N; i++) {
				if (list[curr][i] != 0 && !visitied[i]) {
					q.offer(i);
					visitied[i] = true;
					cnt[start]++;
				}
			}
		}
	}
	
}
