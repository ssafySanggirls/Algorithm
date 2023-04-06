/**
 * SWEA 2382. [모의 SW 역량테스트] 미생물 격리
 * 
 * */
package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_2382_hm {

	static int N, M, K;
	static int[][] map;
	static List<Cell> cellList;
	static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, -1, 1}; //0: 빈칸, 1:상, 2:하, 3:좌, 4:우
	
	public static class Cell {
		int x; //세로 위치
		int y; //가로 위치
		int cnt; //미생물 수
		int d; //이동방향
		public Cell(int x, int y, int cnt, int d) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Cell [x=" + x + ", y=" + y + ", cnt=" + cnt + ", d=" + d + "]";
		}		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //셀의 개수
			M = Integer.parseInt(st.nextToken()); //격리 시간
			K = Integer.parseInt(st.nextToken()); //미생물 군집 수
			
			// 미생물 리스트에 미생물 (클래스) 들을 입력받음
			map = new int[N][N];
			cellList = new ArrayList<>();
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()); //세로위치
				int y = Integer.parseInt(st.nextToken()); //가로위치
				int cnt = Integer.parseInt(st.nextToken()); //미생물 수
				int d = Integer.parseInt(st.nextToken()); //이동방향
				cellList.add(new Cell(x, y, cnt, d));
				map[x][y] = 1; //0 : A, 1 : B, ...
				//print();
				//System.out.println(x+" "+y+" "+cnt+" "+d);
			}
			
//			System.out.println("==start==");
			for (int m = 0; m < M; m++) {
//				System.out.println("move: "+m);
				move(); //미생물 이동
				addFound(); //군집있는 미생물 찾기
			}
			
//			System.out.println("=============end===========");
//			listDebug();
			
			int ans = 0;
			for (int i = 0; i < cellList.size(); i++) {
				ans += cellList.get(i).cnt;
			}
			
			System.out.println("#"+t+" "+ans);
		
		}
	}
	
	public static void move() {
		// 미생물을 이동하는 함수
		// kill();
		for (int i = cellList.size()-1; i >= 0 ; i--) {
			Cell c = cellList.get(i);
			if(c.cnt == 0) continue; //미생물이 사라짐.
			int x = c.x;
			int y = c.y;
			map[x][y]--;
			
			int nx = x + dx[c.d];
			int ny = y + dy[c.d];
			c.x = nx;
			c.y = ny;
			map[nx][ny]++;
			if(nx==0 || nx==N-1 || ny==0 || ny==N-1) {
				//가장자리에있으면 방향 바꿔주기
				if(c.d == 1) c.d = 2;
				else if(c.d ==2) c.d = 1;
				else if(c.d ==3) c.d = 4;
				else if(c.d ==4) c.d = 3;
				kill(i); //미생물 죽이기
			}
//			print();
		}		
	}
	
	public static void kill(int idx) {
		// 가장자리에 있는 미생물 죽이는 함수
		Cell c = cellList.get(idx);
		c.cnt = (int)(c.cnt / 2);
//		System.out.println("kill - idx: "+idx+" , c.cnt:"+c.cnt);
		if(c.cnt == 0) cellList.remove(idx);
	}
	
	public static void addFound() {
		// 같은 자리의 미생물끼리 합치는 함수
		List<Cell> list = new ArrayList<>(); //미생물 넣는 배열
		List<Integer> idxList = new ArrayList<>(); //넣어진 미생물들의 인덱스를 저장하는 배열
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 1) {
					//여러개의 미생물이 있음.
					for (int k = cellList.size()-1; k >= 0 ; k--) {
						if( cellList.get(k).x == i && cellList.get(k).y == j) {
							list.add(cellList.get(k)); //해당하는 미생물 더해주기
							idxList.add(k);
//							System.out.println("addFound - list.add : "+cellList.get(k).toString());
						
						}
					}
					//미생물 합쳐주기
					add(list, idxList);
					
					//리스트 비워주기
					list.clear();
					idxList.clear();
					
//					System.out.println("==============================");
				}
			}
		}
	}
	
	public static void add(List<Cell> list, List<Integer> idxList) {		
//		System.out.println(idxList.toString());
		int maxIdx = 0; // k, list배열에서 미생물수 가장 큰 것의 인덱스, 0
		int cellIdx = idxList.get(maxIdx); //cellList배열에서 , 7
		int maxCnt = cellList.get(cellIdx).cnt; //가장 큰 미생물 수, 100
		
//		System.out.println("maxIdx, maxCnt, cellIdx:" +maxIdx+" "+maxCnt+" "+cellIdx);
		
		Cell cell = new Cell(list.get(maxIdx).x, list.get(maxIdx).y,
				list.get(maxIdx).cnt, list.get(maxIdx).d);
		cellList.remove(cellIdx); //맨 뒤의 미생물 없앰
		
		for (int c = 1; c < idxList.size(); c++) {
//			if(list.get(c).cnt > maxCnt) {
//				maxIdx = c;
//				maxCnt = cellList.get(cellIdx).cnt;
//			}
			cell.cnt += list.get(c).cnt;
			
			cellIdx = idxList.get(c);	
			if(list.get(c).cnt > maxCnt) {
				maxIdx = c;
				maxCnt = cellList.get(cellIdx).cnt;
			}
			cellList.remove(cellIdx);			
		}
		
		// 미생물 수 가장 큰 것의 방향으로 바꿔주기
		cell.d = list.get(maxIdx).d;
		
		cellList.add(cell); //합친 미생물 다시 생성
		map[cell.x][cell.y] = 1;
		
//		System.out.println("add - print 디버깅");
//		print();
//		listDebug();
//		System.out.println("=====");
	}
	
	public static void print() {
		//map 디버깅을 위한 함수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}
	
	public static void listDebug() {
		for (int i = 0; i < cellList.size(); i++) {
			System.out.println(i+" "+cellList.get(i).toString());
		}
	}

}
