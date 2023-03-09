import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//G4_3584_가장가까운공통조상
public class Main_G4_3584_가장가까운공통조상 {
	
	static int N;
	static Node[] list;
	static boolean[] visited;
	static int[] cnt;
	static int answer;
	
	static class Node {
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine()); // 테스트 케이스 개수
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine()); // 노드의 개수
			list = new Node[N + 1];
			cnt = new int[N + 1];
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list[to] = new Node(from, list[to]);
			}
			st = new StringTokenizer(in.readLine());
			int vertex1 = Integer.parseInt(st.nextToken());
			int vertex2 = Integer.parseInt(st.nextToken());

			visited = new boolean[N + 1];
			dfs(vertex1);
			
			visited = new boolean[N + 1];
			dfs(vertex2);
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
		
	}

	private static void dfs(int curr) {
		visited[curr] = true;
		cnt[curr]++;
		if (cnt[curr] == 2) {
			answer = curr;
			return;
		}
		
		for (Node temp = list[curr]; temp != null; temp = temp.link) {
			if (!visited[temp.vertex]) {
				dfs(temp.vertex);
			}
		}
	}
	
}
