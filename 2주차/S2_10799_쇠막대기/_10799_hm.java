package Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_10799 {
	
	static int bar = 0; //쇠막대기 개수
	static int ans = 0; //조각 개수
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split("");
		Stack<String> s = new Stack<>();
		
		for(int i=0;i<in.length;i++) {
			if(in[i].equals("(")) {
				s.push(in[i]); 
				bar++;
			}
			else if(in[i].equals(")")){
				if(s.peek().equals("(")) {
					//만약 지금이 레이저라면 ()
					bar--;
					ans += bar;
					
					s.push(in[i]);
				}else {
					//))
					//쇠막대기의 끝을 말하는 닫는 괄호라면
					bar--;
					ans++;
					s.push(in[i]);
				}
			}
		}
		System.out.println(ans);
		
	}

}
