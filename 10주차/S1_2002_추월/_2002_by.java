package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// S1. 추월
public class _2002 {

    public static void main(String[] args) throws Exception{
        int ans = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 차의 대수
        String[] ent = new String[n]; // 대근 -> 입구
        String[] exit = new String[n]; // 영식 -> 출구
        int[] index = new int[n]; // 들어간 차들이 나온 순서 저장

        // 입력
        for(int i=0; i<n; i++){
            ent[i] = br.readLine();
        }
        for(int i=0; i<n; i++){
            exit[i] = br.readLine();
        }

        // 전처리 -> 들어간 차들이 나온 순서를 인덱스로 저장
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(ent[i].equals(exit[j])){
                    index[i] = j;
                    break;
                }
            }
        }

        // 로직
        int max = index[0]; // 제일 처음 들어간 차가 나온 순서로 최대값 초기화
        for(int i=1; i<n; i++){ // 두번째 들어간 차부터 살펴봄
            if(index[i] < max){ // 저장된 최대값보다 먼저 나온 차라면 추월한 차
                ans++;
            }else{ // 저장된 최대값보다 큰 값이 나오면 추월하지 않음 -> 최대값 갱신
                max = index[i];
            }
        }

        // 출력
        System.out.println(ans);
    }
}
