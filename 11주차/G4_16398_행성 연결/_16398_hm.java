package boj;

import java.io.*;
import java.util.*;

public class _16398_hm {

	static int N;
	static int[][] adjMatrix;
	static int[] minEdge;
	static boolean[] visited;
	
	static class Vertex implements Comparable<Vertex>{
		int no; //정점번호
		int weight; //간선 비용
		public Vertex(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		adjMatrix = new int[N][N];
		minEdge = new int[N]; //다른 정점에서 자신으로의 간선 비용중 최소 비용을 저장해서 사용
		visited = new boolean[N]; //신장트리에 선택되었는지 여부를 기록하기 위한 배열
		
		// 데이터가 많을 수록 우선순위 큐를 사용해야 시간이 빠르고 효율적이고, 좋다
		PriorityQueue<Vertex> pq = new PriorityQueue<>(); //간선 비용이 낮은 순서대로 정렬하게 만들 pq
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N;j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
			minEdge[i] = Integer.MAX_VALUE;
		}
		
		long result = 0; // MST 비용
		int nodeCount = 0;
		minEdge[0] = 0; //임의의 시작점의 비용을 0으로 두고 출발
		
		pq.offer(new Vertex(0, 0));
		while(!pq.isEmpty()) {
			Vertex minVertex = pq.poll(); 
			if(visited[minVertex.no]) continue; 
			
			result += minVertex.weight;
			visited[minVertex.no] = true;
			if(++nodeCount==N) break;
			
			for(int i=0;i<N;i++) {  
				if(!visited[i] && adjMatrix[minVertex.no][i] != 0
						&& minEdge[i] > adjMatrix[minVertex.no][i]) {
					minEdge[i] = adjMatrix[minVertex.no][i];
					pq.offer(new Vertex(i, adjMatrix[minVertex.no][i]));
				}
			}
		}
		System.out.println(result);
	}

}
