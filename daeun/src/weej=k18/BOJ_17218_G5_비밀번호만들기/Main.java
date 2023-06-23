import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String one = br.readLine();
		String two = br.readLine();
		
		int len1 = one.length();
		int len2 = two.length();
		
		int[][] dp = new int[len1+1][len2+1];
		//같은 문자가 나오면 값 증가
		for(int i=0;i<len1;i++) {
			for(int j=0;j<len2;j++) {
				if(one.charAt(i) == two.charAt(j)) {
					dp[i+1][j+1] = dp[i][j]+1;
				}
				else {
					dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
				}
			}
		}
		
		String result = "";
		
		//하나라도 끝까지 읽으면 끝
		while (len1!=0 && len2!=0) {
			if (one.charAt(len1-1) == two.charAt(len2-1)) {
				result = one.charAt(len1-1)+result;
				len1--;
				len2--;
			}
			else {
				//같지 않은 경우에는 같은 곳을 찾기 위해서 dp가 일치할 때까지 자리 이동
				if (dp[len1][len2-1]<dp[len1-1][len2]) {
					len1--;
				}else {
					len2--;
				}
			}
		}
		System.out.println(result);
		
	}
}
