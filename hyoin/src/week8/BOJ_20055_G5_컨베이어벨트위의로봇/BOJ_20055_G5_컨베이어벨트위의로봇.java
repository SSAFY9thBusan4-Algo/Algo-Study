package week8.BOJ_20055_G5_컨베이어벨트위의로봇;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20055_G5_컨베이어벨트위의로봇 {

	/*
	 * 1. 벨트 회전 : 양방향큐를 사용하여 맨뒤의 벨트 index를 맨앞으로 삽입하면서 회전
	 * 2. 로봇 이동 : 큐에서 로봇을 poll하면서 이동 
	 * 		- 내려가는 위치일 때는 추가 안함
	 * 		- 이동이 가능할 때는 현재의 벨트의 방문여부를 false로 변경하고 이동할 벨트 index를 큐에 추가
	 * 		- 이동이 불가능할 때는 현재의 벨트 index를 큐에 추가
	 * 3. 새로운 로봇 올리기 : 올리려는 위치에 로봇이 없고 내구도가 1이상일 때 큐에 추가
	 * 4. 내구도가 0인 칸의 개수가 K이상이면 종료
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Deque<Integer> beltQueue = new ArrayDeque<>(); // 회전하는 벨트의 index를 담을 큐
		int[] belt = new int[N * 2]; // 벨트의 내구도

		Queue<Integer> robots = new ArrayDeque<>(); // 벨트위에 올라가있는 로봇 리스트
		boolean[] isRobot = new boolean[N * 2]; // 해당 벨트의 index위에 로봇이 있는지 없는지 알려주는 배열

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N * 2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
			beltQueue.offer(i);
		}

		int down = N - 1; // 내려가는 벨트의 index
		int count = 0; // 내구도가 0인 벨트의 개수
		int result = 1; // 최종결과
		while (true) {
			// 1. 벨트 회전
			// 맨뒤의 벨트 index를 맨앞으로 삽입
			int temp = beltQueue.pollLast();
			beltQueue.offerFirst(temp);
			// 회전이 끝난 후 내려가는 벨트의 위치에 있으면 로봇 삭제
			if (isRobot[down]) {
				isRobot[down]=false;
				robots.poll();
			}
			down = (down - 1) < 0 ? 2 * N - 1 : down - 1; // 인덱스범위 조정

			// 2. 로봇 이동(가장 먼저 벨트에 올라간 로봇부터 이동 -> 큐는 선입선출이라 따로 조건처리 안해줘도 됨)
			int qSize = robots.size();
			for (int i = 0; i < qSize; i++) {
				int curBeltNo = robots.poll(); // 현재 로봇이 위치한 벨트 index

				if (curBeltNo == down) { // 로봇이 내려가는 위치에 있으면 큐에 현재 로봇 추가 안함
					isRobot[curBeltNo]=false;
					continue;
				}

				int nextBeltNo = curBeltNo + 1; // 로봇이 이동할 다음 벨트 index
				if (nextBeltNo == 2 * N) { // 벨트 index가 2*N일 때 0으로 조정해줘야함
					if (!isRobot[0] && belt[0] >= 1) { // 로봇이 이동 가능할 때
						isRobot[nextBeltNo - 1] = false;
						isRobot[0] = true;
						belt[0] -= 1;
						if(belt[0]==0) { // 내구도가 0일 때
							count++;
						}
						robots.offer(0); // 이동한 로봇의 위치 큐에 추가

					}
					else { // 이동이 불가능하면 현재 로봇 위치 그대로 큐에 추가
						robots.offer(curBeltNo);
					}
				} else {
					if (!isRobot[nextBeltNo] && belt[nextBeltNo] >= 1) {
						isRobot[nextBeltNo - 1] = false;
						isRobot[nextBeltNo] = true;
						belt[nextBeltNo] -= 1;
						if(belt[nextBeltNo]==0) {
							count++;
						}
						robots.offer(nextBeltNo);
					}
					else {
						robots.offer(curBeltNo);
					}
				}
			}
			

			// 3. 새로운 로봇 올리기
			if (!isRobot[beltQueue.peek()]&&belt[beltQueue.peek()]>=1) { // 올리려는 위치에 로봇이 없고 내구도가 1이상일 때
				robots.offer(beltQueue.peek()); // 로봇추가
				isRobot[beltQueue.peek()] = true;
				belt[beltQueue.peek()] -= 1;
				if(belt[beltQueue.peek()]==0) {
					count++;
				}
			}
			
			if (count >= K) { // 내구도가 0인 칸의 개수가 K이상이면 종료
				break;
			}
			
			result++;
		}
		System.out.println(result);

	}

}

