import java.io.*;
import java.util.*;

public class G4_2374_같은 수로 만들기 {

    static long N, max = 0L, ans = 0L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Stack<Long> stack = new Stack<>();

        for(int i=0;i<N;i++) {
            long x = Long.parseLong(br.readLine());
            max = Math.max(max, x);

            if(stack.isEmpty()) {
                stack.push(x);
            }else {
                if(stack.peek() < x) {
                    ans += x-stack.pop();
                    stack.push(x);
                }else if(stack.peek() > x) {
                    stack.pop();
                    stack.add(x);
                }
            }
        }

        while(!stack.isEmpty()) {
            long num = stack.pop();
            ans += max-num;
        }
        System.out.println(ans);
    }
}
