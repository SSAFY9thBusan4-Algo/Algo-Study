package week11.BOJ_9019_G4_DSLR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019_G4_DSLR {

	public static int A;
	public static int B;
	public static String result;
	public static boolean[] isVisited;

	public static class Command {
		int n; // 현재 숫자
		String comm = ""; // 누적된 명령어

		public Command(int n) {
			super();
			this.n = n;
		}

		public Command(int n, String comm) {
			super();
			this.n = n;
			this.comm = comm;
		}

		@Override
		public String toString() {
			return "Command [n=" + n + ", comm=" + comm + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(in.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			result = "";
			isVisited = new boolean[10000]; // 해당 숫자가 만들어진 적이 있는지
			bfs();
			sb.append(result).append("\n");
		}

		System.out.println(sb);

	}

	private static void bfs() {
		Queue<Command> queue = new ArrayDeque<>();
		queue.offer(new Command(A));
		isVisited[A] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Command cur = queue.poll();
				if (cur.n == B) { // 현재 숫자가 B와 같을 때 종료
					result = cur.comm;
					return;
				}
				for (int j = 0; j < 4; j++) { // 현재 숫자에서 DSLR 명령어 실행
					Command newComm = DSLR(j, cur);
					if (!isVisited[newComm.n]) { // 명령어 수행 후 결과가 이미 나온적이 있으면 패스
						queue.offer(newComm);
						isVisited[newComm.n] = true;
					}
				}
			}
		}

	}

	private static Command DSLR(int mode, Command cur) {
		int n = 0; // 명령어 실행 후 숫자
		String comm = ""; // 누적된 명령어

		switch (mode) {
		case 0: // D
			n = cur.n * 2 % 10000;
			comm = cur.comm + "D";
			break;
		case 1: // S
			if (cur.n == 0) {
				n = 9999;
			} else {
				n = cur.n - 1;
			}
			comm = cur.comm + "S";
			break;
		case 2: // L
			int d1 = cur.n / 1000;
			int d234 = cur.n - d1 * 1000;
			n = d234 * 10 + d1;
			comm = cur.comm + "L";
			break;
		case 3: // R
			int d123 = cur.n / 10;
			int d4 = cur.n - d123 * 10;
			n = d4 * 1000 + d123;
			comm = cur.comm + "R";
			break;
		}

		Command newComm = new Command(n, comm);
		return newComm;
	}
}
