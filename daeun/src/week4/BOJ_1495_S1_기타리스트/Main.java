package week4.BOJ_1495_S1_기타리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	private static int n, s, m, max;
	private static int[] vol, now;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		String[] nsm = br.readLine().split(" ");
		n = Integer.parseInt(nsm[0]); // 곡의 개수
		s = Integer.parseInt(nsm[1]); // 시작 볼륨
		m = Integer.parseInt(nsm[2]); // 최대 볼륨
		vol = new int[n+1];
		max = -1;
		String[] split = br.readLine().split(" ");
		for (int i = 1; i < n+1; i++) {
			vol[i] = Integer.parseInt(split[i-1]);
		}
		//현재볼륨 +- vol[i] (1~m 사이)

		int plus, minus;
		now = new int[m+1]; 
		now[s] = 0; //볼륨 S는 0번째 노래 (시작)
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> list2 = null;
		list.add(s);
		for(int i=1;i<n+1;i++){
			list2 = new ArrayList<>();
			for(int a : list) {
				plus = a + vol[i];
				minus = a - vol[i];
				/*if(plus> m && minus < 0) {
					max = -1;
					break;
				}*/
				if(plus <= m) {
					now[plus] = i;
					list2.add(plus);
				}
				if(minus >= 0) {
					now[minus] = i;
					list2.add(minus);
				}		
			}
		
			if(list2.size() == 0) {
				max = -1;
				break;
			}
			
			
			if(i==n) {
				for(int a: list2) {
					max = Math.max(a, max);
				}
			}
			
			list = list2;
		}
				
		System.out.println(max);
	}
}