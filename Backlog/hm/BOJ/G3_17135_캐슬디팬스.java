import java.io.*;
import java.util.*;

public class G3_17135_캐슬디팬스 {

    static int N, M, D;
    static int[][] map;
    static int[] mans;
    static int ans;
    static ArrayList<int[]> list;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한
        map = new int[N][M];
        mans = new int[3];
        list = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    list.add(new int[] {i, j});
                }
            }
        }

        ans=0;
        func(0, 0);
        System.out.println(ans);

    }


    private static void func(int cnt, int start) {
        // 1. 궁수 배치
        if(cnt == 3) {
            // 복사
            List<int[]> tmp = new ArrayList<>();
            for(int i=0;i<list.size();i++) {
                int[] arr = list.get(i);
                tmp.add(new int[] {arr[0], arr[1]});
            }

            attack(tmp); // 2. 궁수 공격
            return;
        }
        for(int i=start;i<M;i++) {
            mans[cnt] = i;
            func(cnt+1, i+1);
        }
    }


    private static void attack(List<int[]> tmp) {
        // 2. 궁수 공격
        int cnt=0;

        while(true) {
            if(tmp.isEmpty()) break; //종료조건
            List<int[]> targets = new ArrayList<>(); // 궁수들이 잡는 적들 리스트

            for(int i=0;i<3;i++) { // 각 궁수들이 잡을 수 있는 적들을 찾음
                Queue<Node> q = new PriorityQueue<>();

                for(int j=0;j<tmp.size();j++) {
                    // 적들의 목록
                    int[] arr = tmp.get(j);
                    int dist = Math.abs(arr[0]-N) + Math.abs(arr[1]-mans[i]); // 궁수와 적의 거리 계산
                    if(dist <= D) {
                        q.add(new Node(arr[0], arr[1], dist));
                    }
                }

                if(!q.isEmpty()) {
                    // 잡을 수 있는 적이 있다면
                    Node n = q.poll(); // 가장 가깝고, 가장 왼쪽에 있는 것
                    boolean flag = false; // 다른 사람이 잡으려고 하는지
                    for(int j=0;j<targets.size();j++) {
                        int[] arr2 = targets.get(j);
                        if(n.x == arr2[0] && n.y == arr2[1]) {
                            flag = true; // 다른 사람이 잡으려고 함
                        }
                    }
                    if(!flag) {
                        targets.add(new int[] {n.x, n.y});
                    }
                }
            }

            // targets 리스트 애들 전부 제거
            for(int i=0;i<targets.size();i++) {
                for(int j=tmp.size()-1;j>=0;j--) {
                    if(targets.get(i)[0] == tmp.get(j)[0]
                            && targets.get(i)[1] == tmp.get(j)[1]) {
                        tmp.remove(j);
                        cnt++;
                        break;
                    }
                }
            }

            // 3. 적 아래로 이동
            for(int i=tmp.size()-1;i>=0;i--) {
                tmp.get(i)[0] +=1;
                if(tmp.get(i)[0] == N) {
                    tmp.remove(i);
                }
            }

            // 궁수가 공격으로 제거할 수 있는 적의 최대 수
            ans = Math.max(ans, cnt);
        }
    }

    public static class Node implements Comparable<Node>{
        int x, y, d;
        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            if(this.d == o.d) {
                return this.y-o.y; // 더 왼쪽에 있는 것
            }else {
                return this.d-o.d; // 거리순
            }
        }
    }
}
