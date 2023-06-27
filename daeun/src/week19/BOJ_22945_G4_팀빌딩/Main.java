import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		

		int N = Integer.parseInt(br.readLine());
		
		int[] power = new int[N];
		String[] split = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			power[i] = Integer.parseInt(split[i]);
		}
		
		int result = 0, cal = 0;
		int start = 0, end = N-1;
		
		//사람의 절반
		for(int i=0;i<N/2+1;i++) {
			cal = (end - start - 1) * Math.min(power[start], power[end]);
			result = Math.max(result, cal);
			
			if(power[start] < power[end]) {
				start++;
			}
			else {
				end--;
			}
		}
		
		System.out.println(result);
	}
}
