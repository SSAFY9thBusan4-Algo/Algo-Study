import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] minus = new int[N];
		
		String[] wrong = br.readLine().split(" ");
		String[] right = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			minus[i] = Integer.parseInt(right[i]) - Integer.parseInt(wrong[i]); //차이
		}
		
		int prev = minus[0], result = 0;
		for(int i=1;i<N;i++) {
			if(prev*minus[i]<0) { //부호가 다른 경우
				result+=Math.abs(prev);
			}else if(Math.abs(prev)>=Math.abs(minus[i])) {
				result += Math.abs(prev) - Math.abs(minus[i]);
			}
			prev = minus[i];
		}
		
		result+=Math.abs(prev);
		System.out.println(result);
	}
}
