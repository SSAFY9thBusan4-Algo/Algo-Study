import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			String[] split = br.readLine().split(" ");
			BigInteger A = new BigInteger(split[0], 2); //2진수 입력을 10진수로
			BigInteger B = new BigInteger(split[1], 2);
			BigInteger result = A.add(B);
			sb.append(result.toString(2)).append("\n"); //2진수 변환
		}
		System.out.println(sb);
	}
}
