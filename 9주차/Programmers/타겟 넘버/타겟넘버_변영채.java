package l2;

import java.util.*;

// 프로그래머스 > DFS/BFS LEVEL2. 타겟 넘버
public class dfs_targetnumber {
    int answer, n, end, list[];
    public int solution(int[] numbers, int target) {
        answer = 0;
        n = numbers.length;
        end = target;
        list = Arrays.copyOf(numbers, numbers.length);
        dfs(-1, 0);
        return answer;
    }

    public void dfs(int index, int sum){
        if(index == n-1){
            if(sum == end){
                answer++;
            }
            return;
        }

        index++;
        dfs(index, sum+list[index]);
        dfs(index, sum-list[index]);
    }
}
