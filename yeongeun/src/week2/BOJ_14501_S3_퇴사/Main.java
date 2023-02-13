package src.week2.BOJ_14501_S3_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int N, result=0;
	private static int[][] timetable;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력파일 읽기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 2. 입력파일 객체화
		N = Integer.parseInt(br.readLine());
		timetable = new int[N][2];
		for(int i = 0 ; i < N ; i++) {
			String[] input = br.readLine().split(" ");
			timetable[i][0] = Integer.parseInt(input[0]);
			timetable[i][1] = Integer.parseInt(input[1]);
		}

		//3. 알고리즘 - 조합
		choose(0,0);

		// 4. 정답 출력
		System.out.println(result);
	}

	private static void choose(int sum, int start) {
		if(start >= N) {					//날짜가 다 지나면
			result = Math.max(sum, result);	//제일 많이 번 돈 저장
			return;
		}

		for(int i = start; i < N ; i++) {	//i : 일 수 (0부터)
			int day = timetable[i][0];
			int pay = timetable[i][1];

			if(i+day <= N)					//일을 완료 할 수 있으면
				choose(sum+pay, i+day);		//일을 하고 일을 완료한 날로 넘긴다.
			else if(i == N-1)				//마지막 날인데 일을 완료할 수 없다.
				choose(sum, N);				//금액을 추가하지 않고 날짜 넘김.
		}
	}
}
