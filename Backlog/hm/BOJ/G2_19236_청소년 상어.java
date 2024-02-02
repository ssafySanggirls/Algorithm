import java.io.*;
import java.util.*;

public class G2_19236_청소년 상어 {

    static int[][] map;
    static Fish[] fishs;
    static int ans=0;
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1}, dx={-1, -1, 0, 1, 1, 1, 0, -1};
    static class Fish{
        int num, dir, x, y, alive;
        Fish(int num, int dir, int x, int y, int alive){
            this.num = num;
            this.dir = dir;
            this.x = x;
            this.y = y;
            this.alive = alive;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[4][4];
        fishs = new Fish[17];
        for(int i=0;i<4;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1;
                fishs[num] = (new Fish(num, dir, i, j, 1));
                map[i][j] = num;
            }
        }

        // 상어 처음 이동
        int first = map[0][0];
        fishs[first].alive = 0; // 물고기 상태를 죽음으로
        map[0][0] = -1;

        dfs(0, 0, fishs[first].dir, first);

        System.out.println(ans);
    }

    private static void dfs(int sx, int sy, int sd, int eat) {
        ans = Math.max(ans, eat); // 이전에 먹은 물고기 번호 합과 max 비교

        // map 복사
        int[][] copyMap = new int[4][4];
        for(int i=0;i<4;i++) {
            System.arraycopy(map[i], 0, copyMap[i], 0, 4); // 깊은 복사
        }

        // fish 복사
        Fish[] copyFish = new Fish[fishs.length];
        for(int i=1;i<=16;i++) {
            copyFish[i] = new Fish(fishs[i].num, fishs[i].dir, fishs[i].x, fishs[i].y, fishs[i].alive);
        }

        // 물고기 이동
        moveFish();

        // 상어 이동
        for(int i=1;i<4;i++) {
            //4*4니까 3번 이동 가능
            int nx = sx + dx[sd]*i;
            int ny = sy + dy[sd]*i;

            if(nx<0 || ny<0 || nx>=4 || ny >=4 || map[nx][ny] == 0) continue;

            int eatFish = map[nx][ny];
            int nd = fishs[eatFish].dir;
            map[sx][sy] = 0;
            map[nx][ny] = -1;
            fishs[eatFish].alive = 0;

            dfs(nx, ny, nd, eat+eatFish);

            fishs[eatFish].alive = 1;
            map[nx][ny] = eatFish;
            map[sx][sy] = -1;
        }

        // map, fish 복구
        for(int i=0;i<4;i++) {
            System.arraycopy(copyMap[i], 0, map[i], 0, 4); // 깊은 복사
        }
        for(int i=1;i<=16;i++) {
            fishs[i] = new Fish(copyFish[i].num, copyFish[i].dir, copyFish[i].x, copyFish[i].y, copyFish[i].alive);
        }

    }

    public static void moveFish() {
        for(int i = 1; i < 17; i++) { //i는 현재 물고기의 번호
            if(fishs[i].alive == 0) { //죽은 물고기라면 넘김
                continue;
            }

            int cnt = 0;
            int dir = fishs[i].dir;//현재 i번째 물고기의 방향
            int nx = 0, ny = 0; //물고기가 이동할 칸의 x, y값

            while(cnt < 8) {
                dir %= 8; //방향 +1로 범위 넘어가는 걸 처리하기 위한 나머지 연산
                fishs[i].dir = dir; //방향 바꿨다면 바뀐 것 적용

                nx = fishs[i].x + dx[dir];
                ny = fishs[i].y + dy[dir];

                if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && map[nx][ny] != -1) {
                    if(map[nx][ny] == 0) { //이동할 위치가 빈칸일 경우
                        map[fishs[i].x][fishs[i].y] = 0; //기존 위치 빈칸으로
                        fishs[i].x = nx;
                        fishs[i].y = ny;
                        map[nx][ny] = i;
                    } else { //이동할 위치에 다른 물고기가 있을 경우
                        // 바꿀 물고기 위치 변경
                        int changeFish = fishs[map[nx][ny]].num;
                        fishs[changeFish].x = fishs[i].x;
                        fishs[changeFish].y = fishs[i].y;
                        map[fishs[changeFish].x][fishs[changeFish].y] = changeFish;

                        //현재 물고기 위치 변경
                        fishs[i].x = nx;
                        fishs[i].y = ny;
                        map[nx][ny] = i;
                    }
                    break;
                } else {
                    dir++;
                    cnt++;
                }
            }
        }
    }

}