package week1.BOJ_1476_S5_날짜계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int temp_E=0;
		int temp_S=0;
		int temp_M=0;
		
		int result = 0;
		while(true) {
			temp_E++;
			temp_S++;
			temp_M++;
			result++;
			
			if(temp_E==16) {
				temp_E=1;
			}
			if(temp_S==29) {
				temp_S=1;
			}
			if(temp_M==20) {
				temp_M=1;
			}
			
			if(temp_E==E&&temp_S==S&&temp_M==M)
				break;
		}
		
		System.out.println(result);
	}

}

