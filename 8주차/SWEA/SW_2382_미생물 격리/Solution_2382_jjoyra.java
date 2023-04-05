package _0403_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_2382_조희라 {
    static int N, M, K;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Micro {
        int r, c, n, d;
    }
    static int[][] map;
    static PriorityQueue<Micro> pq;
    static Queue<Micro> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            pq = new PriorityQueue<>((a, b) -> {
                if(a.r == b.r) return a.c - b.c;
                return a.r - b.r;
            });
            queue = new ArrayDeque<>();

            int answer = 0;

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                Micro micro = new Micro();
                micro.r = Integer.parseInt(st.nextToken());
                micro.c = Integer.parseInt(st.nextToken());
                micro.n = Integer.parseInt(st.nextToken());
                micro.d = Integer.parseInt(st.nextToken()) - 1;

                pq.offer(micro);

                map[micro.r][micro.c]++;
            }

            for(int i = 0; i < M; i++) {
                move();
                merge();

            }

            int size = pq.size();

            for(int i = 0; i < size; i++) {
                answer += pq.poll().n;
            }

            System.out.println("#" + t + " " + answer);

        }


    }
        static void move() {

            while(!pq.isEmpty()) {

                Micro tmp = pq.poll();

                map[tmp.r][tmp.c]--;

                tmp.r += dr[tmp.d]; // 이동
                tmp.c += dc[tmp.d];

                map[tmp.r][tmp.c]++;

                if(tmp.r == 0 || tmp.r == N - 1|| tmp.c == 0 || tmp.c == N - 1) { // 가장자리에 위치할 때
                    tmp.n /= 2; // 절반으로 수 감소
                    if(tmp.n == 0) { // 해당 군집의 미생물 수가 0이면 사라짐
                        map[tmp.r][tmp.c]--;
                        continue;
                    }
                    tmp.d += tmp.d % 2 == 0 ? + 1 : - 1; // 방향 변환
                }

                queue.offer(tmp); // 다른 큐에 저장

            }

            while(!queue.isEmpty()) {
                pq.offer(queue.poll());
            }

        }

        static void merge() {
            while(!pq.isEmpty()) {
                Micro micro = pq.poll();

                if(map[micro.r][micro.c] >= 2) { // 해당 위치의 군집이 2개 이상일 경우
                    int sum = micro.n;
                    int maxD = micro.d;
                    int maxN = micro.n;

                    for(int i = 1; i < map[micro.r][micro.c]; i++) {
                        micro = pq.poll();

                        sum += micro.n; // 미생물 합

                        if(maxN < micro.n) {
                            maxN = micro.n; // 가장 큰 군집의 방향 구하기
                            maxD = micro.d;
                        }
                    }

                    micro.n = sum; // 미생물 합
                    micro.d = maxD; // 가장 큰 군집의 방향

                    map[micro.r][micro.c] = 1; 

                }

                queue.offer(micro);
            }

            while(!queue.isEmpty()) {
                pq.offer(queue.poll());
            }
        }

}

