package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// S1. 귀여운 라이언
public class _15565 {
    static int n, k, dolls[];
    static long ans;
    static List<Integer> ryanList;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 인형 개수
        k = Integer.parseInt(st.nextToken());
        dolls = new int[n];
        ryanList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());
            if(dolls[i] == 1) ryanList.add(i);
        }

        if(ryanList.size() < k) {
            System.out.println(-1);
            return;
        }

        findSet();
        System.out.println(ans);
    }

    private static void findSet() {
        int first = 0;
        int last = first + k - 1;
        ans = ryanList.get(last) - ryanList.get(first) + 1;

        for(int i=1; i<=ryanList.size()-k; i++) {
            long temp = ryanList.get(++last) - ryanList.get(++first) + 1;
            ans = Math.min(ans, temp);
        }

    }
}
