import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, K;
    static int[] A;
    static boolean[] isRobot;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N * 2];
        isRobot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        while (check()) {
            cnt++;
            rotate();
            moveRobot();
            addRobot();
        }

        System.out.println(cnt);
    }

    static void rotate() {
        // 벨트 회전
        int temp = A[A.length - 1];
        for (int i = A.length - 1; i >= 1; i--) {
            A[i] = A[i - 1];
        }
        A[0] = temp;

        // 로봇 회전
        for (int i = N - 1; i >= 1; i--) {
            isRobot[i] = isRobot[i - 1];
        }
        isRobot[N - 1] = false;
        isRobot[0] = false;
    }

    static void moveRobot() {
        for (int i = N - 1; i >= 1; i--) {
            if (!isRobot[i] && A[i] >= 1 && isRobot[i - 1]) {
                isRobot[i] = true;
                isRobot[i - 1] = false;
                // 내구도 감소
                A[i]--;
            }
        }
    }

    static void addRobot() {
        if (A[0] >= 1) {
            isRobot[0] = true;
            // 내구도 감소
            A[0]--;
        }
    }

    static boolean check() {
        int cnt = 0;

        for (int i = 0; i < N * 2; i++) {
            if (A[i] == 0) {
                cnt++;
            }
            if (cnt >= K) {
                return false;
            }
        }

        return true;
    }

}
