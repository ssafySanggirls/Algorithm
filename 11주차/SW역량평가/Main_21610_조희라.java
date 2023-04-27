package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_21610_조희라 {
    static int N, M, answer;
    static int[][] map, moveArr, cMap;
    static boolean[][] cloudMap;

    static class Cloud {
        int r, c;

        public Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    static Queue<Cloud> queue = new ArrayDeque<>();

    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        cMap = new int[N][N];
        moveArr = new int[M][2];
        cloudMap = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            moveArr[i][0] = Integer.parseInt(st.nextToken());
            moveArr[i][1] = Integer.parseInt(st.nextToken());
        }

        queue.add(new Cloud(N - 1, 0));
        queue.add(new Cloud(N - 1, 1));
        queue.add(new Cloud(N - 2, 0));
        queue.add(new Cloud(N - 2, 1));

        for(int i = 0; i < M; i++) {
            moveAndRain(i);
            waterMagic();
            afterMagic();
        }

        countWater();
        System.out.println(answer);

    }

    static void moveAndRain(int cnt) {
        for(int i = 0; i < queue.size(); i++) {
            int dir = moveArr[cnt][0] - 1;
            int s = moveArr[cnt][1];

            int nr = queue.peek().r + dr[dir] * s;

            if(nr > 0) nr %= N;
            else if(nr < 0) nr = (100 * N + nr) % N;

            int nc = queue.poll().c + dc[dir] * s;

            if(nc > 0) nc %= N;
            else if(nc < 0) nc = (100 * N + nc) % N;

            map[nr][nc]++;
            queue.offer(new Cloud(nr, nc));
            cloudMap[nr][nc] = true;
        }

    }


    static void waterMagic() {
        copyMap(map, cMap);
        while(!queue.isEmpty()) {
            Cloud cloud = queue.poll();
            int cnt = 0;
            for(int i = 0; i < 4; i++) {
                int nr = cloud.r + dr[i * 2 + 1];
                int nc = cloud.c + dc[i * 2 + 1];

                if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] > 0)
                    cnt++;
            }

            cMap[cloud.r][cloud.c] += cnt;
        }

        copyMap(cMap, map);

    }

    static void afterMagic() {

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] >= 2 && !cloudMap[i][j]) {
                    queue.offer(new Cloud(i, j));
                    map[i][j] -= 2;
                } else if(cloudMap[i][j]) cloudMap[i][j] = false;
            }
        }


    }

    static void countWater() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                answer += map[i][j];
            }
        }
    }

    static void debug() {
        for(int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    static void copyMap(int[][] map, int[][] cMap) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cMap[i][j] = map[i][j];
            }
        }
    }

}
