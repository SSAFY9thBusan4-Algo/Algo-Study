package week9.BOJ_1043_G4_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1043_G4_거짓말 {

	public static int N;
	public static int M;
	public static int TCount; // 진실을 아는 사람의 수
	public static int TPeople; // 진실을 아는 사람
	public static List[] partyList; // 각 파티마다 오는 사람의 수와 번호

	public static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		makeSet();

		st = new StringTokenizer(in.readLine());
		TCount = Integer.parseInt(st.nextToken());
		for (int i = 0; i < TCount; i++) {
			int TPeople = Integer.parseInt(st.nextToken());
			parents[TPeople] = 0; // 진실을 아는 사람의 부모를 0으로 설정
		}

		// 각 파티의 정보 저장
		partyList = new List[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			partyList[i] = new ArrayList<Integer>();
			int partyCount = Integer.parseInt(st.nextToken());
			for (int j = 0; j < partyCount; j++) {
				partyList[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		// 같은 파티에 온 사람들의 부모를 통일(더 작은 부모로 통일)
		// 진실을 아는 사람이 존재하면 해당 파티에 온 사람들의 부모는 모두 0으로 통일됨
		for (int i = 0; i < M; i++) {
			int people1 = (int) partyList[i].get(0);
			for (int j = 1; j < partyList[i].size(); j++) {
				int people2 = (int) partyList[i].get(j);
				union(people1, people2);
			}
		}

		// 위의 과정에서 진실을 아는 사람들이 업데이트 됐으므로 한번 더 부모를 통일
		// 부모가 0이 아니면 거짓말 가능하므로 result++
		int result = 0;
		for (int i = 0; i < M; i++) {
			int people1 = (int) partyList[i].get(0);
			for (int j = 1; j < partyList[i].size(); j++) {
				int people2 = (int) partyList[i].get(j);
				union(people1, people2);
			}
			if (findSet(people1) != 0) {
				result++;
			}
		}

		System.out.println(result);

	}

	private static void makeSet() {
		parents = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	private static int findSet(int a) {

		if (parents[a] == a) {
			return a;
		}

		return parents[a] = findSet(parents[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot) {
			return false;
		}

		if (aRoot < bRoot) { // 더 작은 노드로 부모 통일
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}
		return true;
	}
}
