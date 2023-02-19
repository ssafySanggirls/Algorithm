package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class _7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] box = new int[n][m];
        Deque<Pos> dq = new ArrayDeque<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1){
                    dq.add(new Pos(i, j));
                }
            }
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        while(!dq.isEmpty()){
            Pos pos = dq.poll();
            int i = pos.x;
            int j = pos.y;
            for(int k=0; k<4; k++){
                int ni = i + dx[k];
                int nj = j + dy[k];
                if(ni >= 0 && ni < n && nj >= 0 && nj < m && box[ni][nj] == 0){
                    box[ni][nj] = box[i][j] + 1;
                    dq.add(new Pos(ni, nj));
                }
            }
        }

        boolean isOkay = true;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(box[i][j] == 0){
                    isOkay = false;
                }
            }
        }

        if(isOkay){
            int ans = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    ans = Math.max(ans, box[i][j]);
                }
            }
            System.out.println(ans-1);
        }else{
            System.out.println(-1);
        }

    }

    static class Pos{
        int x, y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
