package week14.BOJ_2529_S1_부등호;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2529_S1_부등호 {

	private static int K;
	private static char[] sign;
	private static long max;
	private static long min;
	private static String strMax;
	private static String strMin;

	private static int[] numbers;
	private static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		sign = new char[K];
		for(int i=0; i<K; i++) {
			sign[i] = st.nextToken().charAt(0);	
		}
		
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
		numbers = new int[K + 1];
		isSelected = new boolean[10];
		permutation(0);

		System.out.println(strMax);
		System.out.println(strMin);

	}

	private static void permutation(int cnt) {
		// 기저조건
		if (cnt == K + 1) {
			// 부등호 조건이 맞는지 체크
			for (int i = 0; i < K; i++) {
				char curSign = sign[i];
				if (curSign == '<') { // 부등호가 <일 때
					if (numbers[i] > numbers[i + 1]) { // 부등호 조건이 맞지 않으므로 패스
						return;
					}
				} else {
					if (numbers[i] < numbers[i + 1]) {
						return;
					}
				}
			}

			String strNum = ""; // 숫자합치기
			for (int i = 0; i < K + 1; i++) {
				strNum += numbers[i];
			}

			long num = Long.parseLong(strNum);

			if (max < num) { // 최대값 갱신
				max = num;
				strMax=strNum;
			}
			if (min > num) {// 최소값 갱신
				min = num;
				strMin=strNum;
			}
			return;
		}

		// 유도조건
		for (int i = 0; i < 10; i++) {
			if (!isSelected[i]) {
				numbers[cnt] = i;
				isSelected[i] = true;
				permutation(cnt + 1);
				isSelected[i] = false;
			}
		}
	}
}
