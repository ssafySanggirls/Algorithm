package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main_6198_조희라 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        long sum = 0;

        long arr[] = new long[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 10 4 3 2 1
        // 10
        // 5 1 2 3 4
        // 4

        for(int i = 0; i < N - 1; i++) {
            if(arr[i] <= arr[i + 1]) { // 바로 다음 원소가 크거나 같으면
               // 해당 원소에서 확인할 수 있는 빌딩 x

                while(!stack.isEmpty()) { // 스택이 빌 때까지 
                    if(arr[i + 1] >= arr[stack.peek()]) { // 스택의 맨 위 원소 i + 1 원소보다 작거나 같으면
                        sum += i - stack.pop(); // 스택에서 해당 원소 제거 후 해당 층에서 확인할 수 있는 옥상 수 계 
                    } else
                        break;

                }
            } else {
                stack.push(i); // 바로 다음 원소보다 작으면 스택에 삽
            }
        }

        while(!stack.isEmpty()) { // 스택이 빌 때까지 
            sum += N - 1 - stack.pop(); // 스택에 남아있는 층에서 볼 수 있는 옥상 수 계산 
        } // 이 때 남아있는 원소들은 자신의 오른쪽에 있는 모든 옥상을 볼 수 있음 

        System.out.println(sum);

    }

}
