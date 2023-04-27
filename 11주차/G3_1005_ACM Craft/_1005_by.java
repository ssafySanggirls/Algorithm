package g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// G3. ACM Craft
public class Main_1005 {

	static int N, K, time[], W, d[];
	static List<Integer>[] adjList;
	static List<Integer> end;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 개수
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 건물의 개수
			K = Integer.parseInt(st.nextToken()); // 건물간의 건설순서 규칙의 총 개수
			st = new StringTokenizer(br.readLine());
			time = new int[N+1]; // 각 건물당 건설에 걸리는 시간
			for(int i=1; i<=N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			adjList = new ArrayList[N+1]; // 인접리스트
			for(int i=0; i<N+1; i++) { // 초기화
				adjList[i] = new ArrayList<Integer>();
			}
			int from, to;
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken()); // 먼저 지어지는 건물
				to = Integer.parseInt(st.nextToken()); // 다음에 지어지는 건물
				adjList[to].add(from);
			}
			W = Integer.parseInt(br.readLine()); // 승리하기 위해 건설해야 할 건물의 번호
			
			d = new int[N+1]; // 건물이 지어지기 위해 걸리는 시간 저장
			end = new ArrayList<>(); // 도착할 노드 저장
			topologySort();
			
			int ans = 0;
			for(int e : end) { // 도착할 노드 돌면서
				if(d[e] > ans) { // 최대 시간 갱신
					ans = d[e];
				}
			}
			
			System.out.println(ans); // 출력
		}
	}
	
	static void topologySort(){
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(W); // 시작점은 승리하기 위해 건설해야 할 건물의 번호
		d[W] = time[W];
		
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();

			if(adjList[cur].size() > 0) { // 먼저 지어져야 할 건물이 있다면
				for(int a : adjList[cur]) { // 그 건물들 확인해보기
					if(d[a] < d[cur] + time[a]) { // 최대값으로 갱신
						d[a] = d[cur] + time[a];
						q.add(a);
					}
				}
			}else { // 먼저 지어져야 할 건물이 없다면 도착할 노드
				end.add(cur);
			}
		}
	}
}

/*
1
4 3
10 1 100 20
1 2
2 3
4 3
3

--> 120
 */
