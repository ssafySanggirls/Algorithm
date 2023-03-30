package Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class _3085_hm {

	static int N;
	static char[][] candy;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		candy = new char[N][N];
		
		for(int i=0;i<N;i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0;j<N;j++) {
				candy[i][j] = c[j];
			}
		}
		
		int[] di = {-1,1,0,0};
		int[] dj = {0,0,-1,1};
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {				
				for(int d=0;d<4;d++) {
					//상하좌우 -> 인접한 칸, 교환할 칸의 좌표 ni, nj
					int ni = i+di[d];
					int nj = j+dj[d];
					
					if(ni<0 || nj<0 || ni>=N || nj>=N)
						continue;
					
					//사탕 바꿔줌
					char temp = candy[i][j];
					candy[i][j] = candy[ni][nj];
					candy[ni][nj] = temp;
					
					cnt = Math.max(cnt, count()); //가장 긴 연속부분 세기
					
					//사탕 위치 원상복구
					temp = candy[i][j];
					candy[i][j] = candy[ni][nj];
					candy[ni][nj] = temp;
				}
			}
		}
		System.out.println(cnt);
	}
	
	
	private static int count() {
		int cnt = 1;
		// 좌,우 - 행
		for(int i=0;i<N;i++) {
			int count = 1;
			for(int j=0;j<N-1;j++) {
				if(candy[i][j] == candy[i][j+1]) {
					count++;
				}else {
					cnt = Math.max(cnt, count);
					count = 1;
				}
			}
			cnt = Math.max(cnt, count);
		}
		//상,하 - 열
		for(int i=0;i<N;i++) {
			int count = 1;
			for(int j=0;j<N-1;j++) {
				if(candy[j][i] == candy[j+1][i]) {
					count++;
				}else {
					cnt = Math.max(cnt, count);
					count = 1;
				}
			}
			cnt = Math.max(cnt, count);
		}
		return cnt;
	}
}