package silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14888 {

    static int n, max, min;
    static int[] arr;
    static char[] op_char;
    static int[] op_num, visited;

    public static void main(String[] args) throws IOException{
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num != 0) {
                char ch = ' ';
                switch (i) {
                    case 0: // +
                        ch = '+';
                        break;
                    case 1: // -
                        ch = '-';
                        break;
                    case 2: // x
                        ch = '*';
                        break;
                    case 3: // %
                        ch = '/';
                        break;
                }
                for(int j=0; j<num; j++) {
                    sb.append(ch);
                }
            }
        }
        op_char = sb.toString().toCharArray();

        op_num = new int[n-1];
        visited = new int[n-1];
        perm(0);

        System.out.println(max);
        System.out.println(min);
    }

    static void perm(int cnt) {
        if(cnt == n-1) {
            oper();
            return;
        }

        for(int i=0; i<n-1; i++) {
            if(visited[i] == 1) continue;
            op_num[cnt] = i;
            visited[i] = 1;
            perm(cnt+1);
            visited[i] = 0;
        }
    }

    private static void oper() {
        int result = arr[0];
        for(int i=0; i<n-1; i++) {
            char op = op_char[op_num[i]];
            switch (op) {
                case '+':
                    result += arr[i+1];
                    break;
                case '-':
                    result -= arr[i+1];
                    break;
                case '*':
                    result *= arr[i+1];
                    break;
                case '/':
                    result /= arr[i+1];
                    break;
            }
        }

        max = Math.max(result, max);
        min = Math.min(result, min);
    }
}
