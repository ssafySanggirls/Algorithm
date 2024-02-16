import java.io.*;
import java.util.*;

public class G4_8983_사냥꾼 {

    static int M, N;
    static long L;
    static int[] arr;
    static int[][] animal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());
        arr = new int[M];
        animal = new int[N][2];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            animal[i][0] = Integer.parseInt(st.nextToken());
            animal[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long cnt=0;
        for(int i=0;i<N;i++) { // 동물들을 기준으로 탐색
            int left = 0, right = M-1;

            while(left<=right) {
                int idx = (left + right) / 2;
                int x = arr[idx]; // 중간에 있는 사대
                long len = Math.abs(x-animal[i][0]) + animal[i][1];

                if(L >= len) {
                    cnt++;
                    break;
                }

                if(animal[i][0] < x) right = idx-1;
                else left = idx+1;
            }
        }
        System.out.println(cnt);
    }
}
