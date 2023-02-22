package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _6198 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 옥상의 개수
        int[] arr = new int[n]; // 옥상의 높이를 저장해두는 배열
        Stack<Integer> building = new Stack<>(); // 빌딩의 번호를 이용하여 높이를 비교할 스택
        long res = 0; // 결과값
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        building.push(arr[0]);
        for(int i=1; i<n; i++) {
            if(building.peek() > arr[i]){
                res += building.size();
            }else{
                while(!building.isEmpty()){
                    if(building.peek() <= arr[i]) building.pop();
                    else break;
                }
                res += building.size();
            }
            building.push(arr[i]);
        }

        System.out.println(res); // 전체 답 출력
    }
}
