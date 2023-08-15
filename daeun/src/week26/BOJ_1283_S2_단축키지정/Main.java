import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		Set<Character> words = new HashSet<>();
		words.add(' ');
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			String[] split = str.split(" ");
			boolean flag = false;
		
			StringBuilder sb2 = new StringBuilder();
			//각 단어의 첫 글자 확인
			for(int j=0; j<split.length;j++) {
				
				char temp = split[j].charAt(0);
				//단축키에 없으면 지정
				if(!flag && !words.contains(temp)) {
					//대소문자 구분 없어서 둘 다 추가
					words.add(Character.toLowerCase(temp));
					words.add(Character.toUpperCase(temp));
					sb2.append('[').append(temp).append(']');
					for(int k=1;k<split[j].length();k++) {
						sb2.append(split[j].charAt(k));
					}
					flag = true;
				}
				else {
					for(int k=0;k<split[j].length();k++) {
						sb2.append(split[j].charAt(k));
					}
				}
				sb2.append(" ");
			}
			if(flag) {
				sb.append(sb2.toString()).append("\n");
				continue;
			}
			//글자 하나씩 확인
			out:
			for(int j=0;j<str.length();j++) {
				char temp = str.charAt(j);
				if(!words.contains(temp)) {
					words.add(Character.toLowerCase(temp));
					words.add(Character.toUpperCase(temp));
					sb.append('[').append(temp).append(']');
					for(int k=j+1;k<str.length();k++) {
						sb.append(str.charAt(k));
					}
					flag = true;
					break out;
				}
				else {
					sb.append(temp);
				}
			}
			
			sb.append("\n");			
		}
		System.out.println(sb);
	}
}
