package com.ssafy._0402_0413_8주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//S1_미로탐색
public class _2178_seon {
	
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int N;
	static int M;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		// 1 = 이동할 수 있는 칸
		// 0 = 이동할 수 없는 칸
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String temp = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		
		bfs(0, 0);
		
		System.out.println(map[N - 1][M - 1]);
		
	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {i, j});
		
		while (!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if (map[ny][nx] == 1) {
						q.offer(new int[] {ny, nx});
						map[ny][nx] = map[y][x] + 1;
					}
				}
			}
		}
	}

}
