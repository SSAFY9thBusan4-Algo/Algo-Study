package src.week5.BOJ_21608_G5_상어초등학교;

import java.io.*;
import java.util.*;

public class Main {
	
	private static class Student {
		int x, y;
		
		public Student(int x, int y) {
			this.x = x;
			this.y = y;
		}

		
	}
	
	private static class Point implements Comparable<Point>{
		int x, y, likecount, emptycount;
		
		public Point(int x, int y, int count, int emptycount) {
			this.x = x;
			this.y = y;
			this.likecount = count;
			this.emptycount = emptycount;
		}
		

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			
			// x, y 좌표가 같으면 같은거.
			Point other = (Point) obj;
			return x == other.x && y == other.y;
		}


		@Override
		public int compareTo(Point o) {
			// 친구가 많은게 먼저
			int r = Integer.compare(o.likecount, this.likecount);
			if(r != 0) return r;
			
			//빈자리 많은게 먼저
			r = Integer.compare(o.emptycount, this.emptycount);
			if(r != 0) return r;

			// 행 렬 작은게 먼저
			r = Integer.compare(this.x, o.x);
			if(r != 0) return r;
			
			return Integer.compare(this.y, o.y);
		}


		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", likecount=" + likecount + ", emptycount=" + emptycount + "]";
		}
		
	}
	
	private static final int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 입력
		int N = Integer.parseInt(br.readLine());
		int size = N*N;
		int[][] input = new int[size][5];
		
		for(int i = 0 ; i < size; i++) {
			input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		// ================================
		// solve
		Student[] students = new Student[size+1];	// 학생 번호는 1번부터 size
		int[][] seatMap = new int[N+1][N+1];	// 좌석 배치도
		
		// 자리배치 시작
		for(int cnt = 0 ; cnt < size; cnt++) {
			
			// 1. 좋아하는 학생중에 이미 자리를 배정받은 학생이 있는지 확인.
			List<Student> like = new ArrayList<>();
			for(int i = 1 ; i < 5; i++) {
				if(students[input[cnt][i]] != null) like.add(students[input[cnt][i]]);
			}
			
			// 2. 좋아하는 학생과 가장 많이 인접한 칸 찾기.
			// 2 - 1. 앉을 수 있는 자리 찾기
			List<Point> seat = new ArrayList<>();
			for(Student friend : like) {
				// 상하좌우 확인
				for(int d = 0 ; d < 4; d++) {
					int dx = friend.x + delta[d][0];
					int dy = friend.y + delta[d][1];
					
					// 범위 나가지 않고, 이미 앉아있지 않으면
					if(dx > 0 && dx <= N && dy > 0 && dy <= N && seatMap[dx][dy] == 0) {
						int idx = seat.indexOf(new Point(dx,dy,0,0));
						if(idx != -1) seat.get(idx).likecount++;
						else seat.add(new Point(dx,dy,1, emptyCounter(dx,dy,seatMap)));
					}
				}
			}
			// 만약 현재 학생이 제일 먼저 않는 경우. 빈자리를 모두 넣는다.
			// 좋아하는 학생과 같이 앉을 수 있는 자리가 없는 경우도 여기 포함된다.
			if(seat.isEmpty()) {
				for(int i = 1; i <= N; i++) {
					for(int j = 1; j <= N; j++) {
						if(seatMap[i][j] == 0) seat.add(new Point(i,j,0, emptyCounter(i,j,seatMap)));
					}
				}
			}
			
			// 2 - 2. 자리 정하기
			// 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
			// 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
			// 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
			Collections.sort(seat);
			Point curseat = seat.get(0);
			
//			for(Point p : seat) {
//				System.out.println(p);
//			}
			
			// 자리 지정
			int curStudent = input[cnt][0];
			seatMap[curseat.x][curseat.y] = curStudent;
			students[curStudent] = new Student(curseat.x, curseat.y);
			
		}
		
		
		/*
		 * 자리 배치가 모두 끝난 후 학생의 만족도를 구한다. :  그 학생과 인접한 칸에 앉은 좋아하는 학생의 수
		 * 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000이다.
		 * 학생의 만족도의 총 합을 구해보자.
		 */
		
		int sum = 0;
		
		for(int i = 0 ; i < size; i++) {
			Student s = students[input[i][0]];
			
			// 옆자리 앉은 애들 저장
			int[] pair = new int[4];
			int c = 0;
			for(int d = 0 ; d < 4; d++) {
				int dx = s.x + delta[d][0];
				int dy = s.y + delta[d][1];
				
				// 범위 나가지 않으면
				if(dx > 0 && dx < seatMap.length && dy > 0 && dy < seatMap.length) {
					pair[c++] = seatMap[dx][dy];
				}
			}
			
			// 좋아하는 애랑 비교
			int count = 0;
			for(int j = 1 ; j < 5; j++) {
				for(int p = 0 ; p < c ; p++) {
					if(input[i][j] == pair[p]) {
						count++;
						break;
					}
				}
			}
			
			// 점수세기
			switch(count) {
			case 2: sum += 10;
			break;
			case 3: sum += 100;
			break;
			case 4: sum += 1000;
			break;
			default: sum += count;
			}
		}
		
		// 결과 출력
		System.out.println(sum);
		
	}

	private static int emptyCounter(int x, int y, int[][] seatMap) {
		int count = 0;
		// 상하좌우 확인
		for(int d = 0 ; d < 4; d++) {
			int dx = x + delta[d][0];
			int dy = y + delta[d][1];
			
			// 범위 나가지 않고, 이미 앉아있지 않으면
			if(dx > 0 && dx < seatMap.length && dy > 0 && dy < seatMap.length && seatMap[dx][dy] == 0) {
				count++;
			}
		}
		
		return count;
	}
}
