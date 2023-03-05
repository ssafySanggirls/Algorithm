package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14501_jjoyra {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int t[] = new int[N];
        int p[] = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        
        int dpArr[] = new int[N + 1];
        
        for(int i = 0; i < N; i++) {
        	if(i + t[i] <= N) {
        		dpArr[i + t[i]] = Math.max(dpArr[i + t[i]], dpArr[i] + p[i]);
        		
        		for(int j = i + t[i] + 1; j <= N; j++) 
        			dpArr[j] = Math.max(dpArr[i + t[i]], dpArr[j]);
        	}
        }   
        
        Arrays.sort(dpArr);
        
        System.out.print(dpArr[N]);
    }
}
