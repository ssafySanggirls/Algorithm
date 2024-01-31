import java.io.*;
import java.util.*;

public class G5_9205_맥주 마시면서 걸어가기 {

    static int T, N;
    static ArrayList<Node> list;
    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int tc=0;tc<T;tc++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();


            for(int i=0;i<N+2;i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new Node(x, y));
            }

            if(bfs()) sb.append("happy\n");
            else sb.append("sad\n");
        }
        System.out.println(sb);
    }

    public static boolean bfs() {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N+2];
        q.add(new Node(list.get(0).x, list.get(0).y));

        int ex = list.get(list.size()-1).x;
        int ey = list.get(list.size()-1).y;
        while(!q.isEmpty()) {
            Node n = q.poll();
            int x = n.x;
            int y = n.y;
            if(Math.abs(ex - x) + Math.abs(ey - y) <= 1000) return true;

            for(int i=1;i<N+1;i++) {
                if(!visited[i]) {
                    int nx = list.get(i).x;
                    int ny = list.get(i).y;

                    int dis = Math.abs(x - nx) + Math.abs(y - ny);
                    if(dis <= 1000) {
                        visited[i] = true;
                        q.add(new Node(nx, ny));
                    }
                }
            }
        }

        return false;
    }

}
