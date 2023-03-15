package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _5904_hm {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		moo(0, 3);
	}
	private static void moo(int cnt, int len) {
		int totalLen = len*2 + (cnt+1)+3;
		if(N<=3) {
			if(N==1) System.out.println("m");
			else System.out.println("o");
			return;
		}
		if(totalLen >= N) {
			//앞의 S(k-1)부분은 totalLen을 증가시키면서 확인했음
			if(len+1 == N) {
				//중간부분
				System.out.println("m");
				return;
			}else if(len+2 <= N && N < len+(cnt+1)+3) {
				//중간 부분에 있음
				System.out.println("o");
				return;
			}
			else {
				//뒤의 S(k-1)부분에 있음
				N -= (len+(cnt+1)+3);
//				System.out.println(N);
				moo(0, 3); //다시 처음부터 재귀 호출
			}
		}
		else moo(cnt+1, totalLen); //매개변수 len을 증가시키면서 재귀 호출
	}

}
