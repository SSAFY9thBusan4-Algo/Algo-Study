import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	 private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] d = new int[n];
		for(int i=1; i<n; i++) {
			if(arr[i-1] > arr[i]) d[i] = d[i-1]+1;
			else d[i] = d[i-1];
		}
		
		int question = Integer.parseInt(br.readLine());
		for(int i=0; i<question; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			sb.append(d[y]-d[x]).append("\n");
		}
		
		System.out.println(sb);
	}
}
