import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_11866_요세푸스문제0 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람 수
		int K = Integer.parseInt(st.nextToken()); // 제거할 사람 번호(K번째)
		
		List<Integer> list = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		sb.append("<");
		K--; // 인덱스로 변환
		int idx = 0;
		int size = 0;
		while (!list.isEmpty()) {
			idx += K;
			size = list.size();
			if (idx >= size) {
				idx %= size;
			}
			if (list.size() == 1) {
				sb.append(list.get(0)).append(">");
				break;
			}
			sb.append(list.get(idx)).append(", ");
			list.remove(idx);
		}
		
		System.out.println(sb);
	}
	
}
