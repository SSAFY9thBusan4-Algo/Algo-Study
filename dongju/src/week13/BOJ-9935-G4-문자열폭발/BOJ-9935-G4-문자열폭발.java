import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String bomb = br.readLine();
		int bombSize = bomb.length();
		
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<s.length(); i++) {
			stack.push(s.charAt(i));
			
			if(stack.size() >= bombSize) {
				boolean flag = true;
				for(int j=0; j<bombSize; j++) {
					if(stack.get(stack.size() - bombSize + j) != bomb.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					for(int j=0; j<bombSize; j++) stack.pop();
				}
			}
		}
		
		for(char c: stack) sb.append(c);
		System.out.println(sb.length()>0 ? sb.toString() : "FRULA");
	}
}
