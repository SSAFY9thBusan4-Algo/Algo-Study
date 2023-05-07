import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 줄의 개수
		
		int[] arr = new int[n];
		StringTokenizer now = new StringTokenizer(br.readLine()); // 현재 탭
		StringTokenizer correct = new StringTokenizer(br.readLine()); // 올바른 탭
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(correct.nextToken()) - Integer.parseInt(now.nextToken());
		}
		
		int ans = 0;
		int prev = arr[0];
		
		if(n == 1) ans = Math.abs(arr[0]);
		else {
			for(int i=1; i<n; i++) {
				if(prev*arr[i] < 0) ans += Math.abs(prev); // 기호가 바뀌는 경우
				else if(Math.abs(prev) >= Math.abs(arr[i])) { // 같은 값으로 맞춰준다
					ans += Math.abs(prev) - Math.abs(arr[i]);
				}
				
				prev = arr[i];
			}
			
			ans += Math.abs(prev);			
		}
		
		System.out.println(ans);
	}
}
