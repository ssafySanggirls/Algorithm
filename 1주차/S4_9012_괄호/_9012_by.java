package silver4;

import java.util.Scanner;
import java.util.Stack;

public class _9012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int test_case=1; test_case<=t; test_case++){
            String input = sc.next();
            char[] chars = input.toCharArray();
            Stack<Character> st = new Stack<>();
            boolean isOkay = true;

            for(int i=0; i<chars.length; i++){
                Character c = chars[i];
                if(c.equals('(')){
                    st.push(c);
                }else{
                    if(st.size() > 0){
                        st.pop();
                    }else {
                        isOkay = false;
                        break;
                    }
                }
            }

            if(st.size() > 0){
                isOkay = false;
            }

            if(!isOkay){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }
        }
    }
}
