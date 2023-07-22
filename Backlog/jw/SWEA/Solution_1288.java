import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1288 {
	
	static int N;
	static boolean[] num;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			
			N = Integer.parseInt(br.readLine());
			num = new boolean[10];
			
			int k = 0;
			while (true) {
				k++;
				String sheepNum = Integer.toString(N * k);
				
				for (int i = 0; i < sheepNum.length(); i++) {
					num[sheepNum.charAt(i) - '0'] = true;
				}
				
				if (check()) {
					break;
				}
			}
			
			sb.append(N * k).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static boolean check() {
		
		for (int i = 0; i <= 9; i++) {
			if (num[i] == false) {
				return false;
			}
		}
		
		return true;
	}

}
