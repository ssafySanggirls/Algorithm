import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M; // 도시의 크기, 집이 지불할 비용
	static int maxHouseCnt; // 홈 방범 서비스를 제공받는 최대 개수
	static int[][] map;
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			maxHouseCnt = 0;
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);					
				}
			}
			sb.append(maxHouseCnt).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs(int row, int col) {

		boolean[][] visited = new boolean[N][N];
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(row, col));
		visited[row][col] = true;
		
		// 서비스 영역 크기가 1인 경우
		int k = 1; // 서비스 영역의 크기
		int houseCnt = 0; // 집의 개수

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Point now = queue.poll();
									
                if (map[now.row][now.col] == 1) {
                    houseCnt++;
                }
				for (int i = 0; i < 4; i++) {
					int nr = now.row + dr[i];
					int nc = now.col + dc[i];
					
					// 배열의 범위 벗어나는지 체크
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}
					
					if (visited[nr][nc]) {
						continue;
					}
					
					queue.offer(new Point(nr, nc));
					visited[nr][nc] = true;
				}
			}
			if (getOpCost(k) <= houseCnt * M) {
				maxHouseCnt = Math.max(houseCnt, maxHouseCnt);
			}
            k++;
		}
	}
	
	private static int getOpCost(int k) {
		return k * k + (k - 1) * (k - 1);
	}

}

class Point {
	
	int row;
	int col;
	
	public Point(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
}
