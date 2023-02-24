package week4.BOJ_2096_G5_내려가기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2096_G5_내려가기 {

	// 결과를 한번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		/*
		 * 1. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		/*
		 * 2. 입력파일 객체화
		 */
		int N = Integer.parseInt(st.nextToken());
		// 최대값과 최소값을 담을 dp
		int[] max = new int[3];
		int[] min = new int[3];

		/*
		 * 3. 알고리즘 풀기
		 */
		int[] dy = { -1, 0, 1 }; // 왼쪽 아래, 아래, 오른쪽 아래
		
		// 처음 한 줄 입력 저장
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < 3; i++) {
			int no = Integer.parseInt(st.nextToken());
			max[i] = no;
			min[i] = no;
		}
//		System.out.println("max : "+Arrays.toString(max));
//		System.out.println("min : "+Arrays.toString(min));

		// 두번째 줄 부터 가능한 최대값과 최소값 업데이트
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int[] temp = new int[3];
			for (int j = 0; j < 3; j++) {
				temp[j] = Integer.parseInt(st.nextToken());
			}
			
			// 현재 행의 input을 통해 나올 수 있는 최대값, 최소값 배열
			int[] tempMaxArr = new int[3];
			int[] tempMinArr = new int[3];
			Arrays.fill(tempMinArr, Integer.MAX_VALUE);
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					int ny = j + dy[k];
					if (ny >= 0 && ny < 3) {
						if (tempMaxArr[ny]< max[j] + temp[ny]) {
							tempMaxArr[ny] = max[j] + temp[ny];
						}
						if (tempMinArr[ny] > min[j] + temp[ny]) {
							tempMinArr[ny] = min[j] + temp[ny];
						}
					}
					
				}
			}
			for (int j = 0; j < 3; j++) {
				max[j]=tempMaxArr[j];
				min[j]=tempMinArr[j];
			}
		}
		
		sb.append(Arrays.stream(max).max().getAsInt()).append(" ");
		sb.append(Arrays.stream(min).min().getAsInt());


		/*
		 * 4. 정답 출력
		 */
		System.out.println(sb);
	}
}
