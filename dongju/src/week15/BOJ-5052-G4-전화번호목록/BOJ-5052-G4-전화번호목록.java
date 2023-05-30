import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine()); // 전화번호의 수
			
			String[] arr = new String[n];
			for(int i=0; i<n; i++) {
				arr[i] = br.readLine();
			}
			
			Arrays.sort(arr);
			
			boolean ans = true;
			for(int i=0; i<n-1; i++) {
				int min = Math.min(arr[i].length(), arr[i+1].length());
				
				String temp1 = "";
				String temp2 = "";
				for(int j=0; j<min; j++) {
					temp1 += arr[i].charAt(j);
					temp2 += arr[i+1].charAt(j);
				}
				
				if(temp1.equals(temp2)) {
					ans = false;
					break;
				}
			}
			
			if(ans) sb.append("YES");
			else sb.append("NO");
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
