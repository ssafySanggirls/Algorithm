package Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main_18115_problem {

	static int N; // 카드 수
	static String[] in; // 사용한 카드 기술 
	static Deque<Integer> dq = new LinkedList<Integer>(); // 카드 결과들
	static StringBuilder sb = new StringBuilder(); //결과 담는 곳
	
	public static void skill1(int x) {
		dq.offerFirst(x);
		
	}
	
	public static void skill2(int x) {
		int first = dq.poll(); //첫번째 카드 일단 제거
		dq.offerFirst(x); //두번째 카드를 내려놓음
		dq.offerFirst(first);//첫번째 카드 다시 맨 앞에 넣기
	}
	
	public static void skill3(int x) {
		dq.offerLast(x); //카드 맨 뒤에 넣기
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		/* 1 2 3 4 5
		 * 위            아래
		 * */
		in = br.readLine().split(" "); // 기술들 입력받기
		int card = 1; // 결과 카드가 1부터 N 까지의 정수가 중복되지 않게 놓여있었다.
		for(int i=N-1;i>=0;i--) {
			//맨 마지막 기술부터 확인하기
			if(in[i].equals("1")) {
				skill1(card);
			}else if(in[i].equals("2")) {
				skill2(card);
			}else {
				skill3(card);
			}
			card++;
		}
		
		for(int i=0;i<N;i++) {
			sb.append(dq.pollFirst()+" "); // 결과 앞에서부터 출력
		}
		System.out.println(sb);

	}

}
