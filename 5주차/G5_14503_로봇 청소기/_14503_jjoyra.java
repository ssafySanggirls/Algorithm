package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503_조희라 {
    static class Robot {
        int r, c, dir;
        public Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Robot robot;
    static int N, M, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        robot = new Robot(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
//              System.out.println(robot.r + " " + robot.c + " " + robot.dir);

            // 1. 현재 칸이 청소되지 않은 경우, 청소
            if(map[robot.r][robot.c] == 0) {
                answer++;
                map[robot.r][robot.c] = 2;
            }

//            System.out.println(robot.r + " " + robot.c + " " + robot.dir);
//            for(int i  = 0; i < N; i++) {
//                for(int j = 0; j < M; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            // 현재 칸의 주변 4칸 중 청소할 수 있는 칸이 있는지 검사
            boolean flag = false;
            for(int i = 0; i < 4; i++) {
                int nr = robot.r + dr[i];
                int nc = robot.c + dc[i];

                if(nr > 0 && nr < N - 1 && nc > 0 && nc < M - 1) {
                    if(map[nr][nc] == 0) {
                        flag = true;
                        break;
                    }
                }
            }

//            System.out.println(flag);

            // 청소할 수 있는 칸이 있는 경우
            if(flag) {
                // 회전
                robot.dir = robot.dir - 1 == -1 ? 3 : robot.dir - 1;
//                System.out.println(robot.dir);
                int nr = robot.r + dr[robot.dir];
                int nc = robot.c + dc[robot.dir];

                // 앞 칸이 청소되지 않은 칸일 경우 전진
                if(nr > 0 && nr < N - 1 && nc > 0 && nc < M - 1 && map[nr][nc] == 0) {
                    robot.r = nr;
                    robot.c = nc;
                }
            } else {
                int backDir = robot.dir >= 2 ? robot.dir - 2: robot.dir + 2;
                int nr = robot.r + dr[backDir];
                int nc = robot.c + dc[backDir];

                //후진 할 수 있으면 후진
                if(nr > 0 && nr < N - 1 && nc > 0 && nc < M - 1 && map[nr][nc] != 1) {
                    robot.r = nr;
                    robot.c = nc;
//                System.out.println("hi");
//                System.out.println(robot.r + " " + robot.c + " " + robot.dir);


                } else break; // 후진할 수 없으면 종료
            }
        }

        System.out.println(answer);
    }

}
