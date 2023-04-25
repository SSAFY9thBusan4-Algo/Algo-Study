import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
    
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] visit = new boolean[100001];
		queue.offer(new int[] { N, 0 });
		visit[N] = true;
		int result = 0;
    
		out: 
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int where = temp[0];
			int num = temp[1];
			if (temp[0] == M) {
				result = num;
				break out;
			}

			if (where * 2 <= 100000 && !visit[where * 2]) {
				queue.offer(new int[] { where * 2, num });
				visit[where * 2] = true;
			}
			if (where - 1 >= 0 && !visit[where - 1]) {
				queue.offer(new int[] { where - 1, num + 1 });
				visit[where - 1] = true;
			}
			if (where + 1 <= 100000 && !visit[where + 1]) {
				queue.offer(new int[] { where + 1, num + 1 });
				visit[where + 1] = true;
			}

		}

		System.out.println(result);
	}
}
