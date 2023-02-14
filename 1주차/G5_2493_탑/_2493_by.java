package com.ssafy.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_변영채 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 탑의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n]; // 탑들의 높이를 저장해두는 배열
		Stack<Integer> tower = new Stack<>(); // 탑들의 번호를 이용하여 높이를 비교할 스택 
		StringBuilder sb = new StringBuilder(); // 결과값을 저장할 스트링빌더
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 배열에 입력받은 값 넣어주기
		}
		sb.append(0).append(" "); // 맨 처음 탑은 수신할 수 있는 탑이 없음
		tower.add(1); // 맨 처음 탑의 높이를 넣음 -> 인덱스 + 1 로 계산
		for(int i=1; i<n; i++) { // 맨 처음 탑을 앞에서 처리했으므로 인덱스 1부터 for문
			boolean isOkay = false; // 수신한 탑이 있는지 없는지 저장할 변수
			while(!tower.isEmpty()) { // 스택이 빌 때까지
				int a = tower.peek(); // 비교 대상의 탑의 번호, 스택에서 빼지는 않고 조회만
				if(arr[a-1] > arr[i]) { // 현재 비교하고 있는 탑보다 더 높은 탑이 있다면(수신할 수 있음)
					sb.append(a).append(" "); // 수신한 탑의 번호 저장
					isOkay = true; // 수신 성공
					break; // while 종료
				}
				tower.pop(); // 탑의 높이가 현재 비교하고 있는 탑보다 작다면 스택에서 빼줌(더이상 비교할 필요 없으므로)
			}
			tower.add(i+1); // 현재 비교하고 있는 탑의 번호 저장
			if(!isOkay) { // 수신한 탑이 없다면
				sb.append(0).append(" "); // 0을 출력
			}
		}
		
		System.out.println(sb.toString()); // 전체 답 출력
	}

}
