import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int A = 1;
		int B = 1;
		int C = 1;

		for(int i=1; i<10000; i++) {
			A = i % 15;
			B = i % 28;
			C = i % 19;

			if(A == 0) {
				A = 15;
			}
			if(B == 0) {
				B = 28;
			}
			if(C == 0) {
				C = 19;
			}

			if(A==E && B==S && C == M) {
				System.out.println(i);
				break;
			}
		}

	}
}