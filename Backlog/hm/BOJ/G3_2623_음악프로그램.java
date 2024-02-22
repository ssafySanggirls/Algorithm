import java.io.*;
import java.util.*;

public class G3_2623_음악프로그램 {

    static int N, M;
    static int[] line; // 간선
    static ArrayList<Integer> list[], answer;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        answer = new ArrayList<>();
        line = new int[N+1];

        for(int i=0;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            for(int j=1;j<x;j++) {
                int end = Integer.parseInt(st.nextToken());
                list[start].add(end);
                line[end]++; // 간선 개수를 늘려줌
                start = end;
            }
        }

        bfs();
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        // 들어오는 간선이 없는 노드 추가
        for(int i=1;i<=N;i++) {
            if(line[i]==0) q.offer(i);
        }
        sb = new StringBuilder();

        while(!q.isEmpty()) {
            int x = q.poll();
            answer.add(x);

            for(int y : list[x]) {
                line[y]--; // 방문했으니 들어가는 간선 하나 줄어듬
                if(line[y] == 0) q.offer(y);
            }
        }

        if(answer.size() != N) System.out.println(0);
        else {
            for(int x : answer) sb.append(x).append("\n");
            System.out.println(sb.toString());
        }
    }

}
