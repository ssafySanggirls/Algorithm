import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n];
        long answer = 0;
        long max = 0;

        for (int i = 0; i < n; i++) {
            A[i] = Long.parseLong(br.readLine());
            max = Math.max(max, A[i]);
        }

        Stack<Long> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(A[i]);
            } else {
                if (stack.peek() < A[i]) {
                    answer += A[i] - stack.pop();
                    stack.push(A[i]);
                } else if (stack.peek() > A[i]) {
                    stack.pop();
                    stack.push(A[i]);
                }
            }
        }

        while (!stack.isEmpty()) {
            answer += max - stack.pop();
        }

        System.out.println(answer);
    }
}
