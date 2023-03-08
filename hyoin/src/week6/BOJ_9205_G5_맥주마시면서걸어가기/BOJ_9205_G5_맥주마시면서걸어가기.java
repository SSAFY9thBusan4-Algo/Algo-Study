package week6.BOJ_9205_G5_맥주마시면서걸어가기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205_G5_맥주마시면서걸어가기 {

	public static int[] homeXY; // 상근이의 집 위치
	public static int[] festivalXY; // 페스티벌 위치
	public static List<int[]> convinStores; // 편의점 위치들
	public static boolean[] isVisited; // 해당 편의점을 방문했는지

	public static StringBuilder sb = new StringBuilder();

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int t = Integer.parseInt(st.nextToken());
		for (int test_case = 0; test_case < t; test_case++) {
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());

			homeXY = new int[2];
			st = new StringTokenizer(in.readLine());
			homeXY[0] = Integer.parseInt(st.nextToken());
			homeXY[1] = Integer.parseInt(st.nextToken());

			convinStores = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				int convinStoreX = Integer.parseInt(st.nextToken());
				int convinStoreY = Integer.parseInt(st.nextToken());
				convinStores.add(new int[] { convinStoreX, convinStoreY });
			}
			
			festivalXY = new int[2];
			st = new StringTokenizer(in.readLine());
			festivalXY[0] = Integer.parseInt(st.nextToken());
			festivalXY[1] = Integer.parseInt(st.nextToken());

			isVisited = new boolean[n];
			if(bfs(homeXY[0], homeXY[1])) {
				sb.append("happy").append("\n");
			}
			else {
				sb.append("sad").append("\n");
			}
		}
		System.out.println(sb);
	}

	// 편의점을 들리지 않고 총 1000미터를 갈 수 있음
	// 즉 현재위치에서 페스티벌까지의 거리가 1000미터 이상일 때 주변 편의점까지 거리가 1000미터 이하인 편의점이 없으면 페스티벌에 도착 못함 
	private static boolean bfs(int homeX, int homeY) {

		Queue<Node> queue = new ArrayDeque<>(); // 현재위치에서 갈 수 있는 편의점을 넣을 큐
		queue.offer(new Node(homeX, homeY));

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			
			int curX = curNode.x;
			int curY = curNode.y;
			
			if(Math.abs(curX-festivalXY[0])+Math.abs(curY-festivalXY[1])<=1000) { // 현재위치에서 페스티벌까지 바로 갈 수 있을 때
				return true;
			}
			
			for(int i=0; i<convinStores.size(); i++) {
				if(!isVisited[i]) { // 아직 방문하지 않은 편의점만 탐색
					int convinX = convinStores.get(i)[0];
					int convinY = convinStores.get(i)[1];
					
					if(Math.abs(curX-convinX)+Math.abs(curY-convinY)<=1000) { // 현재 위치에서 해당 편의점으로 갈 수 있을 때
						isVisited[i]=true;
						queue.offer(new Node(convinX, convinY));
					}
				}
			}
			
		}
		
		// 편의점을 모두 방문했지만 페스티벌에 도착할 수 없는 경우
		return false;
	}
}
