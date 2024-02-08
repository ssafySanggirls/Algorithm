import java.io.*;
import java.util.*;

public class G1_1208_부분수열의 합2 {

    static int N, S;
    static int[] arr;
    static List<Integer> left, right;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        left = new ArrayList<>();
        right = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 부분수열의 합 구하기
        func(0, N/2, 0, left);
        func(N/2, N, 0, right);

        Collections.sort(left);
        Collections.sort(right);

        // Sum이 되는지 확인
        long ans = add();

        // 0(left) + 0(right) = 0(S)인 경우를 빼줘야 함
        if(S==0) System.out.println(ans-1);
        else System.out.println(ans);
    }

    private static long add() {
        int l = 0;
        int r = right.size()-1;
        long ans=0;

        while(l < left.size() && r >= 0) {
            long sum = left.get(l) + right.get(r);

            if(sum == S) {
                long cntL=0, cntR=0;
                long valueL = left.get(l), valueR = right.get(r);

                while(l<left.size() && left.get(l) == valueL) {
                    // 왼쪽에 같은 수
                    cntL++;
                    l++;
                }

                while(r>=0 && right.get(r) == valueR) {
                    // 오른쪽에 같은 수
                    cntR++;
                    r--;
                }
                ans += cntL*cntR;

            }else if(sum < S) {
                l++;
            }else { // sum>S
                r--;
            }
        }

        return ans;
    }

    private static void func(int cnt, int end, int sum, List<Integer> list) {
        if(cnt == end) {
            list.add(sum);
            return;
        }

        func(cnt+1, end, sum+arr[cnt], list);
        func(cnt+1, end, sum, list);
    }

}
