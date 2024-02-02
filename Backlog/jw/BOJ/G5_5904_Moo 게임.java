import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> cnt = new ArrayList<>();

        cnt.add(0, 3);

        int k = 1;
        while (N > 3) {
            cnt.add(k, cnt.get(k - 1) * 2 + k + 3);
            if (cnt.get(k) >= N) {
                break;
            }
            k++;
        }

        while (true) {
            if (k - 1 < 0) {
                if (N == 1) {
                    sb.append("m");
                } else if (N == 2 || N == 3) {
                    sb.append("o");
                }
                break;
            } else if (N == cnt.get(k - 1) + 1) {
                sb.append("m");
                break;
            } else if (N <= cnt.get(k - 1) + k + 3) {
                sb.append("o");
                break;
            } else {
                N -= cnt.get(k - 1) + k + 3;
                k = 0;
                while (N >= cnt.get(k)) {
                    k++;
                }
            }
        }

        System.out.println(sb);
    }
}
