package com.ssafy._0402_0413_9주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//G3_웜홀
//도로 무방향, 웜홀 방향
//음수 사이클이 발생하는 경우 YES, 발생하지 않는 경우 NO 출력

public class Main_1865_임희선 {

	static final int INF = 10001;
	static int N; // 지점 수
	static int M; // 도로 수
	static int W; // 웜홀 수
	static List<Edge> list;

    static class Edge {
    	int from; // 나가는 정점
    	int to; // 들어오는 정점
    	int cost; // 비용

    	public Edge(int from, int to, int cost) {
    		this.from = from;
    		this.to = to;
    		this.cost = cost;
    	}
    }
    
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine()); // 테스트 케이스 수
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // 지점의 수
			M = Integer.parseInt(st.nextToken()); // 도로의 수
			W = Integer.parseInt(st.nextToken()); // 웜홀의 수
			list = new ArrayList<>();
			for (int i = 0; i < M; i++) { // 도로 정보
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken()); // 연결된 지점의 번호
				int to = Integer.parseInt(st.nextToken()); // 연결된 지점의 번호
				int cost = Integer.parseInt(st.nextToken()); // 이 도로를 통해 이동하는데 걸리는 시간
				list.add(new Edge(from, to, cost));
				list.add(new Edge(to, from, cost));
			}
			for (int i = 0; i < W; i++) { // 웜홀 정보
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken()); // 웜홀의 시작지점
				int to = Integer.parseInt(st.nextToken()); // 웜홀의 도착지점
				int cost = Integer.parseInt(st.nextToken()); // 줄어드는 시간
				list.add(new Edge(from, to, -cost));
			}
			
			boolean possible = BellmanFord(1);
			if (possible) sb.append("YES");
			else sb.append("NO");
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}

	//정점의 개수, 간선의 개수, 출발지
	public static boolean BellmanFord(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		//정점의 개수만큼 반복
		for (int i = 1; i <= N - 1; i++) {
			//간선의 개수만큼 반복
			for (Edge edge: list) {
				//현재 간선의 들어오는 정점에 대해 비교
//				if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
				if (dist[edge.to] > dist[edge.from] + edge.cost) {
					dist[edge.to] = dist[edge.from] + edge.cost;
				}
			}
		}
		
		//음수 가중치 확인
		for (Edge edge : list) {
			//현재 간선의 들어오는 정점에 대해 비교 -> 더 작은 값 생기면 음수 사이클 존재
//			if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
			if (dist[edge.to] > dist[edge.from] + edge.cost) {
				return true;
			}
		}
		
		return false;
	}
	
}
