package boj;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2617_jjoyra {
    static boolean[] visited;
    static int N, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] heavyList = new List[N + 1];
        List<Integer>[] lightList = new List[N + 1];

        for(int i = 1; i <= N; i++) {
            heavyList[i] = new ArrayList<>();
            lightList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            lightList[x].add(y);
            heavyList[y].add(x);

        }

        int answer = 0;

        for(int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            cnt = 0;
            dfs(i, lightList);
            if(cnt > N / 2) {
                answer++;
                continue;
            }

            visited = new boolean[N + 1];
            cnt = 0;
            dfs(i, heavyList);
            if(cnt > N / 2) answer++;

        }

        System.out.println(answer);

    }

    static void dfs(int num, List<Integer>[] list) {
        visited[num] = true;


        for(int i = 0; i < list[num].size(); i++) {
            if(!visited[list[num].get(i)]) {
                cnt++;
                dfs(list[num].get(i), list);
            }
        }
    }
}
