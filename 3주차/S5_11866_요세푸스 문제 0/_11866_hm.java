package Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _11866_hm2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//1번부터 N번까지 넣어주기
		Queue<Integer> num = new ArrayDeque<Integer>();
		for(int i=0;i<N;i++) {
			num.offer(i+1);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		//순서대로 K번째 사람 제거 
		while(num.size() > 0) {
			for(int i=0;i<K;i++) {
				if(i==K-1) {
					if(num.size()==1) sb.append(num.peek()).append(">");
					else sb.append(num.peek()).append(", ");
					num.poll();//빼기
				}
				else num.offer(num.poll()); //가장 앞의 것을 뒤로 보내줌
			}
		}
		System.out.println(sb);
	}

}