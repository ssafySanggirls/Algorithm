package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9012_jjoyra {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            char[] arr = br.readLine().toCharArray();
            int cnt = 0;

            for(int i = 0; i < arr.length; i++) {
                if(arr[i] == '(') {
                    cnt++;
                } else {
                    if(cnt == 0) {
                        cnt = -1;
                        break;
                    } else {
                        cnt--;    
                    }
                }
            }

            if(cnt == 0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
