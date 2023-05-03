import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		String bombString = in.readLine();

		for (int i = 0; i < input.length(); i++) {
			sb.append(input.charAt(i));
			if (sb.length() >= bombString.length()) {
				boolean isSame = true;
				for (int j = 0; j < bombString.length(); j++) {
					char c1 = sb.charAt(sb.length() - bombString.length() + j); // sb 뒤에서 5글자 탐색
					char c2 = bombString.charAt(j);
					
					if(c1!=c2) { // bombString과 비교
						isSame = false;
						break;
					}
				}
				
				if(isSame) {
					sb.delete(sb.length() - bombString.length(), sb.length()); // 같으면 해당 부분 삭제
				}
			}
		}

		if (sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb);
		}

	}
}
