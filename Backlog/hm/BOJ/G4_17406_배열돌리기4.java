import java.util.*;
import java.io.*;

public class G4_17406_배열돌리기4 {

    static int N, M, K, ans = Integer.MAX_VALUE;
    static int[][] map, cal;

    static int[][] selected;
    static boolean[] visited;
    static int[][] dxdy = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};//오른쪽, 아래, 왼쪽, 위

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cal = new int[K][3];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine()); // r c s
            cal[i][0] = Integer.parseInt(st.nextToken())-1;
            cal[i][1] = Integer.parseInt(st.nextToken())-1;
            cal[i][2] = Integer.parseInt(st.nextToken());
        }

        selected = new int[K][3];
        visited = new boolean[K];
        func(0);

        System.out.println(ans);
    }

    private static void func(int cnt) {
        if(cnt == K) {
            // 배열 연산 모두 수행
            int[][] copy = copyArr();
            for(int k=0; k<K; k++) {
                for(int j=0; j<selected[k][2]; j++) {
                    // 회전
                    copy = turn(copy, j, selected[k][0], selected[k][1], selected[k][2]);
                }
            }

            // 최소값 구하기
            ans = Math.min(ans, calMin(copy));

            return;
        }

        for(int i=0; i<K; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            selected[cnt] = cal[i];
            func(cnt+1);
            visited[i] = false;
        }

    }

    private static int[][] turn(int[][] copy, int i, int r, int c, int s) {
        int x = r-s+i;
        int y = c-s+i;
        int d = 0;

        int arr = copy[x][y];
        while(true) {
            if(d==4) break;

            int nx = x + dxdy[d][0];
            int ny = y + dxdy[d][1];

            if (nx < r-s+i || nx > r+s-i || ny < c-s+i || ny > c+s-i) {
                ++d;
                continue;
            }

            int tmp = arr;
            arr = copy[nx][ny];
            copy[nx][ny] = tmp;

            x = nx;
            y = ny;
        }

        return copy;
    }

    private static int calMin(int[][] copy) {

        int sum = Integer.MAX_VALUE;
        for(int i=0;i<N;i++) {
            int x = 0;
            for(int j=0;j<M;j++) {
                x += copy[i][j];
            }

            sum = Math.min(sum, x);
        }

        return sum;
    }

    private static int[][] copyArr() {
        int[][] arr = new int[N][M];
        for(int i=0;i<map.length; i++) {
            arr[i] = map[i].clone();
        }
        return arr;
    }

}
