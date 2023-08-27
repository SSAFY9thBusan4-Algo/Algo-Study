import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//세 용액을 혼합해 0에 가깝게
		int N = Integer.parseInt(br.readLine());
		long[] list = new long[N];
		long[] three = new long[3];
		String[] split = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			list[i] = Long.parseLong(split[i]);
		}
		Arrays.sort(list); //정렬
		
		long result = Long.MAX_VALUE;
		//하나를 잡고 두개를 이분탐색으로 해서 더하기..!
		for(int i=0;i<N;i++) {
			int start = i+1;
			int end = N-1;
			
			while(start < end) {
				//지금 + 처음 + 끝
				long sum = list[i] + list[start] + list[end];
				long minus = Math.abs(sum);
				if(minus < result) {
					result = minus;
					three[0] = list[i];
					three[1] = list[start];
					three[2] = list[end];
				}
				if(sum > 0) {
					end--;
				}
				else {
					start++;
				}
			}
		}
		System.out.println(three[0]+" "+three[1]+" "+three[2]);
	}
}
