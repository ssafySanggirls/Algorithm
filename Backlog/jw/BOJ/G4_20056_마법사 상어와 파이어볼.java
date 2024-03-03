import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

    static int N, M, K;
    static ArrayList<Integer>[] map;
    static ArrayList<Fireball> fireballs;
    static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N * N];

        fireballs = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireballs.add(new Fireball(r - 1, c - 1, m, s, d));
        }

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N * N; j++) {
                map[j] = new ArrayList<>();
            }

            move();
        }

        System.out.println(getSum());
    }

    static void move() {
        for (int i = fireballs.size() - 1; i >= 0; i--) {
            Fireball f = fireballs.get(i);
            // 이동
            f.r += dr[f.d] * f.s;
            f.c += dc[f.d] * f.s;
            f.r = check(f.r);
            f.c = check(f.c);

            map[f.r * N + f.c].add(i);
        }

        ArrayList<Integer> fireballIdx = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            if (map[i].size() >= 2) {
                int sumM = 0;
                int sumS = 0;
                int even = 0;
                int odd = 0;
                int row = 0;
                int col = 0;
                for (int idx : map[i]) {
                    Fireball f = fireballs.get(idx);
                    sumM += f.m;
                    sumS += f.s;
                    if (f.d % 2 == 0) {
                        even++;
                    } else {
                        odd++;
                    }
                    row = f.r;
                    col = f.c;
                    fireballIdx.add(idx);
                }


                int mass = sumM / 5;
                int speed = sumS / map[i].size();

                int dir = 1;
                if (even == 0 || odd == 0) {
                    dir = 0;
                }

                if (mass == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++, dir += 2) {
                    fireballs.add(new Fireball(row, col, mass, speed, dir));
                }
            }
        }
        // 파이어볼 하나로 합쳐진다.
        Collections.sort(fireballIdx, Collections.reverseOrder());
        for (int idx : fireballIdx) {
            fireballs.remove(idx);
        }
    }

    static int check(int point) {
        while (point < 0) {
            point += N;
        }

        return point % N;
    }

    static int getSum() {
        int sum = 0;
        for (Fireball f : fireballs) {
            sum += f.m;
        }

        return sum;
    }
}

class Fireball {
    int r;
    int c;
    int m;
    int s;
    int d;

    public Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}
