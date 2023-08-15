import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] split = br.readLine().split(" ");
		int[] num = new int[N+1];
		int sum = 0;
		for(int i=0;i<N;i++) {
			int temp = Integer.parseInt(split[i]);
			num[i+1] = sum + temp;
			sum += temp;
		}
		int M = Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++) {
			split = br.readLine().split(" ");
			int start = Integer.parseInt(split[0]);
			int end = Integer.parseInt(split[1]);
			sb.append(num[end] - num[start-1]).append("\n");
		}
		System.out.println(sb);
	}
}
