package week5.BOJ_G5_21608_상어초등학교;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_21608_상어초등학교 {
	
	static class Student implements Comparable<Student> {
		int x;
		int y;
		int empty;
		int like;
		
		public Student(int x, int y, int empty, int like) {
			super();
			this.x = x;
			this.y = y;
			this.empty = empty;
			this.like = like;
		}

		@Override
		public int compareTo(Student o) {
			if(this.like == o.like && this.empty == o.empty && this.x == o.x) return this.y - o.y; // 열 번호(오름차순)
			if(this.like == o.like && this.empty == o.empty) return this.x - o.x; // 행 번호(오름차순)
			if(this.like == o.like) return o.empty - this.empty; // 2. 비어있는 칸 수(내림차순)
			return o.like - this.like; // 1. 좋아하는 학생 수(내림차순)
		}
	}

	static StringBuilder sb = new StringBuilder();
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	static int n;
	static int[] order;
	static int[][] arr;
	static List<Integer>[] list;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		

		n = Integer.parseInt(br.readLine());
		arr = new int[n+1][n+1];

		order = new int[n*n+1];
		list = new ArrayList[n*n+1];
		for(int i=1; i<=n*n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=n*n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			order[i] = idx;
			for(int j=0; j<4; j++) {
				list[idx].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i=1; i<=n*n; i++) {
			seat(order[i]);
		}
		
		int ans = 0;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				ans += satisfy(i, j);
			}
		}
		
		System.out.println(ans);
	}

	private static int satisfy(int x, int y) {
		int num = arr[x][y];
		
		int cnt = 0;
		for(Integer temp: list[num]) {
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx > 0 && nx <= n && ny > 0 && ny <= n) {
					if(arr[nx][ny] == temp) cnt++;
				}
			}
		}
		
		switch (cnt) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 10;
		case 3:
			return 100;
		default:
			return 1000;
		}
	}

	private static void seat(int num) {
		PriorityQueue<Student> pq = new PriorityQueue<>();
		
		int nx, ny;
		int empty, like;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				empty = 0;
				like = 0;
				
				if(arr[i][j] != 0) continue;
				
				for(int k=0; k<4; k++) {
					nx = i + dx[k];
					ny = j + dy[k];
					if (nx > 0 && nx <= n && ny > 0 && ny <= n) {
						for(Integer now: list[num]) {
							if(now == arr[nx][ny]) like++;
						}
						
						if(arr[nx][ny] == 0) empty++;
					}
				}
				
				pq.offer(new Student(i, j, empty, like));
			}
		}
		
		Student student = pq.poll();
		arr[student.x][student.y] = num;
	}
}