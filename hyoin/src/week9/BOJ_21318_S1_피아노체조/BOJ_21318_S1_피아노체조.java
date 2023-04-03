package week9.BOJ_21318_S1_피아노체조;

import java.io.*;
import java.util.*;

public class BOJ_21318_S1_피아노체조 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine());
		int[] piano = new int[N + 1];
		int[] d = new int[N + 1]; // 현재 index까지 실수한 횟수

		for (int i = 1; i <= N; i++) {
			piano[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N; i++) {
			if (piano[i] > piano[i + 1]) { // 지금 연주하는 악보가 바로 다음에 연주할 악보보다 어려울 때 이전 실수횟수 +1
				d[i] = d[i - 1] + 1;
			} else {
				d[i] = d[i - 1];
			}
		}

		st = new StringTokenizer(in.readLine());
		int Q = Integer.parseInt(st.nextToken());

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			// end이전까지 실수했던 횟수 - start이전까지 실수했던 횟수
			int answer = d[end - 1] - d[start - 1]; 
			sb.append(answer).append("\n");
		}

		System.out.println(sb);

	}

}
