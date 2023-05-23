import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<String> list = new ArrayList<>();
			for(int i=0;i<N;i++) {
				list.add(br.readLine());
			}
			Collections.sort(list);
			int len = 11;
			String now = "";
			boolean flag = false;
			for(String s: list) {
				if(s.length()>len && now.equals(s.substring(0,len))) {
					flag = true;
					break;
				}
				now = s;
				len = now.length();
			}
			if(flag) {
				sb.append("NO");
			}
			else {
				sb.append("YES");
			}
			sb.append("\n");
		}
		System.out.println(sb);	
	}
}
