package week5.SWEA_2383_점심식사시간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	static class Human implements Comparable<Human> {
		int x;
		int y;
		int dis;
		int time;

		public Human(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Human o) {
			return this.dis - o.dis;
		}
	}

	static class Stair {
		int x;
		int y;
		int len;

		public Stair(int x, int y, int len) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}

	static int N, min, map[][], humanSize, t;
	static ArrayList<Human> human;
	static Stair[] stair;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t < T + 1; t++) {
			sb.append("#" + t + " ");
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine()); // 한 변 길이 (4~10)
			map = new int[N][N];
			human = new ArrayList<>(); // 사람 리스트
			stair = new Stair[2]; // 계단 배열
			int idx = 0;
			for (int i = 0; i < N; i++) {
				String[] split = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
					if (map[i][j] == 1) { // 사람은 리스트에 넣기
						human.add(new Human(i, j));
					} else if (map[i][j] > 1) { // 계단
						stair[idx++] = new Stair(i, j, map[i][j]);
					}
				}
			} // 입력 끝

			humanSize = human.size();
			visit = new boolean[humanSize];

			// 조합? 아니면 부분집합..?
			Subset(0);

			sb.append(min).append("\n");
		}

		System.out.println(sb);
	}

	static PriorityQueue<Integer> pq1, pq2;

	private static void Subset(int cnt) {
		if (cnt == humanSize) {
			pq1 = new PriorityQueue<>();
			pq2 = new PriorityQueue<>();
			int dis1 = 0, dis2 = 0;
			
			Human now;
			for (int i = 0; i < humanSize; i++) {
				dis1 = 0;
				if (visit[i]) { // 1번 계단
					now = human.get(i);
					dis1 = Math.abs(stair[0].x - now.x) + Math.abs(stair[0].y - now.y);
					pq1.add(dis1);
				} else { // 2번 계단
					now = human.get(i);
					dis2 = Math.abs(stair[1].x - now.x) + Math.abs(stair[1].y - now.y);
					pq2.add(dis2);
				}
			}
			
			
			// 각각 위치에 따른 거리까지 잘 했는데...
			//시간 지나기 전에 pq에서 안 빼면..? 되려나
			
			downStair();
			
			return;
		}
		visit[cnt] = true;
		Subset(cnt + 1);

		visit[cnt] = false;
		Subset(cnt + 1);
	}

	private static void downStair() {
		int[] stair1 = new int[3];
		int[] stair2 = new int[3];
		int time = 0;
		int ber = humanSize;
		while(true) {
			if(ber == 0) { //전부 계단 쓰면 끝
				boolean flag = false;
				for(int i=0;i<3;i++) {
					if(stair1[i] != 0) { //아직 사람이 있으면
						flag = true;
						break;
					}
					if(stair2[i] != 0) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					break;
				}
			}
			
			for(int i=0;i<3;i++) {
				if(stair1[i] == 0) { //계단에 빈자리 있다
					if(!pq1.isEmpty()) { //pq에 사람이 남아 있으면
						if(pq1.peek() <=time) {//그게 도착했으면
							ber--; //남은 사람에서 한 명 빼기
							stair1[i] = stair[0].len;
							pq1.poll();
						}
					}
				}
				else { //빈자리가 없다
					stair1[i]--; //계단에서 한 칸 내리기
					if(stair1[i] == 0) {
						if(!pq1.isEmpty()) { //pq에 사람이 남아 있으면
							if(pq1.peek() <=time) {//그게 도착했으면
								ber--; //남은 사람에서 한 명 빼기
								stair1[i] = stair[0].len;
								pq1.poll();
							}
						}
					}
				}
				
				if(stair2[i] == 0) { //계단에 빈자리 있다
					if(!pq2.isEmpty()) { //pq에 사람이 남아 있으면
						if(pq2.peek() <=time) {//그게 도착했으면
							ber--; //남은 사람에서 한 명 빼기
							stair2[i] = stair[1].len;
							pq2.poll();
						}
					}
				}
				else { //빈자리가 없다
					stair2[i]--; //계단에서 한 칸 내리기
					if(stair2[i] == 0) {
						if(!pq2.isEmpty()) { //pq에 사람이 남아 있으면
							if(pq2.peek() <=time) {//그게 도착했으면
								ber--; //남은 사람에서 한 명 빼기
								stair2[i] = stair[1].len;
								pq2.poll();
							}
						}
					}
				}
			}
			
			time++; //계단 다 돌았으니까 시간 더하기
		}			

		min = Math.min(min, time);
		
	}

}