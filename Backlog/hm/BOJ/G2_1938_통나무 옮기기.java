import java.io.*;
import java.util.*;

public class G2_1938_통나무 옮기기 {

    static int N; //평지의 길이를 저장하는 변수
    static char[][] map; //평지의 상태를 저장하는 배열
    static Node[] start, end; //처음 위치를 저장하는 리스트  start, 최종 위치를 저장하는 리스트 end
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1}; // U, D, L, R - 이동, 탐색을 위한 배열

    static class Node{
        // BBB또는 EEE의 위치를 저장할 수 있는 클래스
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 통나무 중간의 위치, 방향, 굴린 횟수 등 넣을 구조체
    static class Wood{
        int x, y, dir, dist;

        Wood(int x, int y, int dir, int dist){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //주어진 평지의 한 변의 길이
        map = new char[N][N];
        start = new Node[3]; // 통나무 길이는 항상 3
        end = new Node[3]; // 마지막 위치

        // -- 평지의 상태를 입력 받는다
        int sIdx = 0, eIdx = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] =='B') {
                    //평지가 B라면
                    start[sIdx++] = new Node(i, j);
                }else if(map[i][j] =='E') {
                    //평지가  E라면
                    end[eIdx++] = new Node(i, j);
                }
            }
        }

        int ans = bfs(); //최소 동작 횟수 구하는 함수
        System.out.println(ans);
    }

    private static int bfs() { // BBB로부터 EEE의 위치 까지 가는데 걸리는 최소 동작횟수 출력
        Queue<Wood> q = new LinkedList<>(); //bfs에 사용할 큐
        boolean[][][] visited = new boolean[2][N][N]; // 방문체크 배열 -> 가로인 경우[0][N][N], 세로인 경우 나눠서[1][N][N]

        // 통나무 상태 확인
        int dir = 0;
        if(start[0].y + 1 == start[1].y) {
            dir = 0; // 가로 방향
        } else {
            dir = 1; // 세로 방향
        }

        // 통나무의 중간을 기준으로 이동함
        q.add(new Wood(start[1].x, start[1].y, dir, 0));
        visited[dir][start[1].x][start[1].y] = true;

        while(!q.isEmpty()) {

            Wood w = q.poll();
            int wx = w.x;
            int wy = w.y;
            int wdir = w.dir;
            int wdist = w.dist;

            // 종료 조건
            // 통나무 중간이 목적지의 중간에 도달함
            if(wx == end[1].x && wy == end[1].y) {
                // 가로방향
                if(wdir == 0 && map[wx][wy-1] == 'E' && map[wx][wy+1] == 'E') {
                    return wdist;
                }
                // 세로 방향
                else if(wdir == 1 && map[wx-1][wy] == 'E' && map[wx+1][wy] == 'E') {
                    return wdist;
                }
            }

            // UDLR 탐색
            for(int d=0;d<4;d++) {
                int nx = wx + dx[d];
                int ny = wy + dy[d];

                // 이동가능한지 체크
                if(!checkMove(wdir, nx, ny, d)) continue;

                if(visited[wdir][nx][ny]) continue;
                visited[wdir][nx][ny] = true;
                q.add(new Wood(nx, ny, wdir, wdist+1));
            }

            // T 탐색
            if(checkRotate(wx, wy)) {
                // 가로, 수평
                if(wdir == 0 && !visited[1][wx][wy]) {
                    visited[1][wx][wy] = true;
                    q.add(new Wood(wx, wy, 1, wdist+1));
                }
                else if(wdir == 1 && !visited[0][wx][wy]) {
                    visited[0][wx][wy] = true;
                    q.add(new Wood(wx, wy, 0, wdist+1));
                }
            }
        }
        return 0;
    }

    private static boolean checkRotate(int x, int y) {
        // 회전할 때 범위가 나가거나 나무가 있는지
        for(int i=x-1; i<=x+1; i++) {
            for(int j=y-1; j<=y+1; j++) {
                if(i<0 || j<0 || i>=N || j>=N) return false;
                if(map[i][j] == '1') return false;
            }
        }

        return true;
    }

    private static boolean checkMove(int dir, int x, int y, int d) {
        switch(dir) {
            case 0:
                if(d<2) {	// 상하로 이동
                    if(x<0 || x>=N) return false;
                    // 나무가 있으면 false
                    if(map[x][y] == '1' || map[x][y-1] == '1' || map[x][y+1] == '1') return false;
                }
                else { // 좌우
                    if(y-1<0 || y+1 >=N) return false;
                    // 나무가 있으면 false
                    if(map[x][y] == '1' || map[x][y-1] == '1' || map[x][y+1] == '1') return false;
                }
                break;
            case 1:
                if(d<2) {	// 상하로 이동
                    if(x-1 <0 || x+1>=N) return false;
                    // 나무가 있으면 false
                    if(map[x][y] == '1' || map[x-1][y] == '1' || map[x+1][y] == '1') return false;
                }
                else { // 좌우
                    if(y<0 || y >=N) return false;
                    // 나무가 있으면 false
                    if(map[x][y] == '1' || map[x-1][y] == '1' || map[x+1][y] == '1') return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

}