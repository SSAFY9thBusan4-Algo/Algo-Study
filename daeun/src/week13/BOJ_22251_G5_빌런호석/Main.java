import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	static int N, K, P, X, result;
	static int[][] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]); //N층까지
		K = Integer.parseInt(split[1]); //자릿수
		P = Integer.parseInt(split[2]); //최대 반전 개수
		X = Integer.parseInt(split[3]); //현재 층수
		
		//7개로 숫자 표시 => 각 숫자에서 다른 숫자로 변환할 때 필요한 반전 개수
		num = new int[10][10];
		num[0] = new int[] {0, 4, 3, 3, 4, 3, 2, 3, 1, 2};
		num[1] = new int[] {4, 0, 5, 3, 2, 5, 6, 1, 5, 4};
		num[2] = new int[] {3, 5, 0, 2, 5, 4, 3, 4, 2, 3};
		num[3] = new int[] {3, 3, 2, 0, 3, 2, 3, 2, 2, 1};
		num[4] = new int[] {4, 2, 5, 3, 0, 3, 4, 3, 3, 2};
		num[5] = new int[] {3, 5, 4, 2, 3, 0, 1, 4, 2, 1};
		num[6] = new int[] {2, 6, 3, 3, 4, 1, 0, 5, 1, 2};
		num[7] = new int[] {3, 1, 4, 2, 3, 4, 5, 0, 4, 3};
		num[8] = new int[] {1, 5, 2, 2, 3, 2, 1, 4, 0, 1};
		num[9] = new int[] {2, 4, 3, 1, 2, 1, 2, 3, 1, 0};
		
		go(0,1,0,0);
		
		System.out.println(result-1); //자기 자신 빼고
	}
	
	//where = 지금 자리수, ten = 몇번째인지, now = 지금 숫자, cnt = 변경 개수
	private static void go(int where, int ten, int now, int cnt) {
		if(now > N || cnt > P) { //지금 숫자가 최대 층수보다 크거나 변경 개수 초과
			return;
		}
		
		if(where == K) { //자리수가 동일한 경우
			if(now!=0) { //0층은 없음
				result++;
			}
			return;
		}
		
		for(int i=0;i<10;i++) {
			go(where+1, ten*10, i*ten+now, cnt+num[X/ten%10][i]);
		}
	}	
}
