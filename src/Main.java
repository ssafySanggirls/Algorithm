import java.util.*;

public class Main{
    public static void main(String args[])  {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=0; i<n; i++) {
            String s = sc.next();
            boolean flag=true;

            Stack<Character>stack = new Stack<>();
            for (int j = 0; j < s.length(); j++) {
                if(s.charAt(j)=='('){
                    stack.push(s.charAt(j));
                }else if(s.charAt(j)==')'){
                    if(stack.empty()){
                        flag=false;
                        break;
                    }
                    stack.pop(); //마지막 요소를 꺼냄
                }
//                System.out.println(stack);

            }

            if(stack.empty() && flag){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }


    }
}