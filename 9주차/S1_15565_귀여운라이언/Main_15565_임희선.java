package com.ssafy._0402_0413_9주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//S1_귀여운라이언
public class Main_15565_임희선 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()); // 인형 개수
		int K = Integer.parseInt(st.nextToken()); // K개 이상의 연속된 라이언 인형을 포함하는 가장 작은 연속된 인형들의 집합 크기
		int[] dolls = new int[N];
		List<Integer> idxs = new ArrayList<>();
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			dolls[i] = Integer.parseInt(st.nextToken());
			if (dolls[i] == 1) {
				idxs.add(i);
			}
		}
		
		int len = 0;
		int min = Integer.MAX_VALUE;
		
		for (int s = 0, e = K - 1; s < idxs.size() - (K - 1); s++, e++) {
			len = idxs.get(e) - idxs.get(s) + 1;
			if (len > 1) {
				min = Math.min(min, len);
			}
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		
	}
	
}
