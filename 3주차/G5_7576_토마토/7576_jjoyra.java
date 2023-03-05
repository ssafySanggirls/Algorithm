package boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_jjoyra {
    static int M, N, cnt;
    static int[][] arr;
    static int[][] checked;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        checked = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();


        if(isFerment()) System.out.println(cnt);
        else System.out.println(-1);

    }

    static void bfs() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 1) queue.offer(new int[]{i, j});
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            while(size-- != 0) {
                int[] tmp = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int tx = tmp[0] + dx[k];
                    int ty = tmp[1] + dy[k];
                    if (tx >= 0 && tx < N && ty >= 0 && ty < M && arr[tx][ty] == 0) {
                        arr[tx][ty] = 1;
                        queue.offer(new int[]{tx, ty});
                    }
                }
            }
            if(!queue.isEmpty()) cnt++;

        }
    }

    static boolean isFerment() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

}

