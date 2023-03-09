package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3584_조희라 {
	static List<Integer>[] list;
	static int level;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			list = new List[N + 1];
			
			for(int i = 0; i < N + 1; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
					
				list[B].add(A);

			}
			
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		dfs(0, x);
		int levelX = level; 
		dfs(0, y);
		
//		System.out.println(levelX + " " + level);
		
		int lDis = Math.abs(level -levelX);
		
		int newX = level > levelX ? y : x;
		int newY = level > levelX ? x : y;
		

		for(int i = 0; i < lDis; i++) {
			newX = list[newX].get(0);
		}
//		System.out.println(newX + " " + newY);
		
		while(newX != newY) {
			newX = list[newX].get(0);
			newY = list[newY].get(0);
		}
		
		System.out.println(newX);
		}
	}
	
	static void dfs(int cnt, int num) {
		if(list[num].isEmpty()) {
			level = cnt;
			return;
		}
		
		dfs(cnt + 1, list[num].get(0));

	}
}
