package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// G4. 특정한 최단 경로
public class _1504 {

    static int n, e, INF;
    static List<Node>[] list;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        INF = 10000000; // 최대 거리
        // 입력
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        e = Integer.parseInt(st.nextToken()); // 간선의 개수
        list = new List[n+1]; // 간선 정보 저장 리스트
        for(int i=0; i<n+1; i++){ // 리스트 초기화
            list[i] = new ArrayList<>();
        }
        int a, b, c; // 입력받는 변수 선언
        for (int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // 정점1
            b = Integer.parseInt(st.nextToken()); // 정점2
            c = Integer.parseInt(st.nextToken()); // 거리
            list[a].add(new Node(b, c)); // a -> b
            list[b].add(new Node(a, c)); // b -> a : 양방향
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점1
        int v2 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점2

        // 로직
        int[] disStart = dijkstra(1); // 1부터의 최단 거리 저장
        int[] disV1 = dijkstra(v1); // v1부터의 최단 거리 저장
        int[] disV2 = dijkstra(v2); // v2부터의 최단 거리 저장
        // 1 -> v1 -> v2 -> n 와 1 -> v2 -> v1 -> n 비교
        int ans = Math.min(disStart[v1]+disV1[v2]+disV2[n], disStart[v2]+disV2[v1]+disV1[n]);
        if(ans >= INF) ans = -1; // 최대 거리 이상이면 갈 수 없는 곳이 있다는 뜻 -> -1

        // 출력
        System.out.println(ans);
    }
    static class Node{ // 정점 정보 저장
        int idx, cost; // 도착 정점과 비용
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static int[] dijkstra(int start){ // 다익스트라, start부터 모든 정점 방문까지의 최단 거리 반환
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));

        int dis[] = new int[n+1]; // start부터의 최단 거리 저장
        Arrays.fill(dis, INF);
        dis[start] = 0; // start부터 start의 거리는 0

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            // start부터 현재까지의 저장된 거리가 현재까지 온 비용보다 작다면
            // 이미 최소이므로 갱신할 필요 없음
            if(dis[now.idx] < now.cost) continue;

            // 현재에서 갈 수 있는 다음 노드들을 모두 살펴볼 예정
            for(Node next : list[now.idx]) {
                // 비용은 다음까지 가는 비용 + 현재까지 온 비용
                int cost = next.cost + dis[now.idx];
                // start부터 다음 노드의 저장된 거리가
                // start부터 현재를 거쳐 다음까지 가는 비용보다 크다면
                // 현재를 거쳐 다음까지 가는 게 최소 -> 최단거리 갱신
                if(dis[next.idx] > cost) {
                    dis[next.idx] = cost;
                    pq.add(new Node(next.idx, cost));
                }
            }
        }
        return dis;
    }
}
