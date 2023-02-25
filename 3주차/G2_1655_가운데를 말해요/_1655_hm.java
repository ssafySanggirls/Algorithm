package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class _1655_hm {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //외치는 정수의 개수
		StringBuilder sb = new StringBuilder(); //정답을 넣을 스트링빌더
		
		PriorityQueue<Integer> min = new PriorityQueue<>(Collections.reverseOrder()); // 가장 큰 값이 우선순위가 높음
		PriorityQueue<Integer> max = new PriorityQueue<>(); // 가장 작은 값이 우선순위가높음
		
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			if(i==0) {
				min.offer(num);
				sb.append(num).append("\n");
			}
			else if(i==1) {
				if(min.peek() > num) {
					int x = min.poll();
					max.offer(x);
					min.offer(num);
					sb.append(num).append("\n");
				}else if(min.peek()<=num){
					//mid < num
					max.offer(num);
					sb.append(min.peek()).append("\n");
				}
			}
			else {	
				if(min.size() == max.size()) { //1 1
					min.offer(num);
				}else {
					//min>max ->O, min<max ->X
					max.offer(num);
				}
				
				if(min.peek()>max.peek()) {
					int x = min.poll();
					int y = max.poll();
					max.offer(x);
					min.offer(y);
				}
				sb.append(min.peek()+"\n");
			}			
		}
		System.out.println(sb);
	}
}