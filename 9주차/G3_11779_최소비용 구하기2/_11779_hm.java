package Gold;

import java.io.*;
import java.util.*;

public class _11779_hm {

    static int START, N, M, END;
    static List<Node>[] nodeList;
    static int[] parents;
    
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

    private static final int INF = 100000;
    public static int[] dijkstra(List<Node> list[]){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(START, 0));

        int dis[] = new int[N+1];
        Arrays.fill(dis, INF); //최소값 갱신 로직을 반영해야 함. 큰값으로 초기화
        dis[START] = 0;
        parents[START] = 0;

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
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //도시개수
        M = Integer.parseInt(br.readLine()); //버스의 개수
        
        nodeList = new ArrayList[N+1];
        for(int i=0;i<=N;i++) {
            nodeList[i] = new ArrayList<Node>();
        }
        
        StringTokenizer st = null;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            nodeList[from].add(new Node(to, cost));
        }
        
        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());
        
        parents = new int[N+1];
        int[] dist = new int[N+1];
        dist = dijkstra(nodeList);
        
        
        //출력
        System.out.println(dist[END]);
        
//        System.out.println(Arrays.toString(parents));
        List<Integer> path = new ArrayList<Integer>();
        int no = END;
        path.add(END);
        while(parents[no] != 0) {
        	int x = parents[no];
        	path.add(x);
        	no = x;
        }
        System.out.println(path.size());
        
        for (int i = path.size()-1; i >= 0; i--) {
			System.out.print(path.get(i)+" ");
		}
        
        
    }

}


