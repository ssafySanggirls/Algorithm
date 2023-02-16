import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

  public static void main(String[] args) throws NumberFormatException, IOException {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine()); // 빌딩의 개수
    Stack<Integer> s = new Stack<>();
    long cnt = 0;

    for (int i = 1; i <= N; i++) {
      int h = Integer.parseInt(in.readLine());

      while (!s.isEmpty()) {
        if (h < s.peek()) { // 입력 받은 값이 더 작다면
          cnt = cnt + s.size();
          break;
        }

        s.pop();
      }

      s.push(h);
    }

    System.out.println(cnt);
  }

}
