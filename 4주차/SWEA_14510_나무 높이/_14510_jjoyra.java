package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_14510_조희라 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new ArrayDeque<>();

        for(int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int maxTree = 0;
            int date = 0;

            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                queue.offer(x);
                if (maxTree < x) maxTree = x;
            }

            // 짝홀이 일치하지 않을경우 date 1증가 처리
            // 최대높이와 같은 원소가 나왔을 때 date 처리

            // 날짜가 홀수일 때 1 자라고 짝수일 때 2 자람
            while (!queue.isEmpty()) {

                // 큐를 한바퀴 돌렸는데 짝 & 홀이 일치하지 않을 경우
                int size = queue.size();
                boolean flag = false;
                date++;

                int grow = date % 2 == 0 ? 2 : 1;
                for (int i = 0; i < size; i++) {
                    int tmp = queue.poll();
                    // 가장 높이가 높은 나무와 높이가 같으면 break
                    if (tmp >= maxTree) {
                        date--;
                        flag = true;
                        break;
                    }

                    // date의 짝수/홀수와 maxTree의 짝수/홀수가 같을 때
                    if(date % 2 == (maxTree - tmp) % 2) {
                        tmp += grow;
                        queue.offer(tmp);
                        flag = true;
                        break;
                    } else if(queue.isEmpty() && maxTree - tmp <= 2) {
                        flag = true;
                    }

                    queue.offer(tmp);

                }
                // 짝 & 홀 일치하는게 없고 부족한 높이가 grow보다 클 때
                if(!flag) {

                    for(int i = 0; i < queue.size(); i++) {
                        int tmp = queue.poll();
                        if((maxTree - tmp) >= grow) {
                            tmp += grow;
                            queue.offer(tmp);
                            break;
                        }
                        queue.offer(tmp);
                    }
                }

            }

            System.out.println("#" + t + " " + date);

        }
    }
}