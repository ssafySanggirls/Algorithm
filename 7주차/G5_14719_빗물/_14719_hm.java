package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14719_hm {

	static int H, W;
	static int[][] block;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken()); 
		W = Integer.parseInt(st.nextToken()); 
		block = new int[H][W]; //빗물은 0으로 초기화
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<W;i++) {
			int x = Integer.parseInt(st.nextToken());
			if(x==0) continue;
			for(int j = H-x; j < H; j++) {
//				System.out.println(x+" "+j);
//				System.out.println("==");
				block[j][i] = 1; //블록은 1로 바꿔줌
			}
		}
		
		Loop : for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(j==0 && block[i][j]==1) {
					break Loop;
				}
				if(block[i][j] != 0) break; // 1이면, 블록이면
				else { 
					// 0 이면, 빗물이면
					block[i][j] = -1;
				}
			}
			
		}
		
		Loop : for (int i = 0; i < H; i++) {
			for (int j = W-1; j >=0; j--) {
				if(j==0 && block[i][j]==1) {
					break Loop;
				}
				if(block[i][j] != 0) break; // 1이면, 블록이면
				else { 
					// 0 이면, 빗물이면
					block[i][j] = -1;
				}
			}
			
		}

		int ans = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
//				System.out.print(block[i][j]+" ");
				if(block[i][j]==0) ans++;
			}
//			System.out.println();
		}
		System.out.println(ans);
	}
}
