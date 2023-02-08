package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_g5_2493_2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Stack<int[]> top = new Stack<>(); //수신받는 탑 번호, 탑 높이 저장
		StringBuilder sb = new StringBuilder(); // 출력문 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int height = Integer.parseInt(st.nextToken()); //탑 높이 입력받음
			while(!top.isEmpty()) {
				//스택에 무언가있음!
				int maxHeight = top.peek()[1]; // peek : 스택의 가장 위의 값을 반환!만 해줌 vs pop() : 스택 가장 위의 값 제거해줌
				if(maxHeight > height) {
					//스택에 있는게 더큼! 그럼 그게 내 신호를 받음
					sb.append((top.peek()[0]+1)+" "); //그거의 첫번째 배열값(탑 번호 인덱스)를 넣어줌
					break; // @@@@@@안해주면 메모리 초과
				}else {
					//내가 더크네! 스택에 있는거 빼줌
					top.pop();
				}
			}
			if(top.isEmpty()) sb.append("0 "); //만약에 비어있으면 내 신호 받을 탑 없으니까 0으로
			top.push(new int[] {i, height}); //스택에 인덱스랑 높이 넣어주기
		}
		
		System.out.println(sb);
	}
}