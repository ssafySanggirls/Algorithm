import java.util.*;
import java.io.*;

public class G4_20056_마법사 상어와 파이어볼 {

    static int N, M, K;
    static Queue<FireBall>[][] map;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static List<FireBall> balls;

    static class FireBall{
        int r, c, m, s, d;
        public FireBall(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new LinkedList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new LinkedList<>();

            }
        }
        balls = new ArrayList<>();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            balls.add(new FireBall(r, c, m, s, d));
        }

        for(int i=0;i<K;i++) {
            move();
            check();
        }

        int sum = 0;
        for (FireBall f : balls) {
            sum += f.m;
        }
        System.out.println(sum);

    }

    private static void check() {
        // 2개 이상의 파이어볼이 있는지 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].size() >= 2) {
                    int mSum = 0, sSum=0;
                    int cntSum = map[i][j].size(); // 합쳐진 파이어볼의 개수
                    boolean odd = true, even = true;

                    // 파이어볼 합치기
                    while(!map[i][j].isEmpty()) {
                        FireBall f = map[i][j].poll();
                        mSum += f.m;
                        sSum += f.s;

                        if(f.d % 2 == 0) {
                            odd = false;
                        } else {
                            even = false;
                        }
                        balls.remove(f);
                    }

                    int nm = mSum/5;
                    if (nm == 0) continue;

                    int ns = sSum/cntSum;

                    if(odd | even) {
                        balls.add(new FireBall(i, j, nm, ns, 0));
                        balls.add(new FireBall(i, j, nm, ns, 2));
                        balls.add(new FireBall(i, j, nm, ns, 4));
                        balls.add(new FireBall(i, j, nm, ns, 6));
                    }else {
                        balls.add(new FireBall(i, j, nm, ns, 1));
                        balls.add(new FireBall(i, j, nm, ns, 3));
                        balls.add(new FireBall(i, j, nm, ns, 5));
                        balls.add(new FireBall(i, j, nm, ns, 7));
                    }

                }else {
                    map[i][j].clear();
                }

            }
        }

    }

    private static void move() {
        // 파이어볼이 이동
        for(FireBall f : balls) {
            //음수가 나올 수도 있으니까 +N하기
            f.r = (N + f.r + dx[f.d] * (f.s % N)) % N;
            f.c = (N + f.c + dy[f.d] * (f.s % N)) % N;

            map[f.r][f.c].add(f);
        }
    }

}
