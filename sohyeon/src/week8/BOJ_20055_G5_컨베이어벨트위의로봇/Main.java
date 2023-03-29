package week8.BOJ_20055_G5_컨베이어벨트위의로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, K;
	private static List<Integer> aList = new ArrayList<>();
	private static List<Integer> conveyor = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());			
		
		int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		for (int i=0; i<N*2; i++) {
			aList.add(input[i]);
		}
		for (int i=0; i<N; i++) {
			conveyor.add(0);
		}		
		
		int cnt = 0;
		boolean flag = true;
		while (flag) {
			
			rotate();
			move();
			addRobot();
			flag = check();
			
			cnt++;
			
		}
		
		System.out.println(cnt);
		
	}

	// 벨트 회전
	private static void rotate() {
		
		int get = aList.get(N*2-1);
		aList.remove(N*2-1);
		aList.add(0, get);
				
		conveyor.remove(N-1);
		conveyor.add(0,0);
		conveyor.set(N-1, 0);
	}
	
	// 로봇 이동
	private static void move() {
				
		for (int i=N-2; i>=0; i--) {
			if ((conveyor.get(i) == 1) && (conveyor.get(i+1) == 0) && (aList.get(i+1) != 0)) {
				conveyor.set(i+1, 1);
				conveyor.set(i, 0);
				aList.set(i+1, aList.get(i+1)-1);
			}
		}
	}

	// 로봇 추가
	private static void addRobot() {
		
		if (aList.get(0) != 0) {
			conveyor.set(0,1);
			aList.set(0, aList.get(0)-1);
		}
		
	}

	// 내구도 확인
	private static boolean check() {
		
		int cnt = 0;
		for (int a : aList) {
			if (a == 0) cnt++;
		}
		if (cnt >= K) return false;
		else return true;
	}
}
