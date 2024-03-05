import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[] height;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        height = new int[N];

        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            sum += height[i];
        }

        int cnt = 0;
        for (int i = 0; i < height.length; i++) {
            while ((height[i] >= 2) && (sum != cnt) ) {
                height[i] -= 2;
                sum -= 2;
                cnt++;
            }
        }

        if (sum == cnt) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
