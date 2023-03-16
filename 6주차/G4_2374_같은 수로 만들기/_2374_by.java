package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/* G4. 같은 수로 만들기 */
public class _2374 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long ans = 0;
        int n = Integer.parseInt(br.readLine()); // n개의 자연수
        Stack<Integer> st = new Stack<>();
        int[] a = new int[n];
        for(int i=0; i<n; i++){
            a[i] = Integer.parseInt(br.readLine());
        }

        if(n==1) {
            System.out.println(0);
            return;
        }

        st.add(a[0]);
        int max = a[0];
        for(int i=1; i<n; i++){
            int cur = a[i];
            max = Math.max(cur, max);
            if(st.peek() > cur){
                st.pop();
                st.add(cur);
            }else if(st.peek() == cur){ // 같은 구역
                continue;
            } else{
                ans += cur - st.pop();
                st.add(cur);
            }
        }
        ans += max - st.pop();

        System.out.println(ans);
    }
}
