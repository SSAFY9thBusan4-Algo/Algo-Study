import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int n, k;
	static int[] belt, durBelt, robot;
	static int robotNo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		belt = new int[n*2];
		durBelt = new int[n*2];
		robot = new int[n*2];
		
		for(int i=0; i<n*2; i++) {
			belt[i] = i+1;
		}
	
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n*2; i++) {
			durBelt[i] = Integer.parseInt(st.nextToken());
		}
		
		robotNo = 1;
		int cnt = 1;
		while(true) {
			turnBelt();
			
			moveRobot();
			
			newRobot();
			
			if(isEnd()) break;
			
			cnt++;
		}
		
		
		System.out.println(cnt);
	}
	
	private static void turnBelt() {
		int tempBelt = belt[n*2-1];
		int tempDurBlet = durBelt[n*2-1];
		int tempRobot = robot[n*2-1];
		
		for(int i=n*2-1; i>0; i--) {
			belt[i] = belt[i-1];
			durBelt[i] = durBelt[i-1];
			robot[i] = robot[i-1];
			
		}
		
		belt[0] = tempBelt;
		durBelt[0] = tempDurBlet;
		robot[0] = tempRobot;
		
		robot[n-1] = 0;
	}

	private static void moveRobot() {
		for(int i=n-2; i>=0; i--) {
			if(robot[i] != 0) { // 로봇이 있고
				if(robot[i+1]==0 && durBelt[i+1]>0) { // 다음칸에 로봇이 없고, 내구도가 1이상
					// 로봇 이동
					robot[i+1] = robot[i]; 
					robot[i] = 0;
					// 내구도 감소
					durBelt[i+1]--; 
				}
			}
		}
		
		robot[n-1] = 0;
	}

	private static void newRobot() {
		if(durBelt[0] > 0) {
			robot[0] = robotNo++;
			durBelt[0]--;
		}
	}

	private static boolean isEnd() {
		int cnt = 0;
		for(int i=0; i<n*2; i++) {
			if(durBelt[i] == 0) cnt++;
		}
		
		if(cnt >= k) return true;
		return false;
	}
}
