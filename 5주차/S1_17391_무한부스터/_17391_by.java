package com.ssafy.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/* S1. 무한부스터 */
public class _17391 {

	static int n, m, map[][], dp[][];
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로길이
		m = Integer.parseInt(st.nextToken()); // 가로길이
		map = new int[n][m]; // 부스터 개수 정보 저장
		dp = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 1;
		bfs();	
		
		System.out.println(dp[n-1][m-1]-1);
	}

	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(0, 0));
		int x = 0, y = 0; // 현재 위치
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			x = pos.x;
			y = pos.y;
			for(int d=0; d<2; d++) {
				int nx = x;
				int ny = y;
				for(int k=0; k<map[x][y]; k++) { // 부스터의 개수만큼 이동
					nx += dx[d];
					ny += dy[d];
					if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
						if(dp[nx][ny] > 1+dp[x][y]) {
							q.add(new Pos(nx, ny));
							dp[nx][ny] = 1+dp[x][y];
						}
					}else {
						break;
					}
				}
			}
		}
	}

	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
