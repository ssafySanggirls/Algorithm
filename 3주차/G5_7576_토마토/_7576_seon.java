import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//G5_7576_토마토
public class Main_G5_7576_토마토 {

	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int M; // 상자의 가로 칸 수
	static int N; // 상자의 세로 칸 수
	static int[][] box;
	static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken()); // 상자의 가로 칸 수
		N = Integer.parseInt(st.nextToken()); // 상자의 세로 칸 수
		box = new int[N][M];
		q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 저장되어 있는 상태면 0 출력
		int cnt = 0;
		for (int[] r: box) {
			for (int v: r) {
				if (v == 1) {
					cnt++;
				}
			}
		}
		
		if (cnt == M * N) {
			System.out.println(0);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 1) {
					q.offer(new int[] {i, j});
				}
			}
		}
		
		bfs();
		
		for (int[] r: box) {
			for (int v: r) {
				if (v == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int[] r: box) {
			for (int v: r) {
				if (v >= max) {
					max = v;
				}
			}
		}
		
		System.out.println(max - 1);
		
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if (ny < N && ny > -1 && nx < M && nx > -1) {
					if (box[ny][nx] == 0) {
						box[ny][nx] = box[y][x] + 1;
						q.offer(new int[] {ny, nx});
					}
				}
			}
		}
		
		
	}
	
}
