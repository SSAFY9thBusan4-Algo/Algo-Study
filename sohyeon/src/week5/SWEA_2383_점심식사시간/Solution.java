package week5.SWEA_2383_점심식사시간;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
public class Solution {

	private static StringBuilder sb = new StringBuilder();
	
	private static int N, pCount, result;
	private static int[][] map;
	private static int[][] stairs;  
	private static boolean[][] using;  // 계단을 사용중인 사람 표시
	private static List<int[]> people;
	private static int[] curpermut;  // 각사람이 내려갈 계단 순열을 저장
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//System.setIn(new FileInputStream(new File("res/input.txt")));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#"+tc+" ");
	
			result = Integer.MAX_VALUE;
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
			}								
			
			// 계단 위치 , 사람들 위치 list에 저장
			stairs = new int[2][3]; 
			people = new ArrayList<int[]>();
			
			int cnt = 0;
			pCount = 0; // 사람수
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 1) {
						stairs[cnt][0] = i;
						stairs[cnt][1] = j;
						stairs[cnt][2] = map[i][j];		
						cnt++;
					}
					else if (map[i][j] == 1) {
						people.add(new int[] {i,j});
						pCount++;
					}
				}
			}
			
			using = new boolean[2][pCount];
			curpermut = new int[pCount];
			
			permut(0);  // 중복 순열로 각 사람들이 어떤 계단으로 내려가는지 가능한 경우를 모두 고려
			
			sb.append(result+"\n");
			
		}
		
		System.out.println(sb);
	}

	static class People implements Comparable<People>{
		int num;
		int s;
		int t1;
		int t2;
		int l;
		public People(int num, int s, int t1, int t2, int l) {
			super();
			this.num = num;
			this.s = s;
			this.t1 = t1;
			this.t2 = t2;
			this.l = l;
		}
		@Override
		public int compareTo(People o) {			
			return this.t1-o.t1;
		}		
	}
	
	private static void permut(int cnt) {
		if (cnt == pCount) {
			int totalTime = 0;
			
			Queue<People> queue = new ArrayDeque<>();
			
			People[] list = new People[pCount];
			for (int i = 0; i < pCount; i++) {
				int s = curpermut[i];  // 현재 사람이 내려갈 계단
				int[] rc = people.get(i);  // 현재 사람의 위치
				int t1 = Math.abs(rc[0]-stairs[s][0]) + Math.abs(rc[1]-stairs[s][1]);  // 계단까지 걸리는 시간
				int t2 = stairs[s][2];  // 내려가는 시간
				list[i] = new People(i, s, t1, t2, 0);
			}
			Arrays.sort(list);
			
			for (People p : list) {
				queue.offer(p);
			}
			
			while (!queue.isEmpty()) {
				
				People poll = queue.poll();
				int num = poll.num;
				int s = poll.s;
				int t1 = poll.t1;
				int t2 = poll.t2;
				int l = poll.l;
				
				if(t1 == 0 && t2 == 0) {
					using[s][num] = false;
					l++;
					if(totalTime < l) {
						totalTime = l;						
					}
					continue;
				}
				
				if (t1 > 0) t1 -= 1;
				else if (t1 == 0) {
					if (using[s][num]) {
						t2 -= 1;
					}
					else {
						int c = 0; // 계단 이용중인 사람 count
						for (int i = 0; i < pCount; i++) if (using[s][i] == true) c++;
						if (c < 3) {
							using[s][num] = true;
							t2 -= 1;
						}
					}					
				}
				queue.offer(new People(num, s, t1, t2, l+1));
				
			}
			result = Math.min(result, totalTime);
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			curpermut[cnt] = i;
			permut(cnt+1);
		}
		
	}


}
