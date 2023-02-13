package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1966_jjoyra {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Queue<Integer> queue = new LinkedList<>();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int arr[] = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < n; i++) {
				queue.add(i);
			}
			
			int cnt = 0;
			
			while(!queue.isEmpty()) {
				int a = (int) queue.poll();
				int p = 0;
				
				for(int j = 0; j < n; j++) {

					if(arr[a] < arr[j]) {
						queue.add(a);
						p = 1;
						break;
					}
				}
				
				if (p == 0) {
					cnt++;
					arr[a] = 0;
					if(a == m) {
						System.out.println(cnt);
						queue.clear();
						break;
					}
				}
			}
		}
		
	}
}
