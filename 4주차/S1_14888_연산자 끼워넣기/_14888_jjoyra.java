package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14888_jjoyra {
	static int N, minAnswer = Integer.MAX_VALUE, maxAnswer = Integer.MIN_VALUE;
	static int[] operators;
	static int[] perOper;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		numbers = new int[N];
		operators = new int[4];
		perOper = new int[N - 1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		per(0);
		
		System.out.println(maxAnswer);
		System.out.println(minAnswer);
	}
	
	static void per(int num) {
		if(num == N - 1) {
			int tmp = calcul();
			minAnswer = Math.min(minAnswer, tmp);
			maxAnswer = Math.max(maxAnswer, tmp);

			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(operators[i] > 0) {
				operators[i]--;
				perOper[num] = i;
				per(num + 1);
				operators[i]++;			
			}
		
		}
	}
	
	static int calcul() {
		int result = numbers[0];
		for(int i = 0; i < N - 1; i++) {
			int tmp = numbers[i + 1];
			switch(perOper[i]) {
			case 0 :
				result += tmp;
				break;
			case 1 :
				result -= tmp;
				break;
			case 2 :
				result *= tmp;
				break;
			case 3 :
				result /= tmp;
				break;
			}
		}
		
		return result;
	}

}
