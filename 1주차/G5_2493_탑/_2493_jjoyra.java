package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_조희라 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N]; // 입력 배열
        int[] answer = new int[N]; // 정답 배열

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        for(int i = N - 1; i > 0; i--) { // 끝에서부터 시작
            if(arr[i] <= arr[i - 1]) { // 바로 앞 원소가 해당 원소보다 크거나 같으면
                answer[i] = i; // 정답 배열에 저장(인덱스는 0부터, 자리는 1부터)
                while(!stack.isEmpty()) { // 스택이 빌 때까지
                    if(arr[stack.peek()] <= arr[i - 1]) // i - 1 원소가 스택 맨 위 원소보다 크거나 같으면
                        answer[stack.pop()] = i; // 스택에서 제거하고 정답 배열의 해당 자리에 i 저장
                    else
                        break; // 아닐 때 탈출
                }
            } else
                stack.push(i); // 바로 앞 원소가 더 작으면 스택에 해당 원소 삽입

        }

        for(int i = 0; i < N; i++)
            sb.append(answer[i] + " ");

        System.out.println(sb.toString());

    }
}
