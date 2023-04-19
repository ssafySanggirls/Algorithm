package Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2002_hm {

    static int N;
    static String[] dg, ys;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dg = new String[N];
        ys = new String[N];
        
        for(int i = 0;i<N;i++) {
        	dg[i] = br.readLine();
        }
        for(int i = 0;i<N;i++) {
        	ys[i] = br.readLine();
        }
        int cnt = 0, ysIdx= 0;
        while(true) {
        	String s= dg[ysIdx];
        	int nullCnt = 0, i=0, copyCnt = cnt, car = 0;
        	for (i=0; i < N; i++) {
        		//System.out.println(s+" "+ys[i]);
				if(ys[i]==null) {
					nullCnt++;
					continue;
				}
				else if(!s.equals(ys[i]) && ys[i] != null) {
					//cnt++;
					nullCnt++;
					car++;
				}
				else if(s.equals(ys[i])) {
					for (int j = 0; j <= i; j++) {
						ys[j] = null;
					}
					cnt += car;
					break;
					
				}

			}
        	//System.out.println(ysIdx+" "+cnt+" "+nullCnt+" "+car);
        	//if(nullCnt >= N-1) cnt = copyCnt;
        	if(ysIdx == N-1) break;
        	ysIdx++;
        }
        System.out.println(cnt);
    }
}
