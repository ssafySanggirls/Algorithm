import java.io.*;
import java.util.*;

public class G4_1477_휴게소 세우기 {

    static int N, M, L;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N+2];

        arr[0] = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N+1] = L;
        Arrays.sort(arr);

        int left = 1, right = L-1;
        while(left <= right) {
            int mid = (left+right)/2;
            int sum = 0;

            for(int i=1;i<arr.length;i++) {
                sum += (arr[i] - arr[i-1] -1) / mid;
                // 현재 휴게소 사이에 mid 기준으로 총 몇개의 휴게소를 설치할 수 있는지?
            }

            if(sum > M) left = mid+1; // 휴게소를 너무 많이 설치함
            else right = mid-1; // 휴게소를 너무 적게 설치함
        }
        System.out.println(left);
    }

}
