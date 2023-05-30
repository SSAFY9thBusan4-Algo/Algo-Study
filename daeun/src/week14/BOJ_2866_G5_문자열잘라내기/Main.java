import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int R = Integer.parseInt(split[0]);
		int C = Integer.parseInt(split[1]);
		String str;
		int result= 0;
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		String[] ing = new String[C];
		StringBuilder sb;
		for(int i=0;i<C;i++) {
			sb = new StringBuilder();
			for(int j=1;j<R;j++) { //첫 줄은 중복 없다니까~
				sb.append(map[j][i]);
			}
			ing[i] = sb.toString();
		} //세로로 문자열 만들어서 저장
		
		HashSet<String> set;
		out: 
		for(int i=0;i<R;i++) { //길이만큼 잘라보자
			set = new HashSet<>();
			for(int j=0;j<C;j++) { 
				str = ing[j].substring(i);
				if(set.contains(str)) {
					break out;
				}
				else {
					set.add(str);
				}
			}
			result++;
		}
		
		System.out.println(result);
	}
}
