package src.week13.BOJ_22251_G5_빌런호석;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int N, K, P, X, resultCnt;
	private static int[] NNum, XNum;
	private static int[][] convertCnt;
	
	private static final int[] digital = {0x77, 0x03, 0x5D, 0x1F, 0x2B, 0x3E, 0x7E, 0x07, 0x7F, 0x3F};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		
		N = Integer.parseInt(in[0]);
		K = Integer.parseInt(in[1]);
		P = Integer.parseInt(in[2]);
		X = Integer.parseInt(in[3]);
		converttoArr();
		countConvertCnt();
		
		resultCnt = 0;
		find(0, 0, true);
		
		// 자기자신으로 가는 경우
		if(X <= N) resultCnt--;
		// 0층으로 가는 경우
		if(canGoZero())resultCnt--;
		System.out.println(resultCnt);
	}
	

	// N이하인지 확인할 때 현재 자리수에 해당하는 숫자가 무엇인지 접근하기 위함.
	private static void converttoArr() {
		NNum = new int[K];
		int n = N;
		int i = K-1;
		while(n > 0) {
			if(i < 0) {
				// 자리수보다 N이 더 크다는 거니까
				for(int j = 0 ; j < K ; j++) NNum[j] = 9;
				break;
			}
			NNum[i] = n % 10;
			n /= 10;
			i--;
		}
		
		XNum = new int[K];
		int x = X;
		i = K-1;
		while(x > 0) {
			XNum[i] = x % 10;
			x /= 10;
			i--;
		}
	}
	
	private static void countConvertCnt() {
		convertCnt = new int[10][10];
		
		for(int i = 0 ; i < 10 ; i++) {
			for(int j = i ; j < 10 ; j++) {
				convertCnt[i][j] = Integer.bitCount(digital[i] ^ digital[j]);
				convertCnt[j][i] = convertCnt[i][j];
			}
		}
		
	}
	

	private static boolean canGoZero() {
		int cnt = 0;
		for(int i = 0 ; i < K; i++) {
			cnt += convertCnt[XNum[i]][0];
		}
		if(cnt <= P) return true;
		return false;
	}

	private static void find(int cur, int cnt, boolean confirm) {
		if(cur == K) {
			resultCnt++;
			return;
		}
		
		if(confirm) {
			int currentNum = XNum[cur];
			for(int i = 0 ; i < NNum[cur] ; i++) {
				int dif = convertCnt[currentNum][i];
				if(cnt + dif <= P) find(cur+1, cnt + dif, false);
			}

			int dif = convertCnt[currentNum][NNum[cur]];
			if(cnt + dif <= P) find(cur+1, cnt + dif, true);
			
		}
		else {
			int currentNum = XNum[cur];
			for(int i = 0 ; i < 10 ; i++) {
				int dif = convertCnt[currentNum][i];
				if(cnt + dif <= P) find(cur+1, cnt + dif, confirm);
			}
		}
		
	}
}
