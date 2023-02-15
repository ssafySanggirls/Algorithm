package com.ssafy.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class _3190 {
	
	static int n;
	static int[][] brd;
	static HashMap<Integer, Character> time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 보드의 크기
		int k = Integer.parseInt(br.readLine()); // 사과의 개수
		brd = new int[n][n]; // 보드판
		brd[0][0] = 2; // 뱀 위치 설정
		for(int i=0; i<k; i++) { // 사과 위치 보드판에 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			brd[x-1][y-1] = 1; // 1행 1열부터 시작이므로 1씩 빼줘서 넣어주기
		}
		int l = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
		time = new HashMap<Integer, Character>();
		for(int i=0; i<l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			time.put(x, c);
		}
		
		int result = bfs(0, 0);
		System.out.println(result);
		
	}
	
	public static int change(int d, char c) { // c에 따라 방향 전환
		 if(c == 'D') {
			 d = (d+1) % 4; // 왼쪽으로 회전
		 }else {
			 d = (d-1) % 4; // 오른쪽으로 회전
		 }
		 
		 // 인덱스 벗어나면 다시 세팅
		 if(d == -1) {
			 d = 3;
		 }else if(d == 4) {
			 d = 0;
		 }
		 return d;
	}
	
	public static int bfs(int sx, int sy) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		int dir = 1; // 처음 방향
		int curt = 1; // 현재 시간
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(sx, sy));
		int x = sx; // 초기 행 위치
		int y = sy; // 초기 열 위치
		while (true) {
			x += dx[dir];
			y += dy[dir];
			if(x >= 0 && x < n && y >= 0 && y < n && brd[x][y] != 2) { // 범위 안에 있고 다음 위치가 자신의 몸이 아니면
				if(brd[x][y] != 1) { // 다음 위치에 사과가 없다면
					Pos p = q.poll();
					int a = p.x;
					int b = p.y;
					brd[a][b] = 0; // 현재 위치에 있는 꼬리 제거
				}
				brd[x][y] = 2; // 다음 위치로 이동
				q.add(new Pos(x, y)); // 다음 위치를 큐에 추가
				for(int tt : time.keySet()) {
					if(tt == curt) { // 지금 시간이 방향 전환할 시간이라면
						dir = change(dir, time.get(curt)); // 방향 전환하고
					}
				}
				curt++;
			}else { // 벽에 부딪히거나 본인 몸에 부딪히면 게임 종료
				return curt;
			}
		}
	}
	
	public static class Pos{ // 위치 저장할 클래스
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
