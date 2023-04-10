import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_2382 {
	
	static int N; // 가로, 세로의 크기
	static int M; // 미생물 군집들을 격리한 시간
	static int K; // 최초 미생물 군집의 개수
	static ArrayList<Microbe> list;
	static final int[] dr = {0, -1, 1, 0, 0};
	static final int[] dc = {0, 0, 0, -1, 1};
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				list.add(new Microbe(row * N + col, row, col, cnt, dir));
			}
			move();
			
		}
		
		System.out.println(sb);
	}
	
	static void move() {
		
		// M시간동안 반복
		for (int i = 0; i < M; i++) {
			for (int j = list.size() - 1; j >= 0; j--) {
				// 군집들이 1시간마다 이동방향에 있는 다음 셀로 이동
				Microbe m = list.get(j);
				m.row += dr[m.dir];
				m.col += dc[m.dir];
				m.num = (m.row * N) + m.col;
				
				// 약품이 칠해진 셀에 도착했을 때
				if (m.row == 0 || m.row == N - 1 || m.col == 0 || m.col == N - 1) {
					m.cnt /= 2;
					m.dir = changeDir(m.dir);
					if (m.cnt == 0) {
						list.remove(j);
					}
				}
			}
			
			Collections.sort(list);
			
			// 2개 이상의 군집이 한 셀에 모이는 경우
			converge();

		}
		
		int totalCnt = 0;
		for (Microbe m : list) {
			totalCnt += m.cnt;
		}
		sb.append(totalCnt).append('\n');
	}
	
	/**
	 * 방향을 반대로 바꾼다.
	 * @param dir 방향
	 * @return 반대 방향
	 */
	static int changeDir(int dir) {
		
		int tempDir = 0;
		switch (dir) {
		case 1: // 상
			tempDir = 2;
			break;
		case 2: // 하
			tempDir = 1;
			break;
		case 3: // 좌
			tempDir = 4;
			break;
		case 4: // 우
			tempDir = 3;
			break;
		default:
			break;
		}
		
		return tempDir;
	}
	
	/**
	 * 한 칸에 미생물이 2 이상일 때
	 */
	static void converge() {
		
		for (int i = list.size() - 1; i > 0; i--) {
			Microbe now = list.get(i);
			Microbe next = list.get(i - 1);
			
			// 한 칸에 미생물이 2가지 이상일 때
			if (now.num == next.num) {
				// 그룹이 큰 쪽에 수를 더해줄게
				now.cnt += next.cnt;
				// 그리고 미생물 수가 적은 그룹을 지울게
				list.remove(i - 1);
			}
		}
	}

}

class Microbe implements Comparable<Microbe> {
	
	int num; // 배열에서 왼쪽 위를 0으로 했을 때의 번호
	int row;
	int col;
	int cnt;
	int dir;
	
	public Microbe(int num, int row, int col, int cnt, int dir) {
		super();
		this.num = num;
		this.row = row;
		this.col = col;
		this.cnt = cnt;
		this.dir = dir;
	}
	
	@Override
	public int compareTo(Microbe o) {
		
		// num이 같은 경우
		if (this.num == o.num) {
			// cnt가 작은 순서대로 정렬
			return this.cnt - o.cnt;
		}
		// num 이 큰 순서대로 정렬
		return o.num - this.num;
	}
	
}
