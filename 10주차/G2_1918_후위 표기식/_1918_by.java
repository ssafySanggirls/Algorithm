package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// G2. 후위 표기식
public class _1918 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] ch = br.readLine().toCharArray(); // 입력
        StringBuilder sb = new StringBuilder(); // 정답 저장
        Stack<Character> st = new Stack<>();

        // 로직
        for(int i=0; i<ch.length; i++) {
            if(ch[i] >= 'A' && ch[i] <= 'Z') sb.append(ch[i]); // 문자인 경우 정답에 바로 저장
            else { // 연산자인 경우
                if(st.isEmpty()) st.add(ch[i]); // 스택이 비어있으면 추가
                else { // 스택에 뭔가 있으면 제일 위에 있는 연산자와 우선순위 비교
                    if(ch[i] == ')') { // )인 경우 ( 나올때까지 빼서 정답에 세팅
                        while(st.peek() != '(') {
                            sb.append(st.pop());
                        }
                        st.pop();
                    }else if(ch[i] == '(') { // (인 경우 스택에 추가
                        st.add(ch[i]);
                    }else if(ch[i] == '*' || ch[i] == '/') { // *나 / 인 경우
                        if(st.peek() == '+' || st.peek() == '-' || st.peek() == '(') { // 스택에 젤 위에 있는 값이 우선순위가 더 낮으면
                            st.add(ch[i]); // 스택에 저장
                        }else { // 우선순위가 같으면
                            sb.append(st.pop()); // 스택에 있는 값을 빼서 정답에 저장해준 후
                            st.add(ch[i]); // 현재 연산자 스택에 저장
                        }
                    }else { // +나 -인 경우
                        while(true) {
                            if(st.isEmpty()) { // 스택이 비면 해당 연산자 추가해주고 종료
                                st.add(ch[i]);
                                break;
                            }
                            if(st.peek() == '+' || st.peek() == '-') { // 우선순위가 같으면 빼주고 추가해주고 종료
                                sb.append(st.pop());
                                st.add(ch[i]);
                                break;
                            }else if(st.peek() == '*' || st.peek() == '/') { // 스택에 저장된 값의 우선순위가 더 높으면 해당 값을 빼서 정답에 저장
                                sb.append(st.pop());
                            }else { // (이면 그냥 바로 쌓고 종료
                                st.add(ch[i]);
                                break;
                            }
                        }
                    }
                }
            }
        }
        while(!st.isEmpty()) { // 스택에 아직 남아있는 값이 있으면 하나씩 빼주면서 정답에 저장
            sb.append(st.pop());
        }

        // 출력
        System.out.println(sb.toString());
    }
}

/*
 * A*(B+C)
 * -> A*BC+
 * -> ABC+*
 *
 * A+B
 * -> AB+
 *
 * A+B*C
 * -> A+BC*
 * -> ABC*+
 *
 * A+B*C-D/E
 * -> A+BC*-DE/
 * -> ABC*+DE/-
 */
