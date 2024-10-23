import java.io.*;
import java.util.*;

class Main {

    static int N, M, L;
    static int[] dis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[] point = new int[N + 1];

        if (N > 0) {
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                point[i] = Integer.parseInt(st.nextToken());
            }
        }

        point[N] = L;

        Arrays.sort(point);

        dis = new int[N + 1];
        int before = 0;
        for (int i = 0; i < N + 1; i++) {
            dis[i] = point[i] - before;
            before = point[i];
        }

        int left = 1;
        int right = L - 1;
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid) <= M) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);

    }

    public static int check(int mid) {
        int cnt = 0;
        for (int i = 0; i < N + 1; i++) {
            cnt += (dis[i] - 1) / mid;
        }

        return cnt;
    }
}
