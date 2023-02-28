package Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14888_hm {
	
	static int N;
	static int[] operator, num;
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //수의 개수
		num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0;i<N;i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		operator = new int[4]; //덧셈, 뺄셈, 곱셈, 나눗셈
		for(int i=0;i<4;i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		func(1, num[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	private static void func(int cnt, int result) {
		if(cnt==N) { //0~N-1까지 전부 다했을때
			if(result > max)
				max = result;
			if(result < min)
				min = result;
			return;
		}
		for(int x = 0; x<4; x++) {
			if(operator[x]>0) {
				operator[x]--; //재귀 들어가기전 사용한 것이니까 빼줌, 방문체크 true
				if(x == 0) {
					func(cnt+1, result + num[cnt]);
				}else if(x==1) {
					func(cnt+1, result - num[cnt]);
				}else if(x==2) {
					func(cnt+1, result * num[cnt]);
				}else {
					func(cnt+1, result / num[cnt]);
				}
				operator[x]++; //재귀 들어가기전 사용다 했으니까 다시 더해줌, 방문체크 false
			}
		}
	}

}
