package week1.BOJ_1107_G5_리모컨;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N, M, min, over;
	static boolean[] broken  = new boolean[10];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		over = Integer.toString(N).length()+1;
		M = Integer.parseInt(br.readLine()); 
		if(M>0) {
			String[] split = br.readLine().split(" ");
			for(int i = 0;i<split.length;i++) {
				broken[Integer.parseInt(split[i])] = true;
			}
		}
		min = Math.abs(N-100);
		
		for(int i=0;i<10;i++) {
			if(broken[i]) {
				continue;
			}
			solve(1, i);
		}
		System.out.println(min);
	}
	private static void solve(int count, int channel) {
		min = Math.min(min, count+Math.abs(N-channel));
		for(int i=0;i<10;i++) {
			if(broken[i]) {
				continue;
			}
            if(over<count+1) { 
			    return;
		    }
			solve(count+1, channel*10 + i);
		}
	}
}
