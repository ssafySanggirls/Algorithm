package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * (는 스택에 추가
 * 레이저(연속된 열괄,닫괄)가 나올 때마다 cnt에 스택의 길이 추가
 * 연속되지 않은(레이저가 아닌) 닫는 괄호가 나올 때마다 pop해준 후 cnt++
 */
public class _10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] laser = br.readLine().toCharArray();
        Stack<Character> st = new Stack<>();
        int ans = 0;
        boolean isOpen = true;
        for(int i=0; i<laser.length; i++){
            if(laser[i] == '('){ // 열린괄호
                isOpen = true;
                st.push(laser[i]);
            }else{ // 닫힌괄호
                st.pop();
                if(isOpen){ // 바로 직전이 열린괄호였다면 -> 레이저
                    ans += st.size();
                }else { // 레이저가 아닌 닫는 괄호인 경우
                    ans++;
                }
                isOpen = false;
            }
        }
        System.out.println(ans);

    }
}
