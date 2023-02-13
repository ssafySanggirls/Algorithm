package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_11286 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Comparator<Integer> comparator = (a, b) -> {
			if(Math.abs(a) - Math.abs(b) == 0)
				return a - b;
			return Math.abs(a) - Math.abs(b);
		};
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(comparator);
		
		
		for(int i = 0; i < N; i ++) {
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(minHeap.isEmpty())
					System.out.println(0);
				else
					System.out.println(minHeap.poll());
			} else {
				minHeap.add(x);
			}
		}

	}

}
