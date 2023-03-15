package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class _5904 {

    static int n;
    static List<Integer> len;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        len = new ArrayList<>();
        len.add(3); // S(0) = moo -> 3 저장
        int index = findLen();
        System.out.println(index);

        char ans = findN(index, n);
        System.out.println(ans);

    }

    private static char findN(int index, int n) {
        char result;
        int l = len.get(index);
        int moo_l = (l - (1+index+2))/2; // 이전 moo 수열의 길이
        if(n > moo_l && n <= (l-moo_l)){ // 이번 수열 가운데에서 찾으면 됨
            n = n - moo_l;
            if(n == 1){
                result = 'm';
            }else{
                result = 'o';
            }
        }else if(n <= moo_l){ // 이전 수열로 가서 찾아야 함 - 앞
            result = findN(index-1, n);
        }else{ // 이전 수열로 가서 찾아야 함 - 뒤
            result = findN(index-1, n-moo_l-(index+3));
        }

        return result;
    }

    private static int findLen(){
        int result = 0;
        int index = 1;
        while(result < n){
            result = len.get(index-1) + 1 + (index+2) + len.get(index-1);
            len.add(result);
            index++;
        }
        return index-1; // 몇번째 수열까지 가야되는지 반환
    }

}
