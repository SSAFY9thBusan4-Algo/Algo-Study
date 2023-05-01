package week13.BOJ_2879_G3_코딩은예쁘게;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2879_G3_코딩은예쁘게 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] input = new int[N];
		int[] answer = new int[N];
		int[] diff = new int[N];

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}

		// 필요한 탭의 개수(차이)
		for (int i = 0; i < N; i++) {
			diff[i] = input[i] - answer[i];
		}

		int result = 0;
		int prev = diff[0]; // 이전 차이 값

		for (int i = 1; i < N; i++) {
			if (diff[i] >= 0) { // 현재 차이값이 양수일 때
				// 부호가 다를 때(그룹이 달라지므로 이전 그룹의 최대값을 결과에 추가)
				// 오름차순이므로 prev에 최대값이 저장되어있음 
				if (prev < 0) { 
					result += Math.abs(prev);
				} 
				// 부호가 같을 때
				// 오름차순일 때는 prev에 계속 최대값 업데이트 됨
				// 내림차순일 때에는 이전값과 현재값의 차이가 결과에 누적
				else {
					if (prev > diff[i]) {// 오름차순이 끊겼을 때(내림차순)
						result += Math.abs(prev) - Math.abs(diff[i]);
					}
				}
			} else {// 현재 차이값이 음수일 때
				if (prev > 0) {
					result += Math.abs(prev);
				} else {
					if (prev < diff[i]) { // 내림차순이 끊겼을 때(내림차순)
						result += Math.abs(prev) - Math.abs(diff[i]);
					}
				}
			}
			
			// 이전 값 업데이트
			prev = diff[i];
		}

		result += Math.abs(prev); // 마지막 결과 업데이트

		System.out.println(result);
	}

}
