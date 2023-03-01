package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1767 {

    static int connect_cnt, sum, n, cnt;
    static int[][] arr;
    static int[][] core;
    static int[] dx = {0, 0, 1, -1}; // 오 왼 하 상
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case<=T; test_case++){
            connect_cnt = 0; // 전원 연결한 코어 개수 -> 최대
            sum = Integer.MAX_VALUE; // 전선 길이의 합 -> 최소
            n = Integer.parseInt(br.readLine()); // 행과 열 크기
            arr = new int[n][n]; // 멕시노스
            cnt = 0; // core의 개수
            for (int i=0; i<n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(i >= 1 && i < (n-1) && j >= 1 && j < (n-1) && arr[i][j] == 1){
                        cnt++;
                    }
                }
            }

            core = new int[cnt][2];
            int index = 0;
            for (int i=1; i<n-1; i++){ // 가장자리를 제외하고 for문 - 이미 전원이 연결됨
                for(int j=1; j<n-1; j++){
                    if(arr[i][j] == 1){
                        core[index][0] = i;
                        core[index++][1] = j;
                    }
                }
            }

            dfs(0, 0);

            System.out.println("#"+test_case+" "+sum);
        }
    }

    private static void dfs(int index, int connect) {
        if(index == cnt){
            if(connect > connect_cnt){
                connect_cnt = connect;
                int temp_sum = 0;
                for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        if(arr[i][j] == 2) temp_sum++;
                    }
                }
                sum = temp_sum;
            }else if(connect == connect_cnt){
                int temp_sum = 0;
                for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        if(arr[i][j] == 2) temp_sum++;
                    }
                }
                sum = Math.min(temp_sum, sum);
            }
            return;
        }

        int si = core[index][0];
        int sj = core[index][1];

        for(int k=0; k<4; k++){
            if(isPossible(si, sj, k)){
                connect(si, sj, k, 2); // 전선(2) 연결
                dfs(index+1, connect+1);
                connect(si, sj, k, 0); // 전선 해제(0으로)
            }else{
                dfs(index+1, connect);
            }
        }
    }

    private static void connect(int si, int sj, int d, int value) {
        int di = dx[d];
        int dj = dy[d];
        int ni = si + di;
        int nj = sj + dj;

        while(true){
            if(ni < 0 || ni >= n || nj < 0 || nj >= n){
                return;
            }
            arr[ni][nj] = value;
            ni += di;
            nj += dj;
        }
    }

    private static boolean isPossible(int si, int sj, int k) {
        int di = dx[k];
        int dj = dy[k];
        int ni = si + di;
        int nj = sj + dj;

        while(true){
            if(ni < 0 || ni >= n || nj < 0 || nj >= n){
                return true;
            }
            if(arr[ni][nj] != 0){
                return false;
            }
            ni += di;
            nj += dj;
        }
    }
}
