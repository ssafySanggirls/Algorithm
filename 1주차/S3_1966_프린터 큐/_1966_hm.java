package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
public class _1966_problem3 {

	public static void main(String[] args) throws IOException, NullPointerException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0;i<T;i++) {
			String [] in = br.readLine().split(" ");
			int N = Integer.parseInt(in[0]);
			int M = Integer.parseInt(in[1]);
			
			LinkedList<int[]> queue = new LinkedList<>();
			String [] in2 = br.readLine().split(" ");
			
			for(int j=0;j<N;j++) {
				int x = Integer.parseInt(in2[j]);
				queue.add(new int[] {j,x});
			}
			
			int key=0;
			while(true) {
				int[] first = queue.poll(); //맨 처음꺼 반환, 리스트에서 없애주는 함수
				
				boolean ifmax = true; //얘는 max라고 가정
				
				for(int j=0;j<queue.size();j++) { //max라고 생각하지만 일단 뒤에 있는 것들 확인!
					if(first[1]<queue.get(j)[1]) { //앞에것보다 j번째 뒤에 거에 큰게 있음! 그 큰거까지 뒤에 넘겨줘야함
						queue.offer(first); //맨 뒤에 삽입
						//0부터 큰게 있는 j까지 뒤에 넣어줌
						for(int k=0;k<j;k++) {
							queue.offer(queue.poll());//앞에꺼 뺀걸 맨 뒤에 넣어줌
						}
						ifmax = false; //큰게아니여씀 first보다 뒤에꺼가 max임.
						break; //내가 max가 아니였따니!! 난 뒤로 갔으니까 이제 for문 끝내기
					}
					//else //first[1]>=queue.get(j)[1] 첫번째꺼가 max임.ifmax=true
				}
				if(ifmax == false)  //근데 만약에 for문 돌렸는데 큰게 없었음!
					continue; //다른 큐로 넘어가버림
			
				key++;
				if(first[0] == M) { 	//내가 m임. 찾고자 하는 문서라면 해당 테스트케이스 종료
					System.out.println(key);
					break;}
			}
			//System.out.println(key);

		}
		
	}

}

/** 
 * 출처 : https://kkh0977.tistory.com/1071
 * [요약 설명]
 * 1. LinkedList 는 연결 리스트(LinkedList)는 각 노드가 데이터와 포인터를 가지고 한 줄로 연결되어 있는 방식의 자료구조입니다
 *    [헤더 / 노드] -> [헤더 / 노드] -> [헤더 / 노드]
 * 2. 노드는 LinkedList에 객체를 추가하거나 삭제하면 앞뒤 링크만 변경되고 나머지 링크는 변경되지 않으며,
 *    또한, 중간에 데이터를 추가나 삭제하더라도 전체의 인덱스가 한 칸씩 뒤로 밀리거나 당겨지는 일이 없습니다
 * 3. 링크드리스트는 인덱스가 없기에 특정 요소에 접근하기 위해서는 순차 탐색이 필요해 탐색 속도가 떨어집니다
 * 4. 링크드리스트를 사용해서 큐 (queue) 구조 FIFO 선입선출 데이터 삽입, 삭제 기능을 사용할 수 있습니다
 * 5. add : 해당 큐의 맨 뒤에 전달된 요소를 삽입합니다
 * 6. element : 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환합니다
 * 7. offer : 해당 큐의 맨 뒤에 전달된 요소를 삽입합니다
 * 8. peek : 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환합니다
 * 9. poll : 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환하고, 해당 요소를 큐에서 제거합니다
 * 10. remove : 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 제거합니다
 * 11. size : 해당 큐의 크기 (사이즈) 를 구합니다
 * 12. clear : 해당 큐에 저장된 데이터를 전체 삭제합니다 
 * */
