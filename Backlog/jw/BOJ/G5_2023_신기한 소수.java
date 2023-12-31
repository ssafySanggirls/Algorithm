import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        check("", 0);

        System.out.println(sb);
    }

    static void check(String primeNum, int cnt) {
        if (cnt == N) {
            sb.append(primeNum).append('\n');
        }

        for (int i = 1; i <= 9; i++) {
            if (isPrime(Integer.parseInt(primeNum + i))) {
                check(primeNum + i, cnt + 1);
            }
        }
    }

    static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
