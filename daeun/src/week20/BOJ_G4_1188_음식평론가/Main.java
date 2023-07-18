import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		//최대공약수
		int m = M;
		while(m > 0) {
			int temp = N;
			N = m;
			m = temp % m;
		}

		//최대공약수를 구했으니까 그만큼 잘라야 하니까 M - N을 해야하나보다..
		System.out.println(M - N);
	}
}
