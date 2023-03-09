import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//S1_14888_연산자끼워넣기
public class Main_S1_14888_연산자끼워넣기 {
	
	static int N;
	static int[] numbers;
	static char[] list;
	static char[] select;
	static boolean[] isSelected;
	static int max;
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine()); // 수의 개수
		numbers = new int[N]; // 수
		list = new char[N - 1]; // 연산자 목록
		select = new char[N - 1];
		isSelected = new boolean[N - 1];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) { // 수 입력 받기
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine());
		int idx = 0;
		for (int i = 0; i < 4; i++) { // 연산자 개수 입력 받기
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				switch (i) {
				case 0: // 덧셈(+)
					list[idx++] = '+';
					break;
				case 1: // 뺄셈(-)
					list[idx++] = '-';
					break;
				case 2: // 곱셈(8)
					list[idx++] = '*';
					break;
				case 3: // 나눗셈(/)
					list[idx++] = '/';
					break;
				}
			}
		}
		
		permu(0);
		
		System.out.println(max);
		System.out.println(min);
		
	}
	
	private static void permu(int idx) {
		if (idx == N - 1) {
			int calu = numbers[0];
			for (int i = 0; i < N - 1; i++) {
				int num = numbers[i + 1];
				switch (select[i]) {
				case '+':
					calu += num;
					break;
				case '-':
					calu -= num;
					break;
				case '*':
					calu *= num;
					break;
				case '/':
					calu /= num;
					break;
				}
			}
			
			max = Math.max(max, calu);
			min = Math.min(min, calu);
			
			return;
		}
		
		for (int i = 0; i < N - 1; i++) {
			if (isSelected[i]) {
				continue;
			}
			
			select[idx] = list[i];
			isSelected[i] = true;
			permu(idx + 1);
			isSelected[i] = false;
		}
	}

}
