import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.io.IOException;

public class Main {
	static String str, bomb;
	static int blen;
	static Stack<Character> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		bomb = br.readLine();
		blen = bomb.length();
		char last = bomb.charAt(blen-1);
		
		stack = new Stack<>();
		for(int i=0;i<str.length();i++) {
			char now = str.charAt(i);
			if(now != last) {
				stack.add(now);
			}
			else {
				stack.add(now);
				if(i>=blen-1) { //이 경우에만 앞에 나머지 문자열이 있을 수 있음
					Stack<Character> temp = new Stack<>();
					for(int j=0;j<blen;j++) {
						if(stack.peek() != bomb.charAt(blen-1-j)) { //다르면 다시 넣어야 한다..
							while(!temp.isEmpty()) {
								stack.add(temp.pop());
							}
						}
						else {
							temp.add(stack.pop());
						}
					}
				}
			}			
		}

		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		StringBuffer buffer = new StringBuffer(sb);
		String result = buffer.reverse().toString();
		
		if(result.length()==0) {
			System.out.println("FRULA");
		}
		else {
			System.out.println(result);
		}
	}
}
