package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1655_jjoyra {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a,b) -> b - a);
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			minHeap.offer(tmp);
			
			while(minHeap.size() - maxHeap.size() > 2) {
				maxHeap.offer(minHeap.poll());
			}
			
			if(maxHeap.size() > 0 && minHeap.peek() < maxHeap.peek()) {
//
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(minHeap.poll());
			}
			
			if(minHeap.size() == maxHeap.size())
				sb.append(maxHeap.peek() + "\n");
			else 
				sb.append(minHeap.peek() + "\n");
	
		}
		
		System.out.println(sb.toString());
		
	}

}
