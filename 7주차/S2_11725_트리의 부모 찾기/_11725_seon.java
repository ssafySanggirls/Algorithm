package com.ssafy._0319_0323_7주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//S2_11725_트리의부모찾기
public class Main_11725_임희선 {
	
	static int N; // 노드의 개수
	static Node[] list;
	static int[] parents;
	
	static class Node {
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());
		list = new Node[N + 1];
		parents = new int[N + 1];
		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from] = new Node(to, list[from]);
			list[to] = new Node(from, list[to]);
		}
		
		bfs(1);
		
		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		
		q.offer(start);
		visited[start] = true;
		
		int curr = 0;
		while (!q.isEmpty()) {
			curr = q.poll();

			for (Node next = list[curr]; next != null; next = next.link) {
				if (!visited[next.vertex]) {
					parents[next.vertex] = curr;
					q.offer(next.vertex);
					visited[next.vertex] = true;
				}
			}
			
		}
		
		
	}
	
}
