import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _11725_hm {

	static ArrayList<Integer>[] adjList;
	static int[] parents;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		adjList = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		StringTokenizer st;
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
//		for(int i=0;i<N+1;i++) {
//			System.out.println(adjList[i].toString());
//		}
		
		dfs(1, new boolean[N+1]);
		
		for(int i=2;i<N+1;i++) {
			System.out.println(parents[i]);
		}
	}
	
	public static void dfs(int x,boolean[] visited) {
		visited[x] = true;
		
		for(int v : adjList[x]) {
			if(!visited[v]) {
				parents[v] = x;
				
				//System.out.println(v+" "+x);
				dfs(v, visited);
			}
			
		}
	}
	

}
