package week13.BOJ_2879_G3_코딩은예쁘게;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int[] cur = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		int[] target = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		
		// 이동해야하는 크기와 방향
		int[] dd = new int[N];
		for (int i=0; i<N; i++) 
			dd[i] = target[i]-cur[i];
		
		int result = 0;
		boolean flag = true;
		while(flag) {
			int l=0,r=0;
			flag = false;
			
			while (r<N) {
				if (dd[l] == 0) {
					l++;
					r++;
					continue;
				}
				int minabs = Math.abs(dd[l]);
				while (r!=N && dd[l]*dd[r]>0) {
					flag = true;
					minabs = Math.min(minabs, Math.abs(dd[r]));
					r++;
				}
				result += minabs;
				for (int i=l; i<r; i++) {
					dd[i] = dd[i]<0 ? dd[i]+minabs : dd[i]-minabs;
				}
				l=r;
			}
		}
		
		System.out.println(result);
	}
	
}
