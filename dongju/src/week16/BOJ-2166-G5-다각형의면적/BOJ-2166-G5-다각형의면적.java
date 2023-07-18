import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		
		long[] x = new long[n+1];
		long[] y = new long[n+1];
		
		long sumA = 0, sumB = 0;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		x[n] = x[0];
		y[n] = y[0];
		for(int i=0; i<n; i++) {
			sumA += x[i] * y[i+1];
			sumB += x[i+1] * y[i];
		}
		
		String ans = String.format("%.1f", Math.abs(sumA - sumB) / 2.0);
		
		System.out.println(ans);
	}
}
