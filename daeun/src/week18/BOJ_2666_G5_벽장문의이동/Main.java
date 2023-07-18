import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int n, cmd, num[], result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); //벽장 개수
		String[] split = br.readLine().split(" ");
		int one = Integer.parseInt(split[0]);
		int two = Integer.parseInt(split[1]); 
		cmd = Integer.parseInt(br.readLine()); //벽장 순서 길이
		num = new int[cmd];
		for(int i=0;i<cmd;i++) {
			num[i] = Integer.parseInt(br.readLine());
		} 
		
		dfs(one, two, 0, 0);
		
		System.out.println(result);
	}

	private static void dfs(int one, int two, int idx, int cnt) {
		if(cnt > result) {
			return;
		}
		
		if(idx == cmd) {
			result = Math.min(result, cnt);
			return;
		}
		
		//왼쪽
		dfs(num[idx], two, idx+1, cnt + Math.abs(one - num[idx]));
		
		//오른쪽
		dfs(one, num[idx], idx+1, cnt + Math.abs(two - num[idx]));
		
	}
}
