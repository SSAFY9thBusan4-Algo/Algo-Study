package week2.BOJ_10972_S3_다음순열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_10972_S3_다음순열 {
	// 결과를 한 번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();
	private static int N;
	private static int[] inputNums;
	
	// 순열로 풀때 썼던 것
	//private static int[] numbers;
	//private static boolean[] isSelected;
	//private static boolean isInputNumsNext;
	//private static String result;
	//private static int total;

	public static void main(String[] args) throws Exception {

		/*
		 * 1. 입력 파일 읽어들이기
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/*
		 * 2. 입력파일 객체화
		 */
		N = Integer.parseInt(br.readLine());
		inputNums = new int[N];

		String[] split = br.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			inputNums[i] = Integer.parseInt(split[i]);
		}

		// 마지막 값
		int[] lastNums = new int[N];
		for (int i = 0; i < N; i++) {
			lastNums[i] = N - i;
		}

		// input 값이 마지막 값이면 -1 출력하고 끝낸다.
		if (Arrays.toString(lastNums).equals(Arrays.toString(inputNums))) {
			System.out.println(-1);
			return;
		}

		/*
		 * 3. 알고리즘 풀기
		 */

		/* 순열 배워서 순열로 했더니 입력의 답은 나오는데 시간 초과가 떴다 ㅠㅠ
		 * 그래서 순열을 보면서 규칙을 열심히 찾았다.
		 * 뒤에서부터 확인하고 오름차순이 맞으면 안바꿔도 되고 
		 * 오름차순이 아니라면 그 인덱스의 값보다 큰 애들중에 가장 작은 값을 
		 * 해당 위치에 넣고 나머지 값들을 정렬해서 원래 배열에 넣어주면 다음 순열이 된다. 
		 */

		int[] resultNums = inputNums.clone();
		for (int i = N-2; i >=0; i--) {
			// 뒤에서부터 오름차순이 아니면
			if(inputNums[i] < inputNums[i+1]) {
				// temp에 오름차순이 아닌 배열을 담는다.
				int[] temp = Arrays.copyOfRange(inputNums, i, N);
				// 정렬은 inputNums[i] 값보다 큰 애들중에 가장 작은 값을 찾기 위함
				Arrays.sort(temp);;
				for (int j = 0; j < temp.length; j++) {
					// inputNums[i] 값을 바꿔주고 나서 temp에서 그 값을 제거해주기 위해 tempVal로 index를 저장했다.
					int tempVal = 0;
					if(inputNums[i]==temp[j]) {
						tempVal = j;
					}
					// 찾은 값으로 바꿔준다.
					if(inputNums[i]<temp[j]) {
						resultNums[i] = temp[j];
						temp[j] = temp[tempVal];
						temp[tempVal] = 10001;
						
						Arrays.sort(temp);
						// 바꾼 값을 제거하기위해 10001값을 넣어주고 정렬뒤 가장 뒷 값을 제거해줬다.
						temp = Arrays.copyOfRange(temp, 0, temp.length-1);
						// 정렬된 나머지 값들을 결과 배열에 넣어줬다.
						for(int k = i+1; k<N; k++) {
							resultNums[k] = temp[k-i-1];
						}
						break;
					}
				}
				break;
			}
		}
		
		for(int i=0; i<N; i++) {
			sb.append(resultNums[i] + " ");
		}

		// 순열 시도
//			isSelected = new boolean[N+1];
//			numbers = new int[N];
//			permutation(0);
//			if(result == null) {
//				sb.append(-1);
//			}
//			else {
//				sb.append(result);
//			}
		//
//			/*
//			 * 4. 정답 출력
//			 */
//			sb.append("total : " + total);
		System.out.println(sb);
	}

	// 순열
//	private static void permutation(int n) {
//		if (n == N) {
//			// [1, 2, 3] 형태
//			total++;
//			if (isInputNumsNext) {
//				result = "";
//				for (int i = 0; i < N; i++) {
//					result += numbers[i] + " ";
//				}
//				isInputNumsNext = false;
//			}
//
//			System.out.println(Arrays.toString(numbers));
//			if (Arrays.toString(numbers).equals(Arrays.toString(inputNums))) {
//				isInputNumsNext = true;
//			}
//			return;
//		}
//
//		for (int i = 1; i <= N; i++) {
//			if (isSelected[i]) {
//				continue;
//			}
//			numbers[n] = i;
//			isSelected[i] = true;
//			permutation(n + 1);
//			isSelected[i] = false;
//		}
//
//	}
}
