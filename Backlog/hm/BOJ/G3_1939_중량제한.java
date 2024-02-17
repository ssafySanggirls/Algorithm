import java.io.*;
import java.util.*;

public class G3_1939_중량제한 {

    static int N, M, start, end;
    static ArrayList<Node>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i=0;i<N+1;i++) {
            list[i] = new ArrayList<>();
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            max = Math.max(max, c);
            min = Math.min(min, c);

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        int ans = 0; // 한번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값

        while(min<=max) {
            int mid = (min+max) / 2;

            if(bfs(mid)) {
                // start에서 end 까지 mid의 중량이 건널 수 있는지 확인
                min = mid+1;
                ans = mid;
            }else {
                max = mid-1;
            }
        }
        System.out.println(ans);
    }

    private static boolean bfs(int mid) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int x = q.poll();
            if(x == end) return true;

            for(int i=0;i<list[x].size();i++) {
                if(list[x].get(i).cost >= mid && !visited[list[x].get(i).x]) {
                    visited[list[x].get(i).x] = true;
                    q.add(list[x].get(i).x);
                }
            }
        }
        return false;
    }

    static class Node{
        int x, cost;
        Node(int x, int cost){
            this.x = x;
            this.cost = cost;
        }
    }

}
