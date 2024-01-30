import java.io.*;
import java.util.*;

public class G3_1865_웜홀 {

    static int TC, N, M, W;
    static int[] dist;
    static ArrayList<ArrayList<Road>> road;
    static final int INF = 999999999;
    static class Road{
        int end;
        int weight;
        Road(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        for(int tc = 0;tc<TC;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            dist = new int[N+1];
            road = new ArrayList<>();
            for(int i=0;i<=N;i++) {
                road.add(new ArrayList<>());
            }

            for(int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                road.get(start).add(new Road(end, weight));
                road.get(end).add(new Road(start, weight));
            }

            for(int i=0;i<W;i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                road.get(start).add(new Road(end, -weight));
            }

            boolean ans = false;
            for(int i=1;i<=N;i++) {
                if(bellmanFord(i)) {
                    ans = true;
                    System.out.println("YES");
                    break;
                }
            }

            if(!ans) System.out.println("NO");
        }
    }

    public static boolean bellmanFord(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0; // 시작점 초기화
        boolean update = false;

        // 모든 정점에 최단거리 초기화를 함
        for(int i=1;i<N;i++) {
            update = false;

            // 최단거리 초기화
            // 정점의 개수만큼 반복
            for(int j=1;j<=N;j++) {
                // 간선의 개수만큼 반복
                for(Road r : road.get(j)) {
                    if(dist[j] != INF && dist[r.end] > dist[j]+r.weight) {
                        dist[r.end] = dist[j] + r.weight; // 최단거리 갱신
                        update = true;
                    }
                }
            }

            // 더 이상 최단거리 초기화가 일어나지 않았을 경우
            if(!update) break;
        }

        // 마지막을 제외한 모든 정점에 계속 업데이트가 발생했었다면
        // 마지막의 정점도 업데이트 발생하면 음수 사이클이 일어남
        if(update) {
            for(int i=1;i<=N;i++) {
                for(Road r : road.get(i)) {
                    if(dist[i] != INF && dist[r.end] > dist[i] + r.weight)
                        return true;
                }
            }
        }

        return false;
    }

}
