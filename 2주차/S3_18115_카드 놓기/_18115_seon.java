import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine()); // 기술을 사용한 횟수
		Stack<Integer> skills = new Stack<>(); // 사용한 기술
		Stack<Integer> nums = new Stack<>(); // 스킬을 쓰고난 결과
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = N; i >= 1; i--) {
			skills.add(Integer.parseInt(st.nextToken()));
			nums.add(i);
		}
		
		List<Integer> answer = new LinkedList<>();
		while(!skills.isEmpty()) {
			int skill = skills.pop();
			int num = nums.pop();
			
			if (skill == 1) {
				answer.add(0, num);
			} else if (answer.size() > 1 && skill == 2) {
				answer.add(1, num);
			} else if (answer.size() > 1 && skill == 3) {
				answer.add(answer.size(), num);
			} else {
				answer.add(num);
			}
		}
		
		for (int i: answer) {
			sb.append(i).append(" ");
		}
		
		System.out.println(sb);
		
	}
	
}
