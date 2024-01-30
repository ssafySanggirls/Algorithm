import java.io.*;
import java.util.*;

public class G1_16980_창업 {

    static ArrayList<Character> koo, cube;
    static char[] ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] ko, cub;
        koo = new ArrayList<Character>();
        cube = new ArrayList<Character>();

        ko = br.readLine().toCharArray();
        cub = br.readLine().toCharArray();
        for(int i=0;i<ko.length;i++) {
            koo.add(ko[i]);
            cube.add(cub[i]);
        }

        Collections.sort(koo);
        Collections.sort(cube, Collections.reverseOrder());

        ans = new char[koo.size()];
        int kooIdx = 0, cubeIdx=0;
        for(int i=0;i<koo.size();i++) {
            // 구사과는 가장 작은 문자를, 큐브러버는 가장 큰 문자를 앞에서부터 채움
            if(i%2 == 0) {
                // 구사과 순서 - 짝수(구사과가 제일 먼저)
                if(koo.get(kooIdx) >= cube.get(cubeIdx)) { // 구사과 작은 값이 큐브러버 큰 값보다 클때 -> 뒤에 넣어야하는 경우
                    int n = koo.size()-i;

                    if(n%2==1) {
                        // 남은 n이 홀수일때
                        for(int j=0;j<n;j++) {
                            if(j%2==0) ans[i+j] = koo.get(kooIdx++); //짝수는 전부 구사과
                            else ans[i+j] = cube.get(cubeIdx++);
                        }
                    }else {
                        //남은 n이 짝수 일때
                        for(int j=0;j<n;j++) {
                            if(j%2==1) ans[i+j] = koo.get(kooIdx++); //홀수는 전부 구사과
                            else ans[i+j] = cube.get(cubeIdx++);
                        }
                    }

                    break;
                }else {
                    // 구사과 작은 값이 큐브러버 큰 값보다 작을 때
                    ans[i] = koo.get(kooIdx++);
                }
            }else {
                // 큐브러버 순서 - 홀수
                if(koo.get(kooIdx) >= cube.get(cubeIdx)) { // 구사과 작은 값이 큐브러버 큰 값보다 클때
                    int n = koo.size()-i;
                    if(n%2==1) {
                        // 남은 n이 홀수일때
                        for(int j=0;j<n;j++) {
                            if(j%2 == 1) ans[i+j] = koo.get(kooIdx++);
                            else ans[i+j] = cube.get(cubeIdx++);
                        }
                    }else {
                        // 남은 n이 홀수일 때
                        for(int j=0;j<n;j++) {
                            if(j%2==0) ans[i+j] = koo.get(kooIdx++);
                            else ans[i+j] = cube.get(cubeIdx++);
                        }
                    }
                    break;
                }else {
                    // 구사과 작은 값이 큐브러버 큰 값보다 작을 때 ( 구사과<큐브러버 )
                    ans[i] = cube.get(cubeIdx++);
                }
            }
        }
        System.out.println(String.valueOf(ans));
    }
}
