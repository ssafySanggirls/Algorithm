import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//S1_17391_무한부스터
public class Main_S1_17391_무한부스터 {
	
	static int[] dx = { 0, 1 }; // 아래, 오른쪽
	static int[] dy = { 1, 0 }; // 아래, 오른쪽
	static int N;
	static int M;
	static int[][] map;
	static int[][] cnt;
	
	static class Pos {
		int y;
		int x;
		
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 맵의 세로 길이
		M = Integer.parseInt(st.nextToken()); // 맵의 가로 길이
		map = new int[N + 1][M + 1];
		cnt = new int[N + 1][M + 1];
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(cnt[i], Integer.MAX_VALUE);
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(map[1][1], 1, 1);
		
		System.out.println(cnt[N][M]);
		
	}

	private static void bfs(int start, int i, int j) {
		Queue<Pos> q = new ArrayDeque<>();
		
		q.add(new Pos(i, j));
		cnt[i][j] = 0;
		
		while (!q.isEmpty()) {
			int y = q.peek().y;
			int x = q.peek().x;
			q.poll();
			
			for (int d = 0; d < 2; d++) {
				int ny = y;
				int nx = x;
				for (int k = 1; k <= map[y][x]; k++) {
					ny += dy[d];
					nx += dx[d];
					if (ny < N + 1 && ny > 0 && nx < M + 1 && nx > 0) {
						if (cnt[ny][nx] > cnt[y][x] + 1) {
							q.offer(new Pos(ny, nx));
							cnt[ny][nx] = cnt[y][x] + 1;
						}
					}
				}
			}
		}
	}

}
