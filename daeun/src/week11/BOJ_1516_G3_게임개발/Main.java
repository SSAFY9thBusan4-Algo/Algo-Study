import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Building {
		int num;
		Building link;

		public Building(int num, Building link) {
			super();
			this.num = num;
			this.link = link;
		}
	}

	static int N, time[], in[], dp[];
	static Building[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		list = new Building[N + 1]; // 1번부터 쓰려고
		time = new int[N + 1];
		in = new int[N + 1];
		dp = new int[N + 1];
		for (int i = 1; i < N+1; i++) {
			String[] split = br.readLine().split(" ");
			int num = Integer.parseInt(split[0]);
			if (split.length > 2) {
				for (int j = 1; j < split.length - 1; j++) {
					int next = Integer.parseInt(split[j]);
					list[next] = new Building(i, list[next]);
					in[i]++;
				}
			}
			time[i] = num;
		}

		topology();
		for(int i=1; i<N+1;i++) {
			sb.append(dp[i]).append("\n");
		}
		System.out.println(sb);
	}

	private static void topology() {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i < N + 1; i++) {
			if (in[i] == 0) {
				dp[i] = time[i];
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (Building temp = list[current]; temp != null; temp = temp.link) {
				int now = temp.num;
				dp[now] = Math.max(dp[current] + time[now], dp[now]);
				if (--in[now] == 0) {
					queue.offer(now);
				}
			}
		}
	}
}
