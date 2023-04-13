package src.week11.BOJ_11000_G5_강의실배정

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][3];
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new  StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// solve
		// 강의실 : 시작시간 기준 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		// 현재 쓰고있는 강의실 : 종료시간 기준 정렬
        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for(int i = 0 ; i < N; i++) {
            int[] cur = arr[i];
            if(!rooms.isEmpty() && cur[0] >= rooms.peek()){
                rooms.poll();
            }
            rooms.add(cur[1]);
        }
        
        System.out.println(rooms.size());
    }
}
