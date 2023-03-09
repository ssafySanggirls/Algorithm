package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17391_조희라2 {
    static int N, M;
    static int[][] minCount, map;
    static boolean[][] visited;
    static int[] dr = {0, 1};
    static int[] dc = {1, 0};
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        minCount = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                minCount[i][j] = INF;
            }
        }

        bfs();

        System.out.println(minCount[N - 1][M - 1]);


    }

    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0, map[0][0]});
        minCount[0][0] = 0;

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            if(visited[tmp[0]][tmp[1]]) continue;
            visited[tmp[0]][tmp[1]] = true;
            if(tmp[0] == N - 1 && tmp[1] == M - 1) return;

            for(int i = 0; i < 2; i++) {
                int nr = tmp[0];
                int nc = tmp[1];

                for(int j = 0; j < tmp[2]; j++) {
                    nr += dr[i];
                    nc += dc[i];

                    if(nr >= 0 && nr < N && nc >= 0 && nc < M
                            && minCount[nr][nc] > minCount[tmp[0]][tmp[1]] + 1) {
                        minCount[nr][nc] = minCount[tmp[0]][tmp[1]] + 1;
                        queue.offer(new int[] {nr, nc, map[nr][nc]});
                    }
                }
            }
        }



    }

}


