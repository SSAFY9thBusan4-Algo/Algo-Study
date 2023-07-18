import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[10001];
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] d = new int[10001][3];
		d[1][1] = arr[1];
		d[2][1] = arr[2];
		d[2][2] = d[1][1] + arr[2];
		
		for(int i=3; i<=n; i++) {
			d[i][0] = getMax(d[i-1]); // 안마시는 경우
			d[i][1] = getMax(d[i-2]) + arr[i]; // 마시는 경우
			d[i][2] = d[i-1][1] + arr[i]; // 연속으로 마시는 경우
		}
		
		System.out.println(getMax(d[n]));
	}

	private static int getMax(int[] d) {		
		return Math.max(Math.max(d[0], d[1]), d[2]);
	}
}
