package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_10799_조희라 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int cnt = 0;
		int stackCnt = 0;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') { // 여는 괄호 
				stackCnt++; // 쌓인 막대 개수 증가 
			} else { // 닫는 괄호 
				if(str.charAt(i - 1) == '(') { // 바로 앞이 여는 괄호 = 레이저
					cnt += --stackCnt; // 쌓인 막대 개수 하나 감소. 쌓인 막대 개수만큼 총 막대 개수 증가 
				} else { // 레이저가 아닐 때 : 막대 끝 
					stackCnt--; // 막대 개수하나 감소 
					cnt++; // 총 막대 개수 하나 증가 
				}
			}
		}
		
		System.out.println(cnt);

	}

}
