import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String s = br.readLine();
		int q = Integer.parseInt(br.readLine());
		
		int[][] d = new int[s.length()][26];
		d[0][s.charAt(0)-'a'] = 1;
		for(int i=1; i<s.length(); i++) {
			for(int j=0; j<26; j++) {
				d[i][j] += d[i-1][j];
			}
			
			d[i][s.charAt(i)-'a']++;
		}
		
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(start == 0) sb.append(d[end][c-'a']);
			else sb.append(d[end][c-'a'] - d[start-1][c-'a']);
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
