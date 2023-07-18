import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuffer A = new StringBuffer(br.readLine());
		StringBuffer B = new StringBuffer(br.readLine());

		//길이가 같아질 때까지 반복
		char temp;
		while(A.length()<B.length()) {
			temp = B.charAt(B.length()-1);
			B.deleteCharAt(B.length()-1);
			if(temp == 'B'){
				B.reverse();
			}
		}
		
		if(A.toString().equals(B.toString())) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
		
	}
}
