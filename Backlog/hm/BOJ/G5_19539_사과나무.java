import java.util.*;
import java.io.*;

public class G5_19539_사과나무 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sum=0, one = 0, two = 0, x;
        for(int i=0;i<N;i++) {
            x = Integer.parseInt(st.nextToken());
            sum += x;
            two += x/2;
            one += x%2;
        }

        if(sum%3 != 0 || one > two)	System.out.println("NO");
        else System.out.println("YES");
    }

}
