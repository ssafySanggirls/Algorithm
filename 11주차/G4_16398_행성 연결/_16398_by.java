package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// G4. 행성 연결
public class _16398 {

    static int n, price[][], minEdge[], visited[];
    static long ans; // mst 비용

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 행성의 수
        price = new int[n][n]; // 각 행성간의 플로우 관리 비용
        minEdge = new int[n]; // 타정점에서 자신으로의 간선 비용 중 최소 비용 저장
        StringTokenizer st = null;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
            minEdge[i] = Integer.MAX_VALUE; // 최소로 갱신하기 위한 초기값 세팅
        }

        visited = new int[n]; // 신장트리에 선택되었는지 여부를 기록하기 위한 배열
        mst(); // 최소신장트리 - prim 알고리즘 시작

        System.out.println(ans); // 출력
    }

    public static void mst() {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(); // 간선 비용이 낮은 순서대로 정렬하게 만들 pq
        int nodeCount = 0; // 선택된 노드의 개수
        minEdge[0] = 0; // 임의의 시작점의 비용을 0으로 두고 출발
        pq.offer(new Vertex(0, 0));

        while(!pq.isEmpty()) {
            Vertex minVertex = pq.poll(); // pq에서 간선비용이 최소인 정점 뽑기
            // 이미 최소 신장 트리에 포함된 정점은 패스
            if(visited[minVertex.no] == 1) continue;
            ans += minVertex.weight; // 최소 간선 비용 저장
            visited[minVertex.no] = 1; // 방문 체크

            if(++nodeCount == n) break; // node 개수가 n이면 최소 신장 트리 완성

            // 선택된 정점을 기준으로 신장트리에 포함되지 않은 다른 정점으로의 간선 비용을 따져보고 최솟값을 갱신
            for(int i=0; i<n; i++) {
                // 방문한적 없고 갈 수 있는 길이고 (자기자신이 아니고) 최소보다 작다면
                if(visited[i] == 0 && price[minVertex.no][i] != 0 && minEdge[i] > price[minVertex.no][i]) {
                    minEdge[i] = price[minVertex.no][i]; // 최솟값 갱신
                    pq.offer(new Vertex(i, price[minVertex.no][i]));
                }
            }
        }
    }

    static class Vertex implements Comparable<Vertex>{
        int no; // 정점 번호
        int weight; // 간선 비용
        public Vertex(int no, int weight) {
            super();
            this.no = no;
            this.weight = weight;
        }
        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight); // 오름차순
        }
    }
}