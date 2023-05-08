package week14.BOJ_2866_G5_문자열잘라내기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2866_G5_문자열잘라내기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] input = new char[R][C];
		for (int i = 0; i < R; i++) {
			input[i] = in.readLine().toCharArray();
		}

		// 열끼리 중복된 단어가 존재하면 map과 map2에 추가
		HashMap<Integer, String> map = new HashMap<>(); // (열, 문자) 
		HashMap<String, Integer> map2 = new HashMap<>(); // (문자, 열)(map의 반대)
		HashSet<String> set = new HashSet<>(); // 문자열 중복 체크 할 hashset
		for (int j = 0; j < C; j++) {
			String str = Character.toString(input[R - 1][j]);
			if (!set.contains(str)) { // 아직 중복된 단어가 없을 때 map2에 String을 key로 저장
				map2.put(str, j);
				set.add(str);
			} else { // 중복된 단어가 존재할 때 map에 (열번호, 문자열)을 저장
				map.put(map2.get(str), str);
				map.put(j, str);
			}
		}

		// 행을 밑에서부터 탐색하며 열끼리 중복된 단어가 존재하면 map과 map2에 추가
		int result = 0;
		if (map.size() != 0) {
			result=1;

			for (int i = R - 2; i > 0; i--) {
				set = new HashSet<>();
				HashMap<Integer, String> tempMap = new HashMap<>();
				HashMap<String, Integer> tempMap2 = new HashMap<>();
				// 기존의 중복 문자열에 새로운 행의 문자를 합침
				// 합쳐진 문자열 끼리 각 열과 비교해서 중복된 단어가 존재하면 tempMap과 tempMap2에 추가
				for (Map.Entry<Integer, String> entry : map.entrySet()) {
					StringBuilder sb = new StringBuilder();
					int colIndex = entry.getKey(); // 현재 열 index
					sb.append(entry.getValue()).append(input[i][colIndex]); // 기존의 중복 문자열에 새로운 행의 문자를 합침

					if (!set.contains(sb.toString())) {
						tempMap2.put(sb.toString(), colIndex);
						set.add(sb.toString());
					} else {
						tempMap.put(tempMap2.get(sb.toString()), sb.toString());
						tempMap.put(colIndex, sb.toString());
					}
				}

				// map과 map2 업데이트
				map = tempMap;
				map2 = tempMap2;

				if (map.size() == 0) { // map의 크기가 0이면 더이상 같은 문자열이 없다는 뜻
					break;
				}
				result++;
			}
		}

		// result는 밑에서부터 같은 문자열이 존재하는 행의 개수이므로 실제 정답은 R-1-result
		System.out.println(R-1-result);
	}
}
