import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static void makeSet(int N) {
		set = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			set[i] = i;
		}
	}

	private static int findParent(int n) {
		if (set[n] == n || set[n] == 0)
			return set[n];

		return set[n] = findParent(set[n]);
	}

	private static void union(int n1, int n2) {
		int p1 = findParent(n1);
		int p2 = findParent(n2);

		if (p1 > p2) {
			set[p1] = p2;
			set[n1] = p2;
		} else {
			set[p2] = p1;
			set[n2] = p1;
		}
	}

	private static int[] set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 사람의 수 N과 파티의 수 M
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);

		// union set
		makeSet(N);

		// 이야기의 진실을 아는 사람 -> 부모를 0번
		input = br.readLine().split(" ");
		int T = Integer.parseInt(input[0]);
		for (int i = 0; i < T; i++) {
			set[Integer.parseInt(input[i + 1])] = 0;
		}

		// 각 파티마다 오는 사람의 수와 번호
		int[][] partyMember = new int[M][];
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			T = Integer.parseInt(input[0]);
			partyMember[i] = new int[T];

			// 같은 파티에 오는 사람은 같은 이야기를 들어야 한다.
			partyMember[i][0] = Integer.parseInt(input[1]);
			for (int t = 1; t < T; t++) {
				partyMember[i][t] = Integer.parseInt(input[t + 1]);
				union(partyMember[i][0], partyMember[i][t]);
			}
		}
		

		// 과장된 이야기를 할 수 있는 파티 개수의 최댓값
		int cnt = M;
		for (int i = 0; i < M; i++) {

			// 현재 파티가 듣는 이야기가 0번(거짓말)인가.
			int n = findParent(partyMember[i][0]);
			if(n == 0) cnt--;
		}

		System.out.println(cnt);
	}
}
