package week12.BOJ_1796_G4_신기한키보드;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1796_G4_신기한키보드 {

	public static List<List<Alphabet>> alphaList; 
	public static int result;

	public static class Alphabet implements Comparable<Alphabet> {
		public char alphabet;
		public int location;

		public Alphabet(char alphabet, int location) {
			super();
			this.alphabet = alphabet;
			this.location = location;
		}

		@Override
		public String toString() {
			return "Alphabet [alphabet=" + alphabet + ", location=" + location + "]";
		}

		@Override
		public int compareTo(Alphabet o) {
			if (this.alphabet == o.alphabet) {
				return this.location - o.location;
			}
			return this.alphabet - o.alphabet;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		char[] alphabet = in.readLine().toCharArray();

		List<Alphabet> list = new ArrayList<>();
		for (int i = 0; i < alphabet.length; i++) {
			list.add(new Alphabet(alphabet[i], i));
		}

		Collections.sort(list); // 알파벳 순서, 알파벳 위치 순으로 정렬

		/*
		 * alphaList에 담을 데이터 형식 예시(acbbc)
		 * Alphabet [alphabet=a, location=0] 
		 * Alphabet [alphabet=b, location=2] Alphabet [alphabet=b, location=3] 
		 * Alphabet [alphabet=c, location=1] Alphabet [alphabet=c, location=4] 
		 */

		alphaList = new ArrayList<>();
		alphaList.add(new ArrayList<>());
		alphaList.get(0).add(list.get(0));
		char prev = list.get(0).alphabet;
		int index = 0;
		for (int i = 1; i < list.size(); i++) {
			Alphabet cur = list.get(i);
			if (prev != cur.alphabet) { // 알파벳이 달라지면 다음 index의 List에 저장
				index++;
				alphaList.add(new ArrayList<>());
				alphaList.get(index).add(list.get(i));
				prev = cur.alphabet;
			} else {
				alphaList.get(index).add(list.get(i));
			}
		}

		result = Integer.MAX_VALUE;
		alphaPrint(0, 0, 0);
		System.out.println(result);

	}

	// index : 같은 알파벳이 담겨있는 alphaList의 index
	// curLocation : 현재 커서의 위치
	// moveCount : 현재까지 움직인 횟수
	private static void alphaPrint(int index, int curLocation, int moveCount) {
		// 종료조건
		if (index == alphaList.size()) {
			if (result > moveCount) {
				result = moveCount;
			}
			return;
		}

		List<Alphabet> curAlphaList = alphaList.get(index); // 같은 알파벳이 담겨있는 list(ex) a만 담겨있음 or b만 담겨있음)
		int min = curAlphaList.get(0).location; // 현재 탐색하려는 알파벳 위치의 최소값
		int max = curAlphaList.get(curAlphaList.size() - 1).location;// 현재 탐색하려는 알파벳 위치의 최대값

		// 현재 커서의 위치가 탐색하려는 알파벳의 사이에 있을 때(ex) bb커서bb)
		// 왼쪽을 먼저 탐색할 지 or 오른쪽을 먼저 탐색할 지에 따라 결과가 달라짐
		if (min < curLocation && max > curLocation) {
			int temp_moveCount = moveCount;
			int tem_curLocation = curLocation;
			
			// 왼쪽 먼저 방문
			int move = curLocation - min; // 하나의 알파벳을 출력하기 위해 움직인 횟수
			moveCount += move + 1;
			curLocation = min;
			for (int i = 1; i < curAlphaList.size(); i++) { // 왼쪽을 먼저 방문했으므로 오름차순으로 list 탐색
				move = curAlphaList.get(i).location - curLocation;
				moveCount += move + 1;
				curLocation = curAlphaList.get(i).location;
			}
			alphaPrint(index + 1, curLocation, moveCount);

			// 오른쪽 먼저 방문
			moveCount = temp_moveCount;
			curLocation = tem_curLocation;
			
			move = max - curLocation;
			moveCount += move + 1;
			curLocation = max;
			for (int i = curAlphaList.size() - 2; i >= 0; i--) {// 내림차순으로 list 탐색
				move = curLocation - curAlphaList.get(i).location;
				moveCount += move + 1;
				curLocation = curAlphaList.get(i).location;
			}
			alphaPrint(index + 1, curLocation, moveCount);

		}
		// 탐색하려는 알파벳이 모두 현재 커서의 왼쪽이나 오른쪽에 몰려있을 때 ex) 커서bbbb, bbbb커서
		// 한방향으로 탐색하면 됨
		else {
			if (min >= curLocation) { // 커서가 왼쪽에 있을 때 -> 오름차순으로 방문
				for (int i = 0; i < curAlphaList.size(); i++) {
					int move = curAlphaList.get(i).location - curLocation;
					moveCount += move + 1;
					curLocation = curAlphaList.get(i).location;
				}
			} else { // 커서가 오른쪽에 있을 때 -> 내림차순으로 방문
				for (int i = curAlphaList.size() - 1; i >= 0; i--) {
					int move = curLocation - curAlphaList.get(i).location;
					moveCount += move + 1;
					curLocation = curAlphaList.get(i).location;
				}
			}
			alphaPrint(index + 1, curLocation, moveCount);
		}

	}
}
