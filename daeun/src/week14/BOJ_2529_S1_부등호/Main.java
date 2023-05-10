import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	static int N;
	static String[] bracket;
	static boolean visit[], flag;
	static int[] numbers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		bracket = br.readLine().split(" ");
		visit = new boolean[10];
		numbers = new int[N+1];
		flag = false;
		reverse_permutation(0);
	
		visit = new boolean[10];
		flag = false;
		permutation(0);
	}
	
	private static boolean check(int[] numbers) {
		for(int i=0;i<N;i++) {
			String now = bracket[i]; //지금 부등호
			if(now.equals("<")) {
				if(numbers[i]>numbers[i+1]) {
					return false;
				}
			}
			else {
				if(numbers[i]<numbers[i+1]) {
					return false;
				}
			}
		}
		return true;
	}

	
	private static void permutation(int cnt) {
		if(cnt == N+1) {
			if(check(numbers)) {
				flag = true;
				for(int i=0;i<cnt;i++) {
					System.out.print(numbers[i]);
				}
				System.out.println();
			}
			return;
		}
		for(int i=0;i<10;i++) {
			if(visit[i]) {
				continue;
			}
			numbers[cnt] = i;
			visit[i] = true;
			permutation(cnt+1);
			if(flag) return; 
			visit[i] = false;
			
		}
	}
	private static void reverse_permutation(int cnt) {
		if(cnt == N+1) {
			if(check(numbers)) {
				flag = true;
				for(int i=0;i<cnt;i++) {
					System.out.print(numbers[i]);
				}
				System.out.println();
			}
			return;
		}
		for(int i=9;i>=0;i--) {
			if(visit[i]) {
				continue;
			}
			numbers[cnt] = i;
			visit[i] = true;
			reverse_permutation(cnt+1);
			if(flag) return;
			visit[i] = false;
			
		}
	}
}
