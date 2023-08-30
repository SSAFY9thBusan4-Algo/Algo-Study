import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		HashSet<String> set = new HashSet<>(); //메모장
		for(int i=0;i<N;i++) {
			set.add(br.readLine());
		}
    
		for(int i=0;i<M;i++) { //, 구분 쓴 글과 관련된 키워드
			split = br.readLine().split(",");
			for(int j=0;j<split.length;j++) {
				set.remove(split[j]);
			}
			sb.append(set.size()).append("\n");
		}
		
		System.out.println(sb);
	
	}
}
