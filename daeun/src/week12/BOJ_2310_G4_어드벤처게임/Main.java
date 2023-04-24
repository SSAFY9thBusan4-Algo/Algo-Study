import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.io.IOException;

public class Main {
	static class Connect {
		int num;
		Connect link;

		public Connect(int num, Connect link) {
			super();
			this.num = num;
			this.link = link;
		}
	}

	static class Room {
		String content;
		int price;

		public Room(String content, int price) {
			super();
			this.content = content;
			this.price = price;
		}
	}

	static Connect[] rooms;
	static Room[] info;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			int n = Integer.parseInt(br.readLine()); //미로의 방 수
			if (n == 0) {
				break;
			}

			int money = 0;
			rooms = new Connect[n + 1];
			info = new Room[n + 1];
			visit = new boolean[n + 1];

			for (int i = 1; i < n + 1; i++) {
				String[] split = br.readLine().split(" ");
				String con = split[0];
				int price = Integer.parseInt(split[1]);
				info[i] = new Room(con, price);
				for (int j = 2; j < split.length - 1; j++) {
					rooms[i] = new Connect(Integer.parseInt(split[j]), rooms[i]);
				}
			}

			Queue<Integer> queue = new ArrayDeque<Integer>();
			queue.offer(1);
			visit[1] = true;
			boolean flag = false;
			while (!queue.isEmpty()) {
				int now = queue.poll();
				if(now == n) {
					flag = true;
					break;
				}
				for (Connect c = rooms[now]; c != null; c = c.link) {
					int num = c.num;
					if (!visit[num]) { //방문하지 않은 경우
						Room temp = info[num];
						int tPrice = temp.price;
						if (temp.content.equals("L")) {
							if (money < tPrice) {
								money = tPrice;
							}
							queue.offer(num);
							visit[num] = true;
						} else if (temp.content.equals("T")) {
							if (money >= tPrice) {
								money -= tPrice;
								queue.offer(num);
								visit[num] = true;
							}
						}

					}
				}
			}
			if(flag) {
				sb.append("Yes").append("\n");
			}
			else {
				sb.append("No").append("\n");
			}
		}
		System.out.println(sb);
	}
}
