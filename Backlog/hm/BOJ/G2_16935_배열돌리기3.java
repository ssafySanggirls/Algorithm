import java.util.*;
import java.io.*;

public class G2_16935_배열돌리기3 {

    static int N, M, R, NUM;
    static int[][] map;
    static int[] cal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cal = new int[R];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<R;i++) {
            cal[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<R;i++) {
            switch(cal[i]) {
                case 1:
                    func1();
                    break;
                case 2:
                    func2();
                    break;
                case 3:
                    func3(true);
                    break;
                case 4:
                    func3(false);
                    break;
                case 5:
                    func4(true);
                    break;
                case 6:
                    func4(false);
                    break;
            }
        }

        print();
    }

    private static void func4(boolean isRight) {
        int[][] copy = new int[N][M];
        int [][] from, to;
        int halfN = N/2;
        int halfM = M/2;

        if(isRight) {
            from = new int[][] {{0, 0}, {0, halfM}, {halfN, halfM}, {halfN, 0}};
            to = new int[][] {{0, halfM}, {halfN, 0}, {0, -halfM}, {-halfN, 0}};
        }else {
            from = new int[][] {{0, 0}, {halfN, 0}, {halfN, halfM}, {0, halfM}};
            to = new int[][] {{halfN, 0}, {0, halfM}, {-halfN, 0}, {0, -halfM}};
        }

        for(int d=0;d<4;d++) {
            for(int i=from[d][0]; i<from[d][0]+halfN; i++) {
                for(int j=from[d][1]; j<from[d][1]+halfM; j++) {
                    copy[i+to[d][0]][j+to[d][1]] = map[i][j];
                }
            }
        }

        map = copy;
    }

    private static void func3(boolean isRight) {
        int[][] copy = new int[M][N];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(isRight) copy[j][N-i-1] = map[i][j];
                else copy[M-j-1][i] = map[i][j];
            }
        }

        map = copy;
        int tmp = M;
        M = N;
        N = tmp;

    }

    private static void func2() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<M/2;j++) {
                int tmp = map[i][j];
                map[i][j] = map[i][M-j-1];
                map[i][M-j-1] = tmp;
            }
        }

    }

    private static void func1() {
        for(int j=0;j<M;j++) {
            for(int i=0;i<N/2;i++) {
                int tmp = map[i][j];
                map[i][j] = map[N-i-1][j];
                map[N-i-1][j] = tmp;

            }
        }

    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                sb.append(map[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
