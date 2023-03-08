package com.ssafy.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* G5. 로봇 청소기 */
public class _14503 {
	
	static int n, m, ans;
	static int[] dx = {-1, 0, 1, 0}; // 북동남서
	static int[] dy = {0, 1, 0, -1};
	static int[][] room;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 행크기
		m = Integer.parseInt(st.nextToken()); // 열크기
		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken()); // 로봇 처음 위치 - 행
		int sy = Integer.parseInt(st.nextToken()); // 로봇 처음 위치 - 열
		int sd = Integer.parseInt(st.nextToken()); // 로봇 처음 방향
		room = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		clean(sx, sy, sd);
		
		System.out.println(ans);
	}

	private static void clean(int x, int y, int d) {
		if(room[x][y] == 0) { // 비어있으면 청소하고 청소횟수 증가
			room[x][y] = 2;
			ans++;
		}
		
		int nd=0, nx=0, ny=0;
		for(int i=0; i<4; i++) {
			nd = (d+3) % 4;
			nx = x + dx[nd];
			ny = y + dy[nd];
			if(room[nx][ny] == 0) { // 다음 위치가 이동가능한 위치면
				clean(nx, ny, nd); // 이동하여 청소
				return;
			}
			d = nd;
		}
		
		// 후진
		nd = (d+2) % 4;
		nx = x + dx[nd]; 
		ny = y + dy[nd];
		if(room[nx][ny] == 1) { // 뒤가 벽이면 종료
			return;
		}
		clean(nx, ny, d); // 뒤로 후진
	}

}
