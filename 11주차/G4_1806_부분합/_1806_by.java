package g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// G4. 부분합
// 투포인터
// 1. 시작점과 끝점이 첫번째 원소의 인덱스를 가리키도록 한다.
// 2. 현재 부분 합이 M과 같다면 카운트한다.
// 3. 현재 부분 합이 M보다 작다면 end를 1 증가시킨다.
// 4. 현재 부분 합이 M보다 크거나 같다면 start를 1 증가시킨다.
// 5. 모든 경우를 확인할 때까지 2-4번 과정을 반복한다.
public class _1806 {

	static int N, S, nums[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수열의 길이
		S = Integer.parseInt(st.nextToken()); // 목표 부분합
		st = new StringTokenizer(br.readLine());
		nums = new int[N]; // 수열 저장
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = pointer(); // 투포인터 시작
		
		System.out.println(ans); // 출력
	}

	private static int pointer() {
		int result = Integer.MAX_VALUE; // 결과값 -> 최소로 갱신 -> 최대값으로 초기화
		int s = 0, e = 0; // 포인터
		int sum = nums[0]; // 부분합
		while(e < N) { // 두번째 포인터가 부분합을 다 돌았으면 종료
			if(sum >= S) { // 목표 부분합 이상이면 결과값 최소로 갱신
				result = Math.min(result, e-s+1);
			}
			if(sum < S) { // 목표 부분합보다 작으면 두번째 포인터를 증가시키고 부분합 증가
				if(e < N-1) sum += nums[++e];
				else e++; // 인덱스 에러 방지
			}else { // 목표 부분합보다 크거나 같으면 더 작은 길이가 있을 수 있으므로 첫번째 포인터 증가시키고 부분합 감소
				sum -= nums[s++];
			}
		}
		
		return result == Integer.MAX_VALUE ? 0 : result; // 갱신이 안됐다면 합을 만드는 것이 불가능 -> 0 반환
	}

}
