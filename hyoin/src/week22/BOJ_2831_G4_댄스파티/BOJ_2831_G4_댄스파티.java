import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2831_G4_댄스파티 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[] man = new int[N];
		int[] women = new int[N];

		int manP = 0; // 남자 음수 개수 = 양수 남자의 시작 index
		int womenP = 0; // 여자 음수 개수 = 양수 여자의 시작 index

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			man[i] = Integer.parseInt(st.nextToken());
			if (man[i] < 0) {
				manP++;
			}
		}

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			women[i] = Integer.parseInt(st.nextToken());
			if (women[i] < 0) {
				womenP++;
			}
		}

		// 오름차순 정렬
		Arrays.sort(man);
		Arrays.sort(women);

		// 남자음수 X 여자양수 중 가능한 쌍의 개수 구하기
		// 남자 음수 중 가장 큰값과 여자양수 중 가장 작은값끼리 매칭되야함
		int womenIndexP = womenP; // 비교하는 양수 여자의 index
		int result = 0;
		for (int i = manP - 1; i >= 0; i--) { // 남자음수개수만큼 반복
			if (womenIndexP >= N) { // 여자양수가 더이상 없을 때 break
				break;
			}
			if (Math.abs(man[i]) > Math.abs(women[womenIndexP])) { // 조건이 맞으면 양수 여자의 index 옮김
				result++;
				womenIndexP++;
			}
		}

		// 남자양수 X 여자음수 중 가능한 쌍의 개수 구하기
		int manIndexP = manP;
		for (int i = womenP - 1; i >= 0; i--) {
			if (manIndexP >= N) {
				break;
			}
			if (Math.abs(women[i]) > Math.abs(man[manIndexP])) {
				result++;
				manIndexP++;
			}

		}

		System.out.println(result);
	}
}
