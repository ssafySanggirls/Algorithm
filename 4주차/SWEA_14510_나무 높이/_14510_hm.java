import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _14510_hm {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] trees = new int[N];
			for(int i=0;i<N;i++) {
				trees[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(trees);
			int maxLength = trees[N-1];
			int even = 0, odd = 0;
			for(int i=0;i<N-1;i++) {
				int len = maxLength - trees[i];
				even = even + len/2;
				odd = odd + len%2;
			}
			
			int day = 0;
			while(even+odd>0) {
				day++;
				if(day%2==1 && even != 0 && odd != 0) odd--;
				else if(day%2==0 && odd != 0 && even != 0) even--;
				else if(day%2==1 && odd == 0 && even != 1) {
					//짝수 길이만 남았을 때, 첫째날 셋째날일때
					even--;
					odd++;
				}
				else if(day%2==0 && even == 0) continue; //홀수 길이만 남았을 때, 이틀째나 넷째날일때
				else if(day%2==1 && even == 0) odd--;
				else if(day%2==0 && odd == 0) even--;
			}
			sb.append("#").append(t).append(" ").append(day).append("\n");
		}
		System.out.println(sb);
	}
}