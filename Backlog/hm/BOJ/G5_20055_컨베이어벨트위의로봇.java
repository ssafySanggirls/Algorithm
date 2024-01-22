import java.util.*;
import java.io.*;


public class G5_20055_컨베이어벨트위의로봇 {

    static int N, K, ans=0;
    static int[] A;
    static boolean[] robot;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[2*N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2*N;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        func();
        System.out.println(ans);
    }

    private static void func() {
        while(true) {
            // 단계 증가
            ans++;

            // 벨트 회전
            int tmp = A[2*N-1];
            for(int i=2*N-1; i>0; i--) {
                A[i] = A[i-1];
            }
            A[0] = tmp;

            for(int i=N-1; i>0; i--) {
                robot[i] = robot[i-1];
            }
            robot[0] = false;
            robot[N-1] = false;

            // 로봇 이동
            for(int i=N-1; i>0; i--) {
                if(robot[i-1] && !robot[i] && A[i] > 0) {
                    A[i]--;
                    robot[i] = true;
                    robot[i-1] = false;
                }
            }

            // 로봇 올린다
            if(A[0] > 0) {
                robot[0] = true;
                A[0]--;
            }

            // 과정 종료
            if(stop()) break;
        }
    }

    private static boolean stop() {
        int cnt = 0;
        for(int i=0;i<2*N;i++) {
            if(A[i] == 0) cnt++;
            if(cnt >= K) return true;
        }
        return false;
    }
}