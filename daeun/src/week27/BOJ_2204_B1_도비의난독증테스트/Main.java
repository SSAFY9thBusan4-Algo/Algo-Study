import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) {
				break;
			}
			String[] list = new String[N];
			for(int i=0;i<N;i++) {
				list[i] = br.readLine();
			}
			
			Arrays.sort(list, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					
					return o1.toLowerCase().compareTo(o2.toLowerCase());
				}
			});
			
			sb.append(list[0]).append("\n");
		}
		
		System.out.println(sb);
	}
}
