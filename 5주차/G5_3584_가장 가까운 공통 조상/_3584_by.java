import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/* G4. 가장 가까운 공통 조상 */
public class _3584 {
	
	static int n, ans;
	static int a, b;
	static ArrayList<Integer>[] list;
	static int[] alist, blist;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테케 개수
		for (int test_case = 1; test_case <= T; test_case++) {
			int cnt = Integer.MAX_VALUE;
			ans = 0;
			n = Integer.parseInt(br.readLine()); // 노드의 수
			list = new ArrayList[n+1];
			for(int i=0; i<n+1; i++) {
				list[i] = new ArrayList<Integer>();
			}
			StringTokenizer st = null;
			for(int i=0; i<n-1; i++) { // 간선 정보
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				list[child].add(parent);
			}
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()); // a와 b의 공통조상 찾기
			b = Integer.parseInt(st.nextToken());
			alist = new int[n+1];
			blist = new int[n+1];
			Arrays.fill(alist, -1);
			Arrays.fill(blist, -1);
			alist[a] = 0;
			blist[b] = 0;
			int index = 1;
			while(index <= n) {
				if(index == a) {
					index++;
					continue;
				}
				
				if(list[a].contains(index)) {
					alist[index] = 1;
				}else {
					alist[index] = dfs(index);
				}
				index++;
			}
			
			for(int i=1; i<=n; i++) {
				if(i != b && alist[i] != -1) {
					blist[i] = dfs2(i);
				}
				if(alist[i] != -1 && blist[i] != -1) {
					int min = Math.min(alist[i], blist[i]);
					if(cnt > min) {
						cnt = min;
						ans = i;
					}
				}
			}
			
			
			System.out.println(ans);
		}
	}

	private static int dfs2(int end) {
		Stack<Info> st = new Stack<>();
		for(int i=0; i<list[b].size(); i++) {
			st.add(new Info(list[b].get(i), 1));
		}
		
		while(!st.isEmpty()) {
			Info info = st.pop();
			int cur = info.num;
			int cnt = info.cnt;
			
			if(cur == end) {
				return cnt;
			}
			
			for(int i=0; i<list[cur].size(); i++) {
				st.add(new Info(list[cur].get(i), cnt+1));
			}
		}		
		
		return -1;
	}

	private static int dfs(int end) {
		Stack<Info> st = new Stack<>();
		for(int i=0; i<list[a].size(); i++) {
			st.add(new Info(list[a].get(i), 1));
		}
		
		while(!st.isEmpty()) {
			Info info = st.pop();
			int cur = info.num;
			int cnt = info.cnt;
			
			if(cur == end) {
				return cnt;
			}
			
			for(int i=0; i<list[cur].size(); i++) {
				st.add(new Info(list[cur].get(i), cnt+1));
			}
		}		
		
		return -1;
	}
	
	static class Info{
		int num; // 노드 번호
		int cnt; // 지나온 노드 수
		public Info(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

}