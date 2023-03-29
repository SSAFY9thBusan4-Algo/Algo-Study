package week8.BOJ_20055_G5_컨베이어벨트위의로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	static int N, broken;
	static LinkedList<Integer> power = new LinkedList<>();
	static LinkedList<Boolean> robot = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);
		split = br.readLine().split(" ");
		int temp;
		for(int i=0;i<2*N;i++) {
			temp = Integer.parseInt(split[i]);
			power.offer(temp);
			robot.offer(false);
		}
		broken = 0;
		int count = 0;
		while(broken<K) {
			count++;
			function1();
			function2();
			function3();
		}
		System.out.println(count);
	}
	private static void function1() {
		power.offerFirst(power.pollLast());
		robot.offerFirst(robot.pollLast());
		robot.set(N-1, false);
	}
	private static void function2() {
		for(int i = N-2; i>=0;i--) {
			if(robot.get(i) && !robot.get(i+1) && power.get(i+1) != 0) { 
				robot.set(i, false);
				robot.set(i+1, true);
				power.set(i+1, power.get(i+1) - 1);
				if(power.get(i+1) == 0) {
					broken++;
				}
			}
		}
		robot.set(N-1, false);
	}
	private static void function3() {
		if(!robot.get(0) && power.get(0) != 0) {
			robot.set(0, true);
			power.set(0, power.get(0)-1);
			if(power.get(0) == 0) {
				broken++;
			}
		}
	}
}