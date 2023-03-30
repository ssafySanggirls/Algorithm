package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _3085 {
    static int n, ans;
    static char[][] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        for(int i=0; i<n; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<n; j++) {
                board[i][j] = input[j];
            }
        }
        ans = 1;
        char temp = ' ';
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i < n-1) {
                    temp = board[i][j];
                    board[i][j] = board[i+1][j];
                    board[i+1][j] = temp;
                    candy();
                    temp = board[i][j];
                    board[i][j] = board[i+1][j];
                    board[i+1][j] = temp;
                }
                if(j < n-1) {
                    temp = board[i][j];
                    board[i][j] = board[i][j+1];
                    board[i][j+1] = temp;
                    candy();
                    temp = board[i][j];
                    board[i][j] = board[i][j+1];
                    board[i][j+1] = temp;
                }
            }
        }

        System.out.println(ans);
    }

    private static void candy() {
        int cnt = 0;
        for(int i=0; i<n; i++) {
            cnt = 1;
            for(int j=1; j<n; j++) {
                if(board[i][j] == board[i][j-1]) {
                    cnt++;
                    ans = Math.max(ans, cnt);
                }else {
                    cnt = 1;
                }
            }
        }
        for(int j=0; j<n; j++) {
            cnt = 1;
            for(int i=1; i<n; i++) {
                if(board[i][j] == board[i-1][j]) {
                    cnt++;
                    ans = Math.max(ans, cnt);
                }else {
                    cnt = 1;
                }
            }
        }
    }
}
