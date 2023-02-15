package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class _6198_hm2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 빌딩의 개수
		
		Stack<Integer> building = new Stack<>(); //빌딩 스택 - 빌딩 높이를 저장
		long ans=0; //빌딩 수의 합
		
		int[] list = new int[N]; //일단 입력 받는 빌딩의 높이
		// 빌딩의 높이 다 입력받음
		for(int i=0;i<N;i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		// 스택 돌기
		for(int i=0;i<N;i++) { // 앞에서 부터 검사. 문제는 i=0일때 1번 관리인
			int height = list[i]; //탑 높이 입력받음
			
			while(!building.isEmpty() && building.peek() <= height) {  
				// 스택이 비어있지 않으면서 스택 안의 빌딩 높이가 i번째 높이보다 작거나 같을때
					building.pop(); // peek() 는 이제 list[i]의 빌딩의 옥상을 볼 수 없으니까 pop
			}
			
			ans = ans + building.size(); // 스택의 사이즈를 더해줌	
			building.push(height); //스택에 높이 넣어주기
		}
		System.out.println(ans); //결과 출력
	}
}
