package week16.BOJ_2531_S1_회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] plates = new int[N];
		for (int i=0; i<N; i++) {
			plates[i] = Integer.parseInt(in.readLine());
		}
		
		int result = 0;
		
		int[] check = new int[d+1];
		check[c] = 1;
		int cnt = 1;
		for (int i=0; i<k; i++) {
			if (check[plates[i]]==0) {
				check[plates[i]]=1;
				cnt+=1;
			}else {
				check[plates[i]]+=1;				
			}
		}
		result = Math.max(result, cnt);
		
		int l = 0, r = k-1;
		for (int i=1;i<N; i++) {
			if (check[plates[l]]==1) {
				cnt-=1;
				check[plates[l]] = 0;
			}else {
				check[plates[l]]-=1;
			}
			l+=1;
			r = (r+1)%N;
			if (check[plates[r]]==0) {
				cnt+=1;
				check[plates[r]] = 1;
			}else {				
				check[plates[r]] += 1;
			}
			result = Math.max(result, cnt);
		}
		
		System.out.println(result);
	}
	
}
