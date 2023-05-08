import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int N;
	private static char[] bdh;
	private static int[] min, max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		bdh = br.readLine().replace(" ", "").toCharArray();
		min = new int[N+1];
		max = new int[N+1];
		findMax();
		findMin();
		
		print();
	}
	
	private static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i <= N ; i++) {
			sb.append(max[i]);
		}
		sb.append('\n');
		for(int i = 0 ; i <= N ; i++) {
			sb.append(min[i]);
		}
		
		System.out.println(sb);
	}

  // max
	private static void findMax() {
		for(int i = 9 ; i >= 0 ; i--) {
			max[0] = i;
			if(findMaxNum(0,i,(1<<i))) break;
		}
	}
	
	private static boolean findMaxNum(int idx, int pre, int flag) {
		if(idx == N) {
			return true;
		}
		
		if(bdh[idx] == '<') {
			for(int i = 9 ; i > pre ; i--) {
				if((flag & (1 << i)) != 0) continue;	// 이미 사용한 숫자
				max[idx+1] = i; 
				if(findMaxNum(idx+1, i, (flag | (1 << i)))) return true;
			}
		}
		else {	// >
			for(int i = pre-1 ; i >= 0 ; i--) {
				if((flag & (1 << i)) != 0) continue;
				max[idx+1] = i; 
				if(findMaxNum(idx+1, i, (flag | (1 << i)))) return true;
			}
		}
		return false;
	}
	
  // min
	private static void findMin() {
		for(int i = 0 ; i < 10 ; i++) {
			min[0]=i;
			if(findMinNum(0,i,(1<<i))) break;
		}
	}

	private static boolean findMinNum(int idx, int pre, int flag) {
		if(idx == N) {
			return true;
		}
		
		if(bdh[idx] == '<') {
			for(int i = pre+1 ; i < 10 ; i++) {
				if((flag & (1 << i)) != 0) continue;	// 이미 사용한 숫자
				min[idx+1] = i; 
				if(findMinNum(idx+1, i, (flag | (1 << i)))) return true;
			}
		}
		else {	// >
			for(int i = 0 ; i < pre ; i++) {
				if((flag & (1 << i)) != 0) continue;
				min[idx+1] = i; 
				if(findMinNum(idx+1, i, (flag | (1 << i)))) return true;
			}
		}
		return false;
	}
}
