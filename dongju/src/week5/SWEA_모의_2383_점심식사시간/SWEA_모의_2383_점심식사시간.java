package week5.SWEA_모의_2383_점심식사시간;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_모의_2383_점심식사시간 {
	
	static class Stair {
		int x, y, k;

		public Stair(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
	
	static class Person {
		int x, y;
		int stair; // 선택한 계단
		int arriveTime;
		int stairTime;
		
		public Person(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		void calArriveTime() {
			this.arriveTime = Math.abs(x - stairs[stair].x) + Math.abs(y - stairs[stair].y);
		}
	}

	private static StringBuilder sb = new StringBuilder();
	static int n, ans;
	static Stair[] stairs;
	static ArrayList<Person> persons;
	static boolean[] visited;
	static Queue<Person>[] que;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			stairs = new Stair[2];
			persons = new ArrayList<>();
			que = new ArrayDeque[2];
			que[0] = new ArrayDeque<>();
			que[1] = new ArrayDeque<>();
					
			int stairIdx = 0;
			n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					int num = Integer.parseInt(st.nextToken());
					
					if(num == 0) continue;
					else if(num == 1) persons.add(new Person(i, j));
					else {
						stairs[stairIdx++] = new Stair(i, j, num);
					}
				}
			}
			
			ans = Integer.MAX_VALUE;
			
			subSet(0); // 첫번쨰 계단과 두번쨰 계단 중 선택하는 부분 집합
			
			
			sb.append(ans).append("\n");
		}

		System.out.println(sb);
	}

	private static void subSet(int idx) {
		if(idx == persons.size()) { // 모든 사람 방문 완료
			visited = new boolean[persons.size()];
			
			int cur = simulation(); // 뽑힌 부분 집합으로 이동 시간 구하러 가기
			
			ans = Math.min(ans, cur);
			return;
		}


		// 첫번째 계단 사용
		persons.get(idx).stair = 0;
		persons.get(idx).calArriveTime();
		subSet(idx+1);
		
		// 두번쨰 계단 사용
		persons.get(idx).stair = 1;
		persons.get(idx).calArriveTime();
		subSet(idx+1);
	}

	private static int simulation() {
		int cnt = 0;
		int time = 1;
		
		while(true) {
			// 내려가고
			for(Queue<Person> q : que) {
				int size = q.size();
				
				for(int i =0; i<size; i++) {
					Person p = q.poll();
					Stair s = stairs[p.stair];
					
					if(p.stairTime + s.k <= time) {
						continue;
					}
					
					q.offer(p);
				}
			}
			
			if(cnt == persons.size() && que[0].isEmpty() && que[1].isEmpty()) {
				return time;
			}
			
			// 큐에 넣고
			for(int i=0; i<persons.size(); i++) {
				if(visited[i]) continue;
				
				Person p = persons.get(i);
				Queue<Person> q = que[p.stair];
				
				if(p.arriveTime + 1 <= time && q.size() < 3) {
					p.stairTime = time;
					visited[i] = true;
					q.offer(p);
					cnt++;
				}
			}
			
			time++;
		}
	}
}
