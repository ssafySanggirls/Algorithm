package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1504_hm {

	static int N, v1, v2;
	static int[] parents;
	static List<Node> nodelist[];
	static class Node{
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
		@Override
		public String toString() {
			return "Node [idx=" + idx + ", cost=" + cost + "]";
		}
        
    }

    private static final int INF = 10000000;
    /**
     * <반례에 대한 계산>
     * 거치게 되는 모든 간선 카운트: { 2*(n-2) + 1 where n=800 } ~ 1.6 * 10^3
     * 따라서 최악의 경우 distance 테이블에 저장되는 값의 최대 크기: (1.6 * 10^3) * 1000 = 1.6 * 10^6 ----- (3)
     * 
     * */
    public static int[] dijkstra(List<Node> list[], int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));

        int dis[] = new int[N+1];
        Arrays.fill(dis, INF); //최소값 갱신 로직을 반영해야 함. 큰값으로 초기화
        dis[start] = 0;
        parents[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(dis[now.idx] < now.cost) continue;

            for(Node next : list[now.idx]) {
                int cost = next.cost + dis[now.idx];
                if(dis[next.idx] > cost) {
                    dis[next.idx] = cost;
                    parents[next.idx] = now.idx; 
                    pq.add(new Node(next.idx, cost));
                }
            }
        }
        return dis;
    }
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
//		System.out.println(N+" "+E);
		nodelist = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			nodelist[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			nodelist[a].add(new Node(b, c));
			nodelist[b].add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		//1->v1->v2->N
		//1->v2->v1->N
		int first = 0, second=0;
		parents = new int[N+1];
		int dis[] = dijkstra(nodelist, 1);
//		for (int i = 0; i <= N; i++) {
//			System.out.print(dis[i]+" ");
//		}
		first += dis[v1];
		second += dis[v2];
//		System.out.println("f,s: "+first+" "+second);
		
		parents = new int[N+1];
		dis = dijkstra(nodelist, v1);
//		for (int i = 0; i <= N; i++) {
//			System.out.print(dis[i]+" ");
//		}
		first += dis[v2];
		second += dis[N];
//		System.out.println("f,s: "+first+" "+second);
		
		parents = new int[N+1];
		dis = dijkstra(nodelist, v2);
//		for (int i = 0; i <= N; i++) {
//			System.out.print(dis[i]+" ");
//		}
		first += dis[N];
		second += dis[v1];
//		System.out.println("f,s: "+first+" "+second);
		
		int ans = Math.min(first, second);
		if(ans >= INF) System.out.println(-1);
		else System.out.println(ans);
	}

}
