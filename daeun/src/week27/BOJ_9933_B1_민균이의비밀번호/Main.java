import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashSet<String> set = new HashSet<>();
		String result = "";
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			String sb = new StringBuilder(str).reverse().toString();
			set.add(str);
			if(set.contains(sb)) {
				result = str;
				break;
			}
		}
		
		int len = result.length();
		System.out.println(len+" "+result.charAt(len/2));
	}
}
