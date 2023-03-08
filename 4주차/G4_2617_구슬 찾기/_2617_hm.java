package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _2617_hm {
	
	static int N, M, ans = 0, dfsCnt, dfsCntLight;
	static ArrayList<Integer>[] list, listLight; 

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1]; //1~N+1
		listLight = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			list[i] = new ArrayList<Integer>();
			listLight[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());//무거운거
			int to = Integer.parseInt(st.nextToken()); //가벼운거
			
			list[from].add(to); //무거운거->가벼운거
			listLight[to].add(from); //무거운거<-가벼운거
		}
		
		for(int i=1;i<N+1;i++) {
			dfsCnt = 0;
			dfs(i, new boolean[N+1]); //무거운 방향
			
			dfsCntLight = 0;
			dfsLight(i, new boolean[N+1]); //가벼운 방향
			
			//중간이 될 수 없는 구슬 : 
			// 무거운 구슬의 개수나 가벼운 구슬의 개수가 (n+1)/2개 이상일때 
			if(dfsCnt >= (N+1)/2 || dfsCntLight >= (N+1)/2) {
				ans++;
			}	
		}
		System.out.println(ans);
	}
	
	
	public static void dfs(int cnt, boolean[] visited) {
		visited[cnt] = true;
		
		for(int v : list[cnt]) {
			if(!visited[v]) {
				dfsCnt++;
				dfs(v, visited);
			}
		}
	}
	
	public static void dfsLight(int cnt, boolean[] visited) {
		visited[cnt] = true;
		
		for(int v : listLight[cnt]) {
			if(!visited[v]) {
				dfsCntLight++;
				dfsLight(v, visited);
			}
		}
	}

}
