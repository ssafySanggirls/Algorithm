package com.ssafy.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_4193 {
	
	static int N; // 수영장의 크기
	static int A, B; // 시작 위치
	static int C, D; // 도착 위치
	static int time; // 시간
	static int[][] pool; // 수영장
	static boolean[][] visited;
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			pool = new int[N][N];
			visited = new boolean[N][N];
			
			// 수영장 데이터 
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < N; j++) {
					pool[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 시작 위치
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			// 도착 위치
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			bfs(A, B);
			
			sb.append("#").append(test_case).append(" ").append(time).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int r, int c) {
		time = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowR = now[0];
			int nowC = now[1];
			
			// 도착했을 경우
			if (nowR == C && nowC == D) {
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextR = nowR + dr[i];
				int nextC = nowC + dc[i];
				
				// 배열의 범위를 벗어나는 경우
				if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
					continue;
				}
				// 방문 체크
				if (visited[nextR][nextC]) {
					continue;
				}
				// 장애물이 있는 경우
				if (pool[nextR][nextC] == 1) {
					continue;
				}
				// 소용돌이를 만난 경우
				if (pool[nextR][nextC] == 2) {
					time += 2;
					continue;
				}
				
				queue.offer(new int[] {nextR, nextC});
				visited[nextR][nextC] = true;				
			}
			time++;
		}
	}

}
