import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//누적합
		String S = br.readLine();
		int q = Integer.parseInt(br.readLine());
		int[][] alpha = new int[S.length()+1][26];
		
		alpha[0][S.charAt(0)-'a']++;
		for(int i=1;i<S.length();i++) {
			for(int j=0;j<26;j++) {
				alpha[i][j] = alpha[i-1][j];
			}
			alpha[i][S.charAt(i)-'a']++;
		}
		
		for(int i=0;i<q;i++) {
			String[] split = br.readLine().split(" ");
			int a = split[0].charAt(0)-'a';
			int start = Integer.parseInt(split[1]);
			int end = Integer.parseInt(split[2]);
			if(start == 0) {
				sb.append(alpha[end][a]);
			}
			else {
				sb.append(alpha[end][a] - alpha[start-1][a]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
}

