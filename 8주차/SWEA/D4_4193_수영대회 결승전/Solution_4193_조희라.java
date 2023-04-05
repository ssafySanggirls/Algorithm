package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_4193_조희라 {
    static int A, B, C, D, N, time;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            time = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            C = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            bfs();

            System.out.println("#" + t + " " + time);
        }
    }

    static void bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N]; // 방문 체크

        queue.offer(new int[]{A, B}); // 시작점 큐에 집어넣음
        visited[A][B] = true;

        while(!queue.isEmpty()) {
            int size = queue.size(); // 현재 큐 사이즈로 레벨

            for(int i = 0 ; i < size; i++) { // 큐 사이즈만큼 poll
                int[] tmp = queue.poll();
                // 레벨 사용 time count

                if (tmp[0] == C && tmp[1] == D) return; // 도착점에 도착하면 return

                if (map[tmp[0]][tmp[1]] == 2 && time % 3 != 0) { // 해당 지점이 소용돌이면서 사라지지 않았을 경우
                    queue.offer(tmp); // 큐에 삽입
                    continue;
                }

                for (int j = 0; j < 4; j++) { // 4방 탐색
                    int nr = tmp[0] + dr[j];
                    int nc = tmp[1] + dc[j];

                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        if (map[nr][nc] != 1 && !visited[nr][nc]) { // 다음 지점이 섬이 아니고 방문하지 않았을 경우
                            queue.offer(new int[]{nr, nc}); // 큐에 삽입
                            visited[nr][nc] = true;
                        }
                    }
                }

            }
            time++;

        }

        time = -1;
    }

}
