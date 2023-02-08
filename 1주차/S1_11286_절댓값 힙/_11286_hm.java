package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _11286_problem2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		//0: 절대값, 1: 진짜 값 (자연수, 음수)
		PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) return o1[1]-o2[1]; //절대값이 같다면 진짜 값으로 오름차순(음수가 먼저)
				return o1[0]-o2[0]; //절대값이 같지 않다면 절대값을 기준으로 오름차순 (작->큰)
			}
			
		});
		
		for(int i=0;i<T;i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x != 0) {
				if(x>0) heap.add(new int[] {x, x});
				else heap.add(new int[] {x*-1, x});
			}else {				
				if(heap.isEmpty()) {
				//만약 배열이 비어있는 경우에 절대값이 가장 작은 값을 출력하라고 하면 - 0 출력
					System.out.println(0);
				}else {
					//배열이 차있음.
					//절댓값이 가장 작은 값이 여러개일 때는, 가장 작은 수를 출력하고, 그 값을 배열에서 제거
					int[] absMin = heap.poll(); //가장 앞의 것 반환.
					System.out.println(absMin[1]);
				}			
			}
		}
	}
}
