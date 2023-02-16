import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    char[] chars = in.readLine().toCharArray();

    int sumCnt = 0; // 누적 쇠막대 개수
    int nowCnt = 0; // 현재 쇠막대 개수
    if (chars[0] == '(')
      nowCnt++;
    for (int i = 1; i < chars.length; i++) {
      if (chars[i] == '(') { // ((, )(
        nowCnt++;
      } else {
        if (chars[i - 1] == '(') { // () => 레이저
          nowCnt--;
          if (nowCnt <= 0)
            nowCnt = 0;
          sumCnt += nowCnt;
        } else { // ))
          nowCnt--;
          sumCnt++;
        }
      }
    }
    System.out.println(sumCnt);

  }

}
