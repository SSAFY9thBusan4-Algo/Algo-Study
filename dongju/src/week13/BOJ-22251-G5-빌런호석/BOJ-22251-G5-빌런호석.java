import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, k, p, x;
	static boolean[][] LED;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 최대 수
		k = Integer.parseInt(st.nextToken()); // 자리수
		p = Integer.parseInt(st.nextToken()); // 최대 반전가능 개수
		x = Integer.parseInt(st.nextToken()); // 현재 층
		
		LED = new boolean[10][7];
		LED[0] = new boolean[] {true, true, true, false, true, true, true};
		LED[1] = new boolean[] {false, false, true, false, false, true, false};
		LED[2] = new boolean[] {true, false, true, true, true, false, true};
		LED[3] = new boolean[] {true, false, true, true, false, true, true};
		LED[4] = new boolean[] {false, true, true, true, false, true, false};
		LED[5] = new boolean[] {true, true, false, true, false, true, true};
		LED[6] = new boolean[] {true, true, false, true, true, true, true};
		LED[7] = new boolean[] {true, false, true, false, false, true, false};
		LED[8] = new boolean[] {true, true, true, true, true, true, true};
		LED[9] = new boolean[] {true, true, true, true, false, true, true};
		
		int[] target = changeNum(x);
		
		int ans = 0;
		for(int i=1; i<=n; i++) { // 반전 숫자
			if(i == x) continue;
			
			int[] nums = changeNum(i); 
			
			if(reverseLED(nums, target)) ans++;
		}
		
		System.out.println(ans);
	}

	private static boolean reverseLED(int[] nums, int[] target) {
		int diffCnt = 0;
		for(int i=0; i<k; i++) { // 자리수
			for(int j=0; j<7; j++) { // LED 7칸 비교
				if(LED[nums[i]][j] != LED[target[i]][j]) {
					diffCnt++; // 반전 횟수
					if(diffCnt > p) return false;
				}
			}
		}
		
		return true;
	}

	private static int[] changeNum(int x) {
		int[] nums = new int[k];
		for(int i=k-1; i>=0; i--) {
			nums[i] = x % 10;
			x /= 10;
		}
		
		return nums;
	}
}
