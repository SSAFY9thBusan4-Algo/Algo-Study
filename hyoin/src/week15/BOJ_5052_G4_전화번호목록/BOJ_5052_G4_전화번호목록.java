package week15.BOJ_5052_G4_전화번호목록;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class BOJ_5052_G4_전화번호목록 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringBuilder sb2 = new StringBuilder();

		int t = Integer.parseInt(in.readLine());
		for (int test_case = 0; test_case < t; test_case++) {
			int n = Integer.parseInt(in.readLine());

			String[] input = new String[n];
			for (int i = 0; i < n; i++) {
				input[i] = in.readLine();
			}
			Arrays.sort(input, (String s1, String s2) -> s1.length() - s2.length()); // 자리수 기준 정렬

			HashSet<String> set = new HashSet<>();
			int max = input[n - 1].length(); // 최대자리수
			int min = input[0].length(); // 최소자리수

			set.add(input[0]);

			String result = "YES";
			for (int i = 1; i < n; i++) {
				char[] arr = input[i].toCharArray();
				sb = new StringBuilder();

				for (int j = 0; j < arr.length; j++) {
					if (j < min - 1) { // 최소자리수-1만큼 문자열 가져오기
						sb.append(arr[j]);
					} else {
						if (j >= max) { // 최대자리수를 넘어가면 종료
							break;
						}
						sb.append(arr[j]);
						if (set.contains(sb.toString())) { // 접두어인 경우
							result = "NO";
							break;
						}
					}
				}
				set.add(input[i]);
			}

			sb2.append(result).append("\n");
		}

		System.out.println(sb2.toString());
	}
}
