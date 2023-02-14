package silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 시간초과
 * -> StringBuilder로 해결
 */
public class _18115 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] aArr = new int[n];
        for(int i=0; i<n; i++) {
            aArr[i] = Integer.parseInt(st.nextToken());
        }
        Deque<Integer> result = new LinkedList<Integer>();
        result.add(1);
        int cnt = 2;
        for(int i=n-2; i>=0; i--) {
            int a = aArr[i];
            if(a == 1) {
                result.addFirst(cnt);
            }else if(a == 2) {
                int cFirst = result.pollFirst();
                result.addFirst(cnt);
                result.addFirst(cFirst);
            }else {
                result.addLast(cnt);
            }
            cnt++;
        }
        StringBuilder sb = new StringBuilder();
        while(!result.isEmpty()) {
            sb.append(result.poll()+" ");
        }
        System.out.println(sb.toString());
    }
}
