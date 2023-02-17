package silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class _11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=n; i++){
            q.add(i);
        }

        StringBuilder sb = new StringBuilder(); // 정답 저장
        sb.append("<");

        while(!q.isEmpty()){
            for(int i=0; i<k-1; i++){
                q.add(q.poll());
            }
            sb.append(q.poll());
            if(q.size() >= 1)
                sb.append(", ");
        }

        sb.append(">");
        System.out.println(sb.toString());
    }
}
